package edu.whu.recirco.controller;

import com.alibaba.fastjson.JSONObject;
import edu.whu.recirco.exception.TodoException;
import edu.whu.recirco.service.MinioService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/minio")
public class MinioController {
    @Autowired
    private MinioService minioService;
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.access-key}")
    private String minioAccessKey;
    @Value("${minio.secret-key}")
    private String minioSecretKey;
    @PostMapping("/upload")
    public ResponseEntity<JSONObject> upload(MultipartFile file,String bucketName) {
        JSONObject result = new JSONObject();
        try {
            result = minioService.uploadFile(file, bucketName);
        } catch (Exception e) {
            result.put("code",TodoException.MINIO_ERROR);
            result.put("message","上传失败");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(String bucketName, String fileName) throws IOException {
        try {
            boolean fileExists = minioService.fileExists(bucketName, fileName);
            if (!fileExists) {
                return ResponseEntity.badRequest().build();
            }
            InputStream inputStream = minioService.downloadFile(bucketName, fileName);
            // 创建临时文件
            File tempFile = File.createTempFile("temp", null);
            tempFile.deleteOnExit();
            // 将输入流写入临时文件
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // 创建文件资源
            Resource fileResource = new FileSystemResource(tempFile);
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(tempFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileResource);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | TodoException | InternalException | XmlParserException | InvalidResponseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFile(String bucketName,String fileName) {
        try {
            boolean fileExists = minioService.fileExists(bucketName, fileName);
            if (!fileExists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("文件不存在");
            }
            minioService.deleteFile(bucketName, fileName);
            return ResponseEntity.ok("文件删除成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件删除失败：" + e.getMessage());
        }
    }
}
