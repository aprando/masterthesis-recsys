package br.com.aprando.recommendersystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import br.com.aprando.recommendersystem.base.LoggedInFacebookClient;
import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.base.Utils;
import br.com.aprando.recommendersystem.domain.UsuarioFacebook;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.Insight;
import com.restfb.types.User;

@Service
public class SocialMidiaServiceImpl implements SocialMidiaService {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public UsuarioFacebook consultarUsuarioFacebook(String facebookId, String accessToken) throws ServiceException {
		UsuarioFacebook usuarioFacebook = new UsuarioFacebook();
		try {
			FacebookClient fc = new DefaultFacebookClient(accessToken);
			usuarioFacebook.setUser(fc.fetchObject(facebookId, User.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return usuarioFacebook;
	}
	
	@Override
	public UsuarioFacebook consultarUsuarioFacebook(String facebookId) throws ServiceException {
		UsuarioFacebook usuarioFacebook = new UsuarioFacebook();
		try {
			FacebookClient fc = new LoggedInFacebookClient(Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_APP_SECRET);
			usuarioFacebook.setUser(fc.fetchObject(facebookId, User.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return usuarioFacebook;
	}

	@Override
	public Connection<Insight> consultarFacebookInsights() throws ServiceException {
		FacebookClient facebookClient = new LoggedInFacebookClient(Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_APP_SECRET);
		Connection<Insight> insights = facebookClient.fetchConnection(Utils.FACEBOOK_APP_ID + "/insights", Insight.class);
		for (Insight insight : insights.getData())
			System.out.println(insight.getName());

		return insights;
	}

	
	public static void main(String[] args) {
		try {
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("p05WZVs4JivX4a0WSwFyMXXCo")
			  .setOAuthConsumerSecret("DghsY9Dxn2X8xAjdQKEvwBLtqsHNJabFz361pz2ZvRmAgXiPHB")
			  .setOAuthAccessToken("167813147-LwEOQAqO6RCnK0GfIEXNeVOng93QHkW1iFuVjBUV")
			  .setOAuthAccessTokenSecret("kpzp3quxTmVpSfWdgcyN5qbrPTmyoFArdvJeUC4Dfjtg1");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			
			final Paging paging = new Paging();
			paging.count(200); // max statuses you can request for this call
			List<Status> statuses = twitter.getUserTimeline("DaniloGentili", paging);
			int i = 1;
			
			for(Status status : statuses){
				System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(status.getCreatedAt()) + " :: "+ i++ + " :: "+status.getText());
			}
			
//			String facebookId = "654387371325300";
//			FacebookClient facebookClient = new LoggedInFacebookClient(Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_APP_SECRET);
//			
//			JsonParser parser = new JsonParser();
//			JsonElement usuarioElement = parser.parse(facebookClient.fetchObject(facebookId, JsonObject.class).toString());
//			com.google.gson.JsonObject usuarioObject = usuarioElement.getAsJsonObject();
//			
//			//usuarioObject.add("feed", parser.parse(getFacebookFeeds(facebookClient, facebookId)));
//			
//			
//			//"created_time"
//			//"status_type"
//			//"type" - lynk ou status
//			//    "application"- SE type = link
//			//        "name"
//			//   	     "id"
//			//"story"
//			//"id"
//			addJsonObject(facebookClient, "feed", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "statuses", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "events", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "family", facebookId, usuarioObject);
//			
//			//"id"
//			//"name"
//			//"category"
//			addJsonObject(facebookClient, "books", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "albums", facebookId, usuarioObject);
//			
//			// Igual feeds!
//			addJsonObject(facebookClient, "posts", facebookId, usuarioObject);
//			
//			//"created_time"
//			//"id"
//			//"name"
//			//"category"
//			addJsonObject(facebookClient, "likes", facebookId, usuarioObject);
//			
//			//"name"
//			//"id"
//			addJsonObject(facebookClient, "friends", facebookId, usuarioObject);
//			
//			// Likes de video games filtrados!
//			addJsonObject(facebookClient, "games", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "groups", facebookId, usuarioObject);
//
//			// Likes de video games filtrados!
//			addJsonObject(facebookClient, "television", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "tagged_places", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "tagged", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "picture", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "photos", facebookId, usuarioObject);
//
//			// Likes de video games filtrados!
//			addJsonObject(facebookClient, "music", facebookId, usuarioObject);
//			
//			//Lista de permissoes
//			addJsonObject(facebookClient, "permissions", facebookId, usuarioObject);
//			
//			// Likes de video games filtrados!
//			addJsonObject(facebookClient, "movies", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "links", facebookId, usuarioObject);
//			
//			//Nao veio!
//			addJsonObject(facebookClient, "interests", facebookId, usuarioObject);			
//			
//			System.out.println(usuarioObject.toString());
	
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	private static String getFacebookJson(FacebookClient facebookClient, String service, String facebookId){
		try{
			Date oneDayAgo = new Date(new Date().getTime() - 1000L * 60L * 60L * 24L );
			return facebookClient.fetchObject(facebookId + service, JsonObject.class, Parameter.with("since", oneDayAgo)).toString();
			//return facebookClient.fetchObject(facebookId + service, JsonObject.class).toString();
		}catch(Exception e){
			System.out.println("ERRO ao obter " + service + " do usuario " + facebookId);
			e.printStackTrace();
			return "";
		}
	}
	
	private static void addJsonObject(FacebookClient facebookClient, String fieldToAdd, String facebookId, com.google.gson.JsonObject root){
		JsonParser parser = new JsonParser();
		String json = getFacebookJson(facebookClient,"/" + fieldToAdd, facebookId);
		if(!"".equals(json)){
			System.out.println(fieldToAdd + "       " + json);
			JsonElement element = parser.parse(json);
			root.add(fieldToAdd, element.getAsJsonObject());
		}
	}
	
	private static String getFacebookFeeds(FacebookClient facebookClient, String facebookId){
		Date oneDayAgo = new Date(new Date().getTime() - 1000L * 60L * 60L * 24L * 2);	
		//?since={date.last30Days}&until={date.today}
		//https://graph.facebook.com/v1.0/678072642285644/feed?since={date.last30Days}&until={date.today}&format=json&access_token=704203256284579|g6QcCeeeZAYl2RNR7VoHZqewekw&limit=25
		return facebookClient.fetchObject(facebookId + "/feed", JsonObject.class, Parameter.with("since", oneDayAgo)).toString();
	}
	
	
	@Override
	public String consultarFacebookUserJson(String facebookId)
			throws ServiceException {
		try {
			FacebookClient facebookClient = new LoggedInFacebookClient(Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_APP_SECRET);
			
			JsonParser parser = new JsonParser();
			JsonElement usuarioElement = parser.parse(facebookClient.fetchObject(facebookId, JsonObject.class).toString());
			com.google.gson.JsonObject usuarioObject = usuarioElement.getAsJsonObject();
			
			addJsonObject(facebookClient, "feed", facebookId, usuarioObject);
			addJsonObject(facebookClient, "statuses", facebookId, usuarioObject);
			addJsonObject(facebookClient, "feed", facebookId, usuarioObject);
			addJsonObject(facebookClient, "events", facebookId, usuarioObject);
			addJsonObject(facebookClient, "family", facebookId, usuarioObject);
			addJsonObject(facebookClient, "books", facebookId, usuarioObject);
			addJsonObject(facebookClient, "albums", facebookId, usuarioObject);
			addJsonObject(facebookClient, "posts", facebookId, usuarioObject);
			addJsonObject(facebookClient, "likes", facebookId, usuarioObject);
			addJsonObject(facebookClient, "friends", facebookId, usuarioObject);
			addJsonObject(facebookClient, "games", facebookId, usuarioObject);
			addJsonObject(facebookClient, "groups", facebookId, usuarioObject);
			addJsonObject(facebookClient, "television", facebookId, usuarioObject);
			addJsonObject(facebookClient, "tagged_places", facebookId, usuarioObject);
			addJsonObject(facebookClient, "tagged", facebookId, usuarioObject);
			addJsonObject(facebookClient, "picture", facebookId, usuarioObject);
			addJsonObject(facebookClient, "photos", facebookId, usuarioObject);
			addJsonObject(facebookClient, "music", facebookId, usuarioObject);
			addJsonObject(facebookClient, "permissions", facebookId, usuarioObject);
			addJsonObject(facebookClient, "movies", facebookId, usuarioObject);
			addJsonObject(facebookClient, "links", facebookId, usuarioObject);
			addJsonObject(facebookClient, "interests", facebookId, usuarioObject);			
			
			return usuarioObject.toString();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	
	public String salvarDadosUsuarioFacebook(String facebookId) throws ServiceException {
		try{
			String json = this.consultarFacebookUserJson(facebookId);
			mongoTemplate.save(json, "facebook");
			return json;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String consultarFacebookUserJson(String facebookId, String accessToken) throws ServiceException {
		try {
			FacebookClient facebookClient = new LoggedInFacebookClient(accessToken);
			JsonObject user = facebookClient.fetchObject(facebookId, JsonObject.class);
			JsonObject myFeed = facebookClient.fetchObject(facebookId + "/feed", JsonObject.class);
			return user.toString() + " " + myFeed.toString();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String consultarFacebookLongLifeAccessToken(String shortLifeAccessToken) throws ServiceException {
		try{
			FacebookClient facebookClient = new DefaultFacebookClient();
			return facebookClient.obtainExtendedAccessToken(Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_APP_SECRET, shortLifeAccessToken).getAccessToken();
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}

}
