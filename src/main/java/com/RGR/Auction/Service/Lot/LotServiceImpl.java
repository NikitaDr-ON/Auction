package com.RGR.Auction.Service.Lot;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Role;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LotServiceImpl implements LotService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private LotRepositories lotRepository;

    @Autowired
    private Repositories repositories;

    @Override
    public List<Lot> getAll() {
        return lotRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Lot getById(int id) throws NotFoundException {
        Lot lot = lotRepository.findById(id);
        if (lot == null) {
            throw new NotFoundException();
        }
        return lot;
    }

    @Override
    public void saveLot(String product, int startCost, Long seller, String description, MultipartFile file, Long category) throws IOException {
        Lot newLot = new Lot();

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String res = uuidFile;
        file.transferTo(new File(uploadPath + "/" + res+".jpg"));

        newLot.setPhoto(res);
        newLot.setProduct(product);
        newLot.setStartCost(startCost);
        newLot.setSeller(seller);
        newLot.setDescription(description);
        newLot.setCategory(category);
        lotRepository.save(newLot);
    }


    @Override
    public boolean deleteById(int id) {
    	Lot lot = lotRepository.findById(id);
        if (lot == null) {
            return false;
        }
        else {
        	lotRepository.deleteById(id);
        	return true;
        }              
    }
    
    @Override
    public Lot update(int id, Lot lot) throws NotFoundException {
        if (!lotRepository.existsById(id)) {
            throw new NotFoundException();
        }
        Lot newLot = new Lot();
        newLot.setId(id);
        newLot.setProduct(lot.getProduct());
        newLot.setStartCost(lot.getStartCost());
        newLot.setDescription(lot.getDescription());
        newLot.setCategory(lot.getCategory());
       // newLot.setPhoto(lot.getPhoto());
        newLot.setSeller(lot.getSeller());
        return lotRepository.save(newLot);
    }

	@Override
	public void saveLot(Lot lot) {
        lotRepository.save(lot);
	}

    
}
