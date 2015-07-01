package br.com.aprando.recommendersystem.domain;

import java.util.List;

import com.restfb.types.Post;
import com.restfb.types.Post.Likes;
import com.restfb.types.StatusMessage;
import com.restfb.types.User;

public class UsuarioFacebook {

	private User user;

	private List<Post> posts;

	private List<Likes> likes;

	private List<StatusMessage> statuses;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public List<StatusMessage> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<StatusMessage> statuses) {
		this.statuses = statuses;
	}

}
