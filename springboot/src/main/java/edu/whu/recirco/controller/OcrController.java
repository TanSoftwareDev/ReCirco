//package edu.whu.recirco.controller;
//
//import com.alibaba.fastjson.JSONObject;
//
//import edu.whu.recirco.service.MinioService;
//import edu.whu.recirco.service.OcrService;
//import lombok.AllArgsConstructor;
//import net.sourceforge.tess4j.TesseractException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("/ocr")
//@AllArgsConstructor
//public class OcrController {
//    @Autowired
//    private final OcrService ocrService;
//    @Autowired
//    private MinioService minioService;
//    //识别图片
//    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> recognizeImage(MultipartFile file) throws TesseractException, IOException {
//        // 调用OcrService中的方法进行文字识别
//        String result;
//        try {
//            JSONObject uploadFile = minioService.uploadFile(file,"photo");
//            String fileName = String.valueOf(uploadFile.get("fileName"));
//            // 获取输入流
//            InputStream inputStream = minioService.downloadFile("photo", fileName);
//            // 构建MultipartFile对象
//            MultipartFile multipartFile = new MockMultipartFile(fileName, inputStream);
//            // 设置文件内容长度和类型
//            multipartFile = new MockMultipartFile(multipartFile.getName(),
//                    multipartFile.getOriginalFilename(), multipartFile.getContentType(),
//                    multipartFile.getBytes());
//            //识别图片结果并进行修改
//            result=ocrService.recognizeImg(multipartFile);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(result);
//    }
//
//
//}
