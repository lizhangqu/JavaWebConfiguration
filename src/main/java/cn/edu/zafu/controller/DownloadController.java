package cn.edu.zafu.controller;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by lizhangqu on 16/1/24.
 */
@Controller
public class DownloadController {

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "dict.txt");

        String path = request.getSession().getServletContext().getRealPath("WEB-INF/classes/download");

        System.out.println(path);

        File file=new File(path,"/test.txt");

        System.out.println(file.getAbsolutePath());
        Source source = Okio.source(file);
        BufferedSource buffer = Okio.buffer(source);
        byte[] bytes = buffer.readByteArray();
        buffer.close();

        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
    }

}
