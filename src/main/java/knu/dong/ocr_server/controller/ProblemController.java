package knu.dong.ocr_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {

	@GetMapping
	public String test(int studentGrade, int problemGrade) {
		return "대학교";
	}
}
