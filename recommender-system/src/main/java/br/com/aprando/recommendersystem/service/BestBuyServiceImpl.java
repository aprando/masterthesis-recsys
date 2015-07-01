package br.com.aprando.recommendersystem.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import br.com.aprando.recommendersystem.base.RestClient;
import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.base.Utils;
import br.com.aprando.recommendersystem.domain.Categoria;
import br.com.aprando.recommendersystem.domain.Produto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

@Service
public class BestBuyServiceImpl implements BestBuyService {

	@Autowired
	MongoOperations mongoTemplate;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	ProdutoService produtoService;

	RestClient rest = new RestClient();

	final String url = "http://api.remix.bestbuy.com/v1/";

	final String format = "?format=json";

	final String apikey = "&apiKey=" + Utils.BESTBUY_KEY;

	final String showall = "&show=all";
	
	final String pagesize = "&pageSize=100";
	
	final String page = "&page=";
	

	private Map<String, String> getRootCategories() {
		Map<String, String> rootCategories = new HashMap<String, String>();
		rootCategories.put("abcat0100000", "TV & Home Theater");
		rootCategories.put("abcat0200000", "Audio");
		rootCategories.put("abcat0500000", "Computers & Tablets");
		rootCategories.put("abcat0600000", "Movies & Music");
		rootCategories.put("abcat0700000", "Video Games");
		rootCategories.put("abcat0207000", "Musical Instruments");
		return rootCategories;
	}

