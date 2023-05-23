package knu.dong.ocr_server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	private Integer studentGrade;

	private Integer problemGrade;

	public Problem(String content, Integer studentGrade, Integer problemGrade) {
		this.content = content;
		this.studentGrade = studentGrade;
		this.problemGrade = problemGrade;
	}
}
