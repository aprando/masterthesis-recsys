package br.com.aprando.recommendersystem.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class RestClient {

	/*
	 * KEY: w7yw448r9w5d5sjmr4mrqa9a
	 * 
	 * All categories
	 * http://api.remix.bestbuy.com/v1/categories?format=json&apiKey=w7yw448r9w5d5sjmr4mrqa9a&show=id,name
	 * 
	 * http://api.remix.bestbuy.com/v1/categories?format=json&apiKey=
	 * w7yw448r9w5d5sjmr4mrqa9a&show=all
	 * 
	 * Category by id
	 * http://api.remix.bestbuy.com/v1/categories(id=cat00000)?format
	 * =json&apiKey=w7yw448r9w5d5sjmr4mrqa9a&show=subCategories
	 * 
	 * Category by name
	 * http://api.remix.bestbuy.com/v1/categories(name=Leisure%20
	 * Gifts)?format=json&apiKey=w7yw448r9w5d5sjmr4mrqa9a
	 * 
	 * Subcategories from a category
	 * http://api.remix.bestbuy.com/v1/categories(id
	 * =abcat0010000)?format=json&apiKey
	 * =w7yw448r9w5d5sjmr4mrqa9a&show=subCategories
	 * 
	 * Pagination
	 * http://api.remix.bestbuy.com/v1/products(type=Movie)?format=json
	 * &show=sku,name,salePrice&pageSize=3&page=1000&apiKey=YourAPIKey
	 * 
	 * Product
	 * http://api.remix.bestbuy.com/v1/products(sku=1752378)?show=all&format
	 * =json&apiKey=w7yw448r9w5d5sjmr4mrqa9a Categories:
	 * 
	 * ID "abcat0101000" NAME "TVs"
	 * 
	 * ID "abcat0102000" NAME "Blu-ray & DVD Players"
	 * 
	 * ID "abcat0102003" NAME "Blu-ray Players"
	 */

	public static final String GET = "GET";

	public static final String POST = "POST";

	HttpURLConnection conn = null;

	public void openConnection(String urlService, String metodoHttp,
			Hashtable<String, String> params) throws ServiceException {

		try {
			if (conn != null) {
				closeConnection();
			}

			URL url = new URL(urlService);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(metodoHttp);
			conn.setRequestProperty("Accept", "application/json");

			if (metodoHttp == POST && params != null && params.size() > 0) {
				conn.setDoInput(true);
				OutputStream os = conn.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, "UTF-8"));
				writer.write(getPostParamString(params));
				writer.flush();
				writer.close();
				os.close();
			}

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public void closeConnection() {
		if (conn != null) {
			conn.disconnect();
		}
		conn = null;
	}

	public String getMessage() throws Exception {
		if (conn != null) {
			return org.apache.commons.io.IOUtils.toString(new BufferedReader(
					new InputStreamReader(conn.getInputStream())));
		}
		throw new Exception(
				"Nao foi possivel recuperar a mensagem. Connection nula. ");
	}

	public String get(String urlServico) throws Exception {
		openConnection(urlServico, GET, null);
		String value = getMessage();
		closeConnection();
		return value;
	}

	public String post(String urlServico, Hashtable<String, String> params)
			throws Exception {
		openConnection(urlServico, POST, params);
		String value = getMessage();
		closeConnection();
		return value;
	}

	private String getPostParamString(Hashtable<String, String> params) {
		if (params.size() == 0) {
			return "";
		}

		StringBuffer buf = new StringBuffer();
		Enumeration<String> keys = params.keys();
		while (keys.hasMoreElements()) {
			buf.append(buf.length() == 0 ? "" : "&");
			String key = keys.nextElement();
			buf.append(key).append("=").append(params.get(key));
		}
		return buf.toString();
	}

}
