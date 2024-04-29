package com.example.myhouse24admin.validators.fileValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class BlockImageExtensionValidator implements ConstraintValidator<BlockImageExtension, Object> {
    private final Logger logger = LogManager.getLogger(ImageExtensionValidator.class);

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        MultipartFile multipartFile = (MultipartFile) new BeanWrapperImpl(o).getPropertyValue("image");
        if (!multipartFile.isEmpty()) {
            Tika tika = new Tika();
            String detectedType = null;
            try {
                detectedType = tika.detect(multipartFile.getBytes());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            System.out.println(detectedType);
            if (detectedType != null) {
                return detectedType.equals("image/png")
                        || detectedType.equals("image/jpg")
                        || detectedType.equals("image/jpeg");
            }
        }
        return true;
    }
}