	@Override
	public void cargaCategorias() throws ServiceException {
		try {
			for (String key : getRootCategories().keySet()) {
				StringBuilder call = new StringBuilder();
				call.append(url + "categories(id=" + key + ")");
				call.append(format);
				call.append(apikey);
				call.append(showall);

				try {
					salvarCategoriaRecursivo(parseJsonCategoria(rest.get(call.toString())));
				} catch (Exception e) {
					System.out.println(" ERRO ao salvar categoria raiz :" + key);
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private void salvarCategoriaRecursivo(Categoria categoria) throws Exception {
		if(categoria.getNome().toLowerCase().indexOf("best buy") == -1 
				&& categoria.getNome().toLowerCase().indexOf("gift") == -1
				&& categoria.getNome().toLowerCase().indexOf("pre-owned") == -1){
			
			try {
				Thread.sleep(350);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
	
			System.out.println(" .. Salvando categoria " + categoria.getId() + " : " + categoria.getNome());
			categoria = categoriaService.salvar(categoria);
			if (categoria.getSubCategorias() != null && !categoria.getSubCategorias().isEmpty()) {
				Categoria subCategoria = null;
				for (Categoria c : categoria.getSubCategorias()) {
					StringBuilder call = new StringBuilder();
					call.append(url + "categories(id=" + c.getId() + ")");
					call.append(format);
					call.append(apikey);
					call.append(showall);
	
					subCategoria = parseJsonCategoria(rest.get(call.toString()));
					subCategoria.setIdCategoriaPai(categoria.getId());
					subCategoria.setNomeCategoriaPai(categoria.getNome());
					try {
						this.salvarCategoriaRecursivo(subCategoria);
					} catch (Exception e) {
						System.out.println("Erro ao salvar a categoria " + subCategoria.getId() + " : " + subCategoria.getNome());
						e.printStackTrace();
					}
	
				}
			}
		}
	}

	private Categoria parseJsonCategoria(String json) {
		Categoria categoria = new Categoria();
		categoria.setFonte(Utils.BESTBUY);

		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);
		JsonElement element = new JsonParser().parse(reader);
		JsonObject rootObject = element.getAsJsonObject();
		JsonArray categories = rootObject.getAsJsonArray("categories");
		JsonObject category = categories.get(0).getAsJsonObject();

		JsonElement field = category.get("id");
		if (field != null)
			categoria.setId(field.getAsString());

		field = category.get("name");
		if (field != null)
			categoria.setNome(field.getAsString());

		JsonArray subCategorias = category.getAsJsonArray("subCategories");
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria subCategoria = null;
		if (subCategorias != null && subCategorias.size() > 0) {	 
			for (int i = 0; i < subCategorias.size(); i++) {
				subCategoria = new Categoria();
				subCategoria.setFonte(Utils.BESTBUY);

				rootObject = subCategorias.get(i).getAsJsonObject();

				field = rootObject.get("id");
				if (field != null)
					subCategoria.setId(field.getAsString());

				field = rootObject.get("name");
				if (field != null)
					subCategoria.setNome(field.getAsString());

				categorias.add(subCategoria);
			}

		}
		categoria.setSubCategorias(categorias);
		return categoria;
	}

	@Override
	public void cargaProdutos() throws ServiceException {
		try {
			StringBuilder call = null;
			System.out.println(" .. Iniciando processo de carga da produtos da BestBuy ");
			List<Categoria> categorias = categoriaService.listarIdCategoriaSemSubcategoria();
			System.out.println(" .. Total de categorias filhas sem subcategoria encontrados: " + categorias.size());
			for (Categoria cat : categorias) {
				System.out.println(" .. Recuperando produtos da categoria " + cat.getId() + " pagina 1");
				call = new StringBuilder();
				call.append(url + "products(categoryPath.id=" + cat.getId() + ")");
				call.append(format);
				call.append(apikey);
				call.append(showall);
				call.append(pagesize);
				try {
					
					String json = rest.get(call.toString());
					int totalPaginas = getTotalPaginas(json);
					
					List<Produto> produtos = new ArrayList<Produto>();
					produtos.addAll(parseJsonProdutos(json));
					
					if(totalPaginas > 1){
						call.append(page);
						for(int i = 2; i <= totalPaginas; i++){
							try {
								Thread.sleep(500);
								System.out.println(" .. Recuperando produtos da categoria " + cat.getId() + " pagina " +  i + " de " + totalPaginas);
								produtos.addAll(parseJsonProdutos(rest.get(call.toString() + i)));
							} catch(Exception e){
								e.printStackTrace();
							}
						}
					}
					
					System.out.println(" .. Total de produtos " + (produtos == null ? "0" :produtos.size()) + " encontrados na categoria "  + cat.getId());
					for (Produto p : produtos) {
						
						try {
							System.out.println(" .. Salvando produto " + p.getId() + " da categoria " + cat.getId());
							produtoService.salvar(p);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(" ERRO! Ao processar categoria  "  + cat.getId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

	}

	private int getTotalPaginas(String json) {
		try{
			JsonElement element = new JsonParser().parse(json);
			JsonObject rootObject = element.getAsJsonObject();
			return rootObject.getAsJsonPrimitive("totalPages").getAsInt();
		}catch(Exception e){
			System.out.println("Erro ao recuperar o total de paginas no json: " + json);
			e.printStackTrace();
			return 1;
		}
	}
	
	private List<Produto> parseJsonProdutos(String json) {
		List<Produto> produtos = new ArrayList<Produto>();
		Produto produto = null;

		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);
		JsonElement element = new JsonParser().parse(reader);
		JsonObject rootObject = element.getAsJsonObject();
		JsonArray products = rootObject.getAsJsonArray("products");
		JsonObject product = null;
		JsonElement field = null;

		if (products != null && products.size() > 0) {
			for (int i = 0; i < products.size(); i++) {
				try {
					produto = new Produto();
					produto.setFonte(Utils.BESTBUY);

					product = products.get(i).getAsJsonObject();

					field = product.get("productId");
					if (field != null && !field.isJsonNull())
						produto.setId(field.getAsString());

					field = product.get("sku");
					if (field != null && !field.isJsonNull())
						produto.setSku(field.getAsLong());

					field = product.get("name");
					if (field != null && !field.isJsonNull())
						produto.setNome(field.getAsString());

					field = product.get("regularPrice");
					if (field != null && !field.isJsonNull())
						produto.setPreco(field.getAsDouble());

					field = product.get("image");
					if (field != null && !field.isJsonNull())
						produto.setImagem(field.getAsString());

					field = product.get("largeImage");
					if (field != null && !field.isJsonNull())
						produto.setImagemGrande(field.getAsString());

					field = product.get("shortDescription");
					if (field != null && !field.isJsonNull())
						produto.setDescricaoCurta(field.getAsString());

					field = product.get("shortDescriptionHtml");
					if (field != null && !field.isJsonNull())
						produto.setDescricaoCurtaHtml(field.getAsString());

					field = product.get("longDescription");
					if (field != null && !field.isJsonNull())
						produto.setDescricaoLonga(field.getAsString());

					field = product.get("longDescriptionHtml");
					if (field != null && !field.isJsonNull())
						produto.setDescricaoLongaHtml(field.getAsString());

					field = product.get("categoryPath");
					if (field != null && !field.isJsonNull()){
						List<Categoria> categorias = new ArrayList<Categoria>();
						JsonArray categoryPath = field.getAsJsonArray();
						for (int j = 0; j < categoryPath.size(); j++) {
							JsonObject category = categoryPath.get(j).getAsJsonObject();
							Categoria cat = new Categoria();
							cat.setId(category.get("id").getAsString());
							cat.setNome(category.get("name").getAsString());
							categorias.add(cat);
						}
						produto.setCategorias(categorias);
					}

					field = product.get("details");
					if (field != null && !field.isJsonNull()){
						HashMap<String, String> detalhes = new HashMap<String, String>();
						JsonArray details = field.getAsJsonArray();
						for (int j = 0; j < details.size(); j++) {
							JsonObject detail = details.get(j).getAsJsonObject();
							detalhes.put(detail.get("name").getAsString(),detail.get("value").getAsString());
						}
						produto.setDetalhes(detalhes);
					}

					field = product.get("features");
					if (field != null && !field.isJsonNull()){
						ArrayList<String> caracteristicas = new ArrayList<String>();
						JsonArray features = field.getAsJsonArray();
						for (int j = 0; j < features.size(); j++) {
							JsonObject feature = features.get(j).getAsJsonObject();
							caracteristicas.add(feature.get("feature").getAsString());
						}
						produto.setCaracteristicas(caracteristicas);
					}

					produtos.add(produto);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERRO ao parsear... segue o jogo!");
				}
			}
		}
		return produtos;
	}
	
	public static void main(String[] args) {
		System.out.println("Best Buy".indexOf("Best Buy"));
		System.out.println("Gift".indexOf("Gift"));
	}
}
