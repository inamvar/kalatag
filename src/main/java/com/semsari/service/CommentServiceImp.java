package com.semsari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semsari.dao.CommentDao;
import com.semsari.domain.Comment;
import com.semsari.domain.Item;

@Service
public class CommentServiceImp extends CRUDServiceImp<Comment> implements
		CommentService {

	@Autowired
	CommentDao commentDao;

	@Override
	@Transactional
	public List<Comment> findByDeal(Item item, boolean accepted) {
		return commentDao.findByDeal(item, accepted);
	}

	@Override
	@Transactional
	public int changeAccept(int id, boolean accept) {
		return commentDao.changeAccept(id, accept);
	}

	@Override
	@Transactional
	public List<Comment> findAll(String sort, String asc) {
		return commentDao.findAll(sort, asc);
	}

	@Override
	@Transactional
	public List<Comment> findAccepted(boolean accepted) {
		return commentDao.findAccepted(accepted);
	}

	@Override
	@Transactional
	public void acceptAllComments() {
		commentDao.acceptAllComments();
		
	}

}
