package com.RGR.Auction.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RGR.Auction.models.UploadFile;

@Repository
public interface FileRepository  extends CrudRepository<UploadFile, Integer>{


}
