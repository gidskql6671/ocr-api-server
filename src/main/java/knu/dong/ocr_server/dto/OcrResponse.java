package knu.dong.ocr_server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OcrResponse {
	private final String resultString;
}