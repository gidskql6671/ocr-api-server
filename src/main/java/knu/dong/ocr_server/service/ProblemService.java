package knu.dong.ocr_server.service;

import knu.dong.ocr_server.domain.Problem;
import knu.dong.ocr_server.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemService {

	private final ProblemRepository problemRepository;

	public String findProblem(int studentGrade, int problemGrade) {
		List<Problem> list = problemRepository.findAllByStudentGradeAndProblemGrade(studentGrade, problemGrade);
		if (list.isEmpty()) {
			return "데이터가 없습니다.";
		}

		Random random = new Random();
		int randomIndex = random.nextInt(list.size());

		return list.get(randomIndex).getContent();
	}
}
