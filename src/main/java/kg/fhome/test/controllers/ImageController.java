package kg.fhome.test.controllers;

import jakarta.servlet.http.HttpServletResponse;
import kg.fhome.test.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/downloadFile")
public class ImageController {
    private final FileService fileService;
    public ImageController(){
        this.fileService = new FileService();
    }
    @GetMapping(value = "/{fileCode}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("fileCode") String fileCode, HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResources(fileCode);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
