package fr.branch.git.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BranchService {

	private static final Logger LOG = LoggerFactory.getLogger(BranchService.class);
	protected static final String BRANCH_ERREUR = "Erreur récupérer les branchs d'un controller";

	/**
	 * @param url           url.
	 * @param requestEntity request.
	 * @param httpMethod    method webService ( GET , POST , DELETE ).
	 * @return response de webService.
	 */
	public String getRessource(String url, HttpEntity requestEntity, HttpMethod httpMethod) {

		RestTemplate restTemplate = new RestTemplate();

		try {

			ResponseEntity<String> responce = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
			return responce.getBody();

		} catch (HttpClientErrorException e) {
			LOG.error("Erreur Get Ressource : {}", e);
			throw new IllegalArgumentException(BRANCH_ERREUR);
		}
	}
}
