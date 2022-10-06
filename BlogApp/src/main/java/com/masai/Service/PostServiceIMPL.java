package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Exception.PostNotFound;
import com.masai.Model.Post;
import com.masai.Repository.PostDAO;

@Service
public class PostServiceIMPL implements PostService{
	
@Autowired
PostDAO pdao;

	
	

	@Override
	public Post getPostById(Long id) throws PostNotFound {
		Optional<Post> opt=pdao.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
			
		
	 throw new PostNotFound("Post not present with the id: "+id);
	}

	@Override
	public Post addPost(Post post) throws PostNotFound {
		
		return pdao.save(post);
	}

	@Override
	public Post updatePost(Post post,Long id) throws PostNotFound {
		Optional<Post> opt=pdao.findById(id);
		if(opt.isPresent())
		{
			Post exist=opt.get();
			if(post.getTitle()!=null)
				exist.setTitle(post.getTitle());
			if(post.getContent()!=null)
				exist.setContent(post.getContent());
			if(post.getDescription()!=null)
				exist.setDescription(post.getDescription());
			 return pdao.save(exist);
		}
		 throw new PostNotFound("Post not present with the id: "+id);
	}

	@Override
	public boolean deletePost(Long id) throws PostNotFound {
		Optional<Post> opt=pdao.findById(id);
		if(opt.isPresent())
		{
			pdao.delete(opt.get());
			return true;
		}
		 throw new PostNotFound("Post not present with the id: "+id);
	}

	@Override
	public List<Post> getAllPost(int pageN,int pageS) throws PostNotFound {
		List<Post> list=pdao.findAll();
		if(list.size()!=0)
		{
			Pageable paging = PageRequest.of(pageN, pageS);
	        Page<Post> pagedResult = pdao.findAll(paging);
	        return pagedResult.toList();
		}
		throw new PostNotFound("Post List is Empty");
	}

	

}
