package com.tledu.tt.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *  我的日程
 * @author 作者：xiaokeai
 * @Date 创建时间：2020年5月7日 下午10:34:06
 */
public class MyMessage {
	private int id;
	private String title;
	/**
	 * 会议类型
	 */
	private Notice notice;
	private String content;
	/**
	 * 创建者
	 */

	private User user;
	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Date getShow_time() {
		return show_time;
	}

	public void setShow_time(Date show_time) {
		this.show_time = show_time;
	}

	/**
	 * 发布时间,自动设置
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date show_time;

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

	public MyMessage( String title, Notice notice, String content,
			User user, Date show_time) {
		super();
		this.title = title;
		this.notice = notice;
		this.content = content;
		this.user = user;
		this.show_time = show_time;
	}

	public MyMessage() {
		super();
	}

	@Override
	public String toString() {
		return "MyMessage [id=" + id + ", title=" + title + ", notice="
				+ notice + ", content=" + content + ", user=" + user
				+ ", show_time=" + show_time + "]";
	}

}
