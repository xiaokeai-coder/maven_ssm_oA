package com.tledu.tt.model;

import java.util.Date;
import com.tledu.tt.model.User;
/**
 *  我的日程
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午10:34:06
 */
public class MyNode {
	private int id;
	private String title;
	private String content;
	/**
	 * 创建者
	 */
	private User user;
	/**
	 * 创建时间,自动设置
	 */

	private Date create_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public MyNode(String title, String content, User user, Date create_date) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
		this.create_date = create_date;
	}

	public MyNode() {
		super();
	}

	@Override
	public String toString() {
		return "MyNode [id=" + id + ", title=" + title + ", content=" + content
				+ ", user=" + user + ", create_date=" + create_date + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
