package com.RGR.Auction.Service.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.UploadFile;
import com.RGR.Auction.repositories.FileRepository;

@Service
public class FileUploadDAOImpl implements FileUploadDAO {
  
	@Autowired
   private FileRepository fileRepository;
     
	@Override
   public void save(UploadFile uploadFile) {
       fileRepository.save(uploadFile);
   }
}