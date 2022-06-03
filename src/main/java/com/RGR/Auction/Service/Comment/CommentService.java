package com.RGR.Auction.Service.Comment;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Comment;
@Service
public interface CommentService {
	
	List<Comment> getAll();
	Comment getById(Long id) throws NotFoundException;
	List<Comment> getAllByIdAuction(int id_Auction);

}
