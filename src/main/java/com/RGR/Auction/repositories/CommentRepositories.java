package com.RGR.Auction.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RGR.Auction.models.Comment;

@Repository
public interface CommentRepositories extends CrudRepository<Comment,Integer> {
	
    Comment findById(Long id);
    List <Comment> findAllByOrderByIdDesc();
}
