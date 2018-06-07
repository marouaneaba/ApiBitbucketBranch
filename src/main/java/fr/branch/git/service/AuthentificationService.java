package fr.branch.git.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.branch.git.dto.User;

@Service
public class AuthentificationService {
	/**
	 * la constante LOG.
	 */
	final static Logger LOG = LoggerFactory.getLogger(AuthentificationService.class);

	/**
	 * url d'authentification
	 */
	private final String URL = "https://bitbucket.org/site/oauth2/access_token";

	private final String AUTHORISATION = "Authorization";
	private final String CONNECTION_FAILED = "Erreur connect to repositories";
	private final String AUTHETIFICATION_FAILED = "Erreur authentification";

	/**
	 * @param user : dto Utilisateur.
	 * @return token.
	 */
	public String getToken(User user) {
		OAuthClientRequest request = preparRequest(user.getUserName(), user.getPassword());

		byte[] unencodedConsumerAuth = (user.getKey() + ":" + user.getSecret()).getBytes(StandardCharsets.UTF_8);
		byte[] encodedConsumerAuth = Base64.getEncoder()
										   .encode(unencodedConsumerAuth);
		request.setHeader(AUTHORISATION, "Basic " + new String(encodedConsumerAuth, StandardCharsets.UTF_8));
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

		try {
			OAuthResourceResponse response = oAuthClient.resource(request, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
			return response.getBody();

		} catch (OAuthSystemException | OAuthProblemException e) {
			LOG.error("Erreur d'Authentification de Systeme : {}", e);
			throw new IllegalArgumentException(CONNECTION_FAILED);
		}

	}

	/**
	 * @param nameUser nom d'utilisateur.
	 * @param password password.
	 * @return request.
	 */
	public OAuthClientRequest preparRequest(String nameUser, String password) {
		try {
			return OAuthClientRequest.tokenLocation(URL)
									 .setGrantType(GrantType.PASSWORD)
									 .setUsername(nameUser)
									 .setPassword(password)
									 .buildBodyMessage();
		} catch (OAuthSystemException e) {
			LOG.error("Erreur d'Authentification de Systeme : {}", e);
			throw new IllegalArgumentException(AUTHETIFICATION_FAILED);
		}
	}

}
