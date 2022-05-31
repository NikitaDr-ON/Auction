package com.RGR.Auction.Service.File;

import org.springframework.stereotype.Service;

import com.RGR.Auction.models.UploadFile;

@Service
public interface FileUploadDAO {
    void save(UploadFile uploadFile);
}
