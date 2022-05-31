package com.RGR.Auction.controllers;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.RGR.Auction.Service.File.FileUploadDAO;
import com.RGR.Auction.models.UploadFile;
 
@Controller
public class FileController {
    @Autowired
    private FileUploadDAO fileUploadDao;
    
    @GetMapping("/file")
    public String showUploadForm(Model model) {
    	
        return "Upload";
    }
    @SuppressWarnings("null") 
    @PostMapping("/file")
    public String handleFileUpload(@RequestParam CommonsMultipartFile[] fileUpload) {
          
    	if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                  
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                 
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                fileUploadDao.save(uploadFile);               
            }
        }
  
        return "Success";
    }  
}