package com.semsari.dao;

import java.util.List;

import com.semsari.domain.Comment;
import com.semsari.domain.Item;

public interface CommentDao extends GenericDao<Comment>{

	List<Comment> findByDeal(Item item, boolean accepted);
	List<Comment> findAll(String sort,String asc);
	List<Comment> findAccepted(boolean accepted);
	void acceptAllComments();
	int changeAccept(int id, boolean accept);
}
