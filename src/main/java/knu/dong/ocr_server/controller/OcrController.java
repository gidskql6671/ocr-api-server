package knu.dong.ocr_server.controller;

import knu.dong.ocr_server.dto.OcrResponse;
import knu.dong.ocr_server.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ocr")
@RequiredArgsConstructor
@Slf4j
public class OcrController {

	private final OcrService ocrService;

	@PostMapping(params = {"correctText"})
	public OcrResponse ocr(@RequestParam("image") MultipartFile image, @RequestParam("correctText") String correctText) {
		log.info("correctText = " + correctText);
		return ocrService.ocr(image, correctText);
	}

	@PostMapping
	public OcrResponse ocr(@RequestParam("image") MultipartFile image) {
		return ocrService.ocr(image);
	}


}