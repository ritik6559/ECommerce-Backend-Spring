package com.ritik.dreamshop.service.image;

import com.ritik.dreamshop.dto.image.ImageDto;
import com.ritik.dreamshop.model.image.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);

}
