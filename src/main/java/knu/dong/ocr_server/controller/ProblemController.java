package knu.dong.ocr_server.controller;

import knu.dong.ocr_server.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {

	private final ProblemService problemService;

	@GetMapping
	public String test(int studentGrade, int problemGrade) {
		return problemService.findProblem(studentGrade, problemGrade);
	}
}
