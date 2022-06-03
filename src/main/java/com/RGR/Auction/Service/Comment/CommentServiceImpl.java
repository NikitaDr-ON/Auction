package com.RGR.Auction.Service.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Comment;
import com.RGR.Auction.repositories.CommentRepositories;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepositories commentRepository;
	
	
	@SuppressWarnings("null")
	@Override
	public List<Comment> getAllByIdAuction(int id_Auction) {
		 List <Comment> list=commentRepository.findAllByOrderByIdDesc();
		 List <Comment> listComments = null;
		 for(Comment com:list) {
			 if(com.getAuction()==id_Auction)
			 {
				 listComments.add(com);
			 }
		 }
		 return listComments;
	}

	@Override
	public Comment getById(Long id) throws NotFoundException {
	     Comment comment = commentRepository.findById(id);
	     if (comment == null) {
	         throw new NotFoundException();
	         }
	     return comment;
	}

	@Override
	public List<Comment> getAll() {
		return commentRepository.findAllByOrderByIdDesc();
	}

}
