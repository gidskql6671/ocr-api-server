package knu.dong.ocr_server.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class OcrAPI {
	private static final RestTemplate restTemplate = new RestTemplate();

	public static String ocr(String imageAbsolutePath) {
		String url = "http://localhost:8000/ocr";

		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("path", imageAbsolutePath)
				.build(true);

		HttpEntity<String> response;
		try {
			response = restTemplate.getForEntity(uriBuilder.toUri(), String.class);

			String body = response.getBody();

			log.debug(body);

			return body;
		} catch (RestClientException e) {
			log.error(e.getMessage());

			return "error";
		}

	}
}