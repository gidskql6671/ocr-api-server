package knu.dong.ocr_server.repository;

import knu.dong.ocr_server.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}