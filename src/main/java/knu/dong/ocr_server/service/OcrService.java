package knu.dong.ocr_server.service;

import knu.dong.ocr_server.api.OcrAPI;
import knu.dong.ocr_server.domain.Picture;
import knu.dong.ocr_server.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OcrService {

	@Value("${file.dir}")
	private String fileDir;
	private final PictureRepository pictureRepository;


	public String ocr(MultipartFile files) {
		String filepath = saveImage(files);

		if (filepath == null) {
			return null;
		}

		log.info("filepath : " + filepath);
		String result = OcrAPI.ocr(filepath);
		log.info("result : " + result);

		return result;
	}

	private String saveImage(MultipartFile files) {
		if (files.isEmpty()) {
			return "";
		}

		String uuid = UUID.randomUUID().toString();
		String ext = ".jpg";
		String filename = uuid + ext;
		String filepath = fileDir + filename;

		Picture picture = new Picture(filepath);


		try {
			files.transferTo(new File(filepath));
		} catch (IOException e) {
			log.error(e.getMessage());
			return null;
		}

		Picture savedPicture = pictureRepository.save(picture);

		return savedPicture.getFilepath();
	}
}