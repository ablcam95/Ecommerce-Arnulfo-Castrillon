package com.acsolutions.arnulfocastrillon.acsolutions.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class UploadFile {

    private final String UPLOAD_DIR = "uploads/";
    private final String IMG_DEFAULT = "uploads/default.jpg";
    private final String URL = "http://localhost:8085/images/";

    public String upload(MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();

            if (originalFilename == null || originalFilename.trim().isEmpty()) {
                return URL + IMG_DEFAULT;
            }

            // Crear carpeta si no existe
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Crear nombre único
            String fileExtension = getFileExtension(originalFilename);
            String newFilename = UUID.randomUUID().toString() + (fileExtension.isEmpty() ? "" : "." + fileExtension);

            Path path = Paths.get(UPLOAD_DIR + newFilename);
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes);

            return URL + newFilename;
        }
        return URL + IMG_DEFAULT;
    }

    public void delete(String nameFile) {
        if (nameFile != null && !nameFile.trim().isEmpty() && !nameFile.equals(IMG_DEFAULT)) {
            File file = new File(UPLOAD_DIR + nameFile);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            return "";
        }
        return filename.substring(lastIndexOfDot + 1);
    }
}
