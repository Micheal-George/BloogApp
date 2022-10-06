package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.CommentNotFound;
import com.masai.Exception.PostNotFound;
import com.masai.Model.Comment;
import com.masai.Model.Post;
import com.masai.Repository.CommentDAO;
import com.masai.Repository.PostDAO;

@Service
public class CommentServiceIMPL implements CommentService {

	@Autowired
	CommentDAO cdao;

	@Autowired
	PostDAO pdao;
	
	@Override
	public List<Comment> getAllCommentsByPostID(Long Pid,int pageN,int pageS) throws CommentNotFound {
		
			List<Comment> list=cdao.findByPostId(Pid);
			 if(list.size()!=0)
			 {
				 Pageable paging = PageRequest.of(pageN, pageS);
			        Page<Comment> pagedResult = cdao.findAll(paging);
			        return pagedResult.toList();
			 }
		throw new CommentNotFound("No comments belong to the postId :"+Pid);
	}

	@Override
	public Comment addComment(Comment comment, Long Pid) throws PostNotFound {
		   Optional<Post> opt=pdao.findById(Pid);
		   if(opt.isPresent())
		   {comment.setPost(opt.get());
		  return cdao.save(comment);
		   }
		   
		   throw new PostNotFound("No post with the id");
	}

	@Override
	public Comment getCommentByCid(Long Pid, Long Cid) throws CommentNotFound, PostNotFound {
		Optional<Post> post=pdao.findById(Pid);
		if(post.isPresent())
		{
		Optional<Comment> opt=cdao.findById(Cid);
		if(opt.isPresent())
		{
			return opt.get();
		}
		throw new CommentNotFound("No comments belong to the postId :"+Cid);
		}
		throw new PostNotFound("No comments belong to the PostId :"+Pid);
	}

	@Override
	public Comment updateCommentByCid(Long Pid, Long Cid, Comment comment) throws CommentNotFound, PostNotFound {
		Optional<Post> post=pdao.findById(Pid);
		if(post.isPresent())
		{
		Optional<Comment> opt=cdao.findById(Cid);
		if(opt.isPresent())
		{  Comment exist=opt.get();
			if(comment.getName()!=null)
				exist.setName(comment.getName());
			if(comment.getEmail()!=null)
				exist.setEmail(comment.getEmail());
			if(comment.getBody()!=null)
				exist.setBody(comment.getBody());
			return cdao.save(exist);
		}
		throw new CommentNotFound("No comments belong to the postId :"+Cid);
		}
		throw new PostNotFound("No comments belong to the PostId :"+Pid);
	}

	@Override
	public boolean deleteComment(Long Pid, Long Cid) throws CommentNotFound, PostNotFound {
		Optional<Post> post=pdao.findById(Pid);
		if(post.isPresent())
		{
		Optional<Comment> opt=cdao.findById(Cid);
		if(opt.isPresent())
		{  cdao.deleteById(Cid);
			return true;
		
			
		}
		throw new CommentNotFound("No comments belong to the CommentId :"+Cid);
		}
		throw new PostNotFound("No comments belong to the PostId :"+Pid);
	}
	
	

}
