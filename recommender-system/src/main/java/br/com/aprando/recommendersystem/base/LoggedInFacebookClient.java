package br.com.aprando.recommendersystem.base;

import java.io.IOException;

import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient;
import com.restfb.WebRequestor;

public class LoggedInFacebookClient extends DefaultFacebookClient {

	public LoggedInFacebookClient(String appId, String appSecret) {
		super();
		AccessToken accessToken = this.obtainAppAccessToken(appId, appSecret);
		this.accessToken = accessToken.getAccessToken();
	}
	
	public LoggedInFacebookClient(String code) throws IOException {
		this.accessToken = getFacebookUserToken(code).getAccessToken();
	}
	
	private FacebookClient.AccessToken getFacebookUserToken(String code) throws IOException {
	    WebRequestor wr = new DefaultWebRequestor();
	    WebRequestor.Response accessTokenResponse = wr.executeGet(
	            "https://graph.facebook.com/oauth/access_token?client_id=" + Utils.FACEBOOK_APP_ID + "&redirect_uri=" + Utils.REDIRECT_URL_ACCESS_TOKEN
	            + "&client_secret=" + Utils.FACEBOOK_APP_SECRET + "&code=" + code);

	    return DefaultFacebookClient.AccessToken.fromQueryString(accessTokenResponse.getBody());
	}	
}
