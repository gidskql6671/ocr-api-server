package knu.dong.ocr_server.repository;

import knu.dong.ocr_server.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

	List<Problem> findAllByStudentGradeAndProblemGrade(Integer studentGrade, Integer problemGrade);
}
