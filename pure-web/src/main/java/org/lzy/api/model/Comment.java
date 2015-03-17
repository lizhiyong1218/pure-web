package org.lzy.api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 评论
 * @author lzy
 *
 */
@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
	public static final Integer NEW = 1;     	//新增评论
	public static final Integer PASS = 10; 		//已经通过的评论
	private static final long serialVersionUID = 3537746914640003321L;

	private Long id;
	/*评论内容*/
	private String content;
	/*评论时间*/
	private Date crtime;
	/*评论人名*/
	private String cruser;
	/*状态*/
	private Integer status=NEW;//评论状态,初始化为1,审核通过为10
	/*所属视频*/
	private Media media; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentid")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	
	@Column(length = 50)
	public String getCruser() {
		return cruser;
	}
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}
	
	@Column 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JoinColumn(name = "mediaid", referencedColumnName = "mediaid")
	@ManyToOne
	@Basic(fetch = FetchType.LAZY)
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
}
