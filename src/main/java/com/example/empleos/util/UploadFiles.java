package com.example.empleos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFiles {
    public static String saveFile(MultipartFile multipartFile, String path){
        try {
            String originalName = multipartFile.getOriginalFilename();
            originalName = originalName.replaceAll(" ","_");
            File imageFile = new File(path + randomAlphaNumeric(8) + originalName);
            // Guardamos el fichero en el HDD
            multipartFile.transferTo(imageFile);
            return originalName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Metodo para devolver caracteres alfanumericos
     *
     * @return
     */
    private static String randomAlphaNumeric(int size){
        String CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        StringBuilder  builder = new StringBuilder();
        while(size-- > 0){
            int character = (int) (Math.random() * CHARACTERS.length());
            builder.append(CHARACTERS.charAt(character));
        }
        builder.append("_");
        return builder.toString();
    }
}
