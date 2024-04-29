package com.example.myhouse24admin.validators.fileValidator.aboutPage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class ImagesExtensionValidator implements ConstraintValidator<ImagesExtension, List<MultipartFile>> {
    private final Logger logger = LogManager.getLogger(ImagesExtensionValidator.class);
    @Override
    public boolean isValid(List<MultipartFile> multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        if(multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                Tika tika = new Tika();
                String detectedType = null;
                try {
                    detectedType = tika.detect(multipartFile.getBytes());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
                if (detectedType != null && (!detectedType.equals("image/png")
                        || !detectedType.equals("image/jpg")
                        || !detectedType.equals("image/jpeg"))) {
                    return false;
                }
            }
        }
        return true;
    }
}
