package knu.dong.ocr_server.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import knu.dong.ocr_server.api.OcrAPI;
import knu.dong.ocr_server.domain.Picture;
import knu.dong.ocr_server.dto.OcrResponse;
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

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private final PictureRepository pictureRepository;
	private final AmazonS3Client amazonS3Client;


	public OcrResponse ocr(MultipartFile files) {
		String filepath = saveImage(files);

		if (filepath == null) {
			return null;
		}

		log.info("filepath : " + filepath);
		OcrResponse result = OcrAPI.ocr(filepath);

		if (result == null) {
			log.info("왜 null?");
			return null;
		}

		log.info(result.toString());

		return result;
	}

	public OcrResponse ocr(MultipartFile files, String correctText) {
		String filepath = saveImage(files);

		if (filepath == null) {
			return null;
		}

		log.info("filepath : " + filepath);
		OcrResponse result = OcrAPI.ocr(filepath, correctText);

		if (result == null) {
			log.info("왜 null?");
			return null;
		}

		log.info(result.toString());

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

	private String saveImageToS3(MultipartFile files) {
		if (files.isEmpty()) {
			return "";
		}

		try {
			String uuid = UUID.randomUUID().toString();
			String ext = ".jpg";
			String filename = uuid + ext;
			String fileUrl= "https://" + bucket + "/test" + filename;

			ObjectMetadata metadata= new ObjectMetadata();
			metadata.setContentType(files.getContentType());
			metadata.setContentLength(files.getSize());

			amazonS3Client.putObject(bucket, filename, files.getInputStream(),metadata);
			pictureRepository.save(new Picture(fileUrl));

			return fileUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}