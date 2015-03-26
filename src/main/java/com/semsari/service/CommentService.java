package com.semsari.service;

import java.util.List;

import com.semsari.domain.Comment;
import com.semsari.domain.Item;

public interface CommentService extends CRUDService<Comment> {

	List<Comment> findByDeal(Item item, boolean accepted);
	int changeAccept(int id, boolean accept);
	List<Comment> findAll(String sort,String asc);
	List<Comment> findAccepted(boolean accepted);
	 void acceptAllComments();
}
