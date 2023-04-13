package knu.dong.ocr_server.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OcrResponse {
	@JsonProperty(value = "answer_percent")
	private double answerPercent;
	@JsonProperty(value = "correct_string")
	private String correctString;
	@JsonProperty(value = "origin_string")
	private String originString;
}
