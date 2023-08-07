package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    FileService fileService;

    @PostMapping("/add-file")
    public ResponseEntity<?> createFileRestaurant(@RequestParam MultipartFile file) {
        ResponseData responseData = new ResponseData();
        responseData.setData(fileService.saveFile(file));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
        Resource resource = fileService.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename\"" + resource.getFilename() + "\"").body(resource);
    }
}
