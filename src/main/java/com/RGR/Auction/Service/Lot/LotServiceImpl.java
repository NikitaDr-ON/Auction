package com.RGR.Auction.Service.Lot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepositories lotRepository;

    @Override
    public List<Lot> getAll() {
        return lotRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Lot getById(Long id) throws NotFoundException {
        Lot lot = lotRepository.findById(id);
        if (lot == null) {
            throw new NotFoundException();
        }
        return lot;
    }
}
