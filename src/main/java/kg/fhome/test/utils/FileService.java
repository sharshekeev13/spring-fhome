package kg.fhome.test.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private String foundFile;

    public InputStream getResources(String fileCode) throws IOException {
        Path dirPath = Paths.get("photos");
        String fileName = getFileName(fileCode);
        if(fileName == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String fullPath = dirPath+ File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

    public String getFileName(String fileCode) throws IOException {
        Path dirPath = Paths.get("photos");
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file.getFileName().toString();
                return;
            }
        });
        if (foundFile != null) {
            return foundFile;
        }
        return null;
    }

}
