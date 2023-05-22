package knu.dong.ocr_server.controller;

import knu.dong.ocr_server.dto.OcrResponse;
import knu.dong.ocr_server.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ocr")
@RequiredArgsConstructor
public class OcrController {

	private final OcrService ocrService;

	@PostMapping
	public OcrResponse ocr(@RequestParam("image") MultipartFile image) {
		return ocrService.ocr(image);
	}

	@PostMapping("/{correctText}")
	public OcrResponse ocr(@RequestParam("image") MultipartFile image, @PathVariable("correctText") String correctText) {
		return ocrService.ocr(image, correctText);
	}

}