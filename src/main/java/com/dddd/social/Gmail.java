package com.dddd.social;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


import com.dddd.model.GmailProfile;
import com.dddd.model.GmailToken;

public class Gmail {

	
	private static final Logger logger = Logger.getLogger(Gmail.class);
	private static String sScope = "profile email";
	private static String sGMAIL_CLIENT_ID = "42686649494-nkseiu7fkesvpc5on2u21h0v1majcnvf.apps.googleusercontent.com";
	private static String sGMAIL_SECRET_KEY = "JrvA9_UbgVTpb4_lKdht2vPj";
	private static String sGMAIL_REDIRECT_URL = "/postGoogle";
	private static String sGMAIL_URL = "https://accounts.google.com/o/oauth2/v2/auth?client_id=%s&redirect_uri=%s&state=%s&"
			+ "response_type=code&scope=%s&approval_prompt=force&access_type=offline";

	//THIS is post url
	private static String sGmail_ACCESS_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";

	// Access token in header
	private static String sGmail_GET_USER_URL = "https://www.googleapis.com/plus/v1/people/me";

	public static String getGmailURl(String appURL, String stateCode) {

		appURL = appURL + sGMAIL_REDIRECT_URL;

		return new String().format(sGMAIL_URL, new String[] { sGMAIL_CLIENT_ID, appURL, stateCode, sScope });
	}

	public static GmailProfile authUser(String authCode, String appURL) {

		appURL = appURL + sGMAIL_REDIRECT_URL;
		/*System.out.println("========" + appURL);*/
		String accToken = getAccessToken(authCode, appURL);

		return getUserProfile(accToken);

	}

	private static GmailProfile getUserProfile(String accToken) {

		String sccProfileUrl = sGmail_GET_USER_URL;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(sccProfileUrl);	
		//System.out.println(accToken);

		String headerAuth = "Bearer " + accToken;

		Response response = target.request().header("Authorization", headerAuth).accept(MediaType.APPLICATION_JSON)
				.get();

		GmailProfile profile = response.readEntity(GmailProfile.class);
		logger.warn(profile);
		
		return profile;
	}

	private static String getAccessToken(String authCode, String appURL) {
		
		/*System.out.println(authCode + "==== " + appURL);*/
		logger.warn("Inside gmail FOR GET GMAIL getAccess token");
		String accTokenUrl = sGmail_ACCESS_TOKEN_URL;

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(accTokenUrl);
		Form f = new Form();
		f.param("client_id", sGMAIL_CLIENT_ID);
		f.param("client_secret", sGMAIL_SECRET_KEY);
		f.param("redirect_uri", appURL);
		f.param("code", authCode);
		f.param("grant_type", "authorization_code");

		Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(f));
		 GmailToken token = response.readEntity(GmailToken.class);
		 System.out.println(token.toString());
		/*String gmailToken = response.readEntity(String.class);
		System.out.println(gmailToken);*/
		
		
		return token.getAccess_token();
		
	}

}
