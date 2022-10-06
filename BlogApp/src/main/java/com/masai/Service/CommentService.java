package com.masai.Service;

import java.util.List;

import com.masai.Exception.CommentNotFound;
import com.masai.Exception.PostNotFound;
import com.masai.Model.Comment;

public interface CommentService {
	
	public List<Comment> getAllCommentsByPostID(Long Pid,int pageN,int pageS) throws CommentNotFound;
	
	public Comment addComment(Comment comment,Long Pid)throws PostNotFound;
	
	public Comment getCommentByCid(Long Pid,Long Cid)throws CommentNotFound, PostNotFound;
	
	public Comment updateCommentByCid(Long Pid,Long Cid,Comment comment)throws CommentNotFound, PostNotFound;
	
	public boolean deleteComment(Long Pid,Long Cid)throws CommentNotFound, PostNotFound;
	
	
	

}
