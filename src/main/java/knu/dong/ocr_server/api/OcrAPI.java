package knu.dong.ocr_server.api;

import knu.dong.ocr_server.dto.OcrResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class OcrAPI {
	private static final RestTemplate restTemplate = new RestTemplate();

	public static OcrResponse ocr(String imageAbsolutePath) {
		String url = "http://localhost:8000/ocr";

		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("path", imageAbsolutePath)
				.build(true);

		HttpEntity<OcrResponse> response;
		try {
			response = restTemplate.getForEntity(uriBuilder.toUri(), OcrResponse.class);

			return response.getBody();
		} catch (RestClientException e) {
			log.error(e.getMessage());

			return null;
		}

	}
}