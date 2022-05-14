package com.reviews_system.service.impl;

import com.reviews_system.dao.CommentDao;
import com.reviews_system.domain.Comment;
import com.reviews_system.service.CommentService;
import entity.PageResult;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> list() {
        List<Comment>commentList=commentDao.findAll();
        return commentList;
    }

    @Override
    public Integer save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void delById(int comment_id) {
        commentDao.delById(comment_id);
    }

    @Override
    public Comment selectById(int comment_id) {
       Comment comment =  commentDao.selectById(comment_id);
        return comment;
    }

    @Override
    public int updateComment(Comment comment) {
        int i=commentDao.updateComment(comment);
        return i;
    }

    @Override
    public PageResult search(Comment comment, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<Comment> selectByName(String commenr_content) {
        List<Comment>commentList=commentDao.selectByName(commenr_content);
        return commentList;
    }

    @Override
    public int delByIds(int[] ids) {
        int i=commentDao.delByIds(ids);
        return i;
    }
}
