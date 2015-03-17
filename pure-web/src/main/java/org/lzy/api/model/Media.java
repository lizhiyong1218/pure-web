package org.lzy.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 视频
 * @author lzy
 *
 */
@Entity
@Table(name = "Media")
public class Media implements Serializable{

	private static final long serialVersionUID = 8571731349694922632L;
	
	public static final Integer NEW = 1;     	//新建视频
	public static final Integer CONVERT = 2; 	//已转视频
	public static final Integer ERROR = -2;  	//失败视频
	public static final Integer AUDIT = 10;  	//审核视频
	public static final Integer DELETE = -1; 	//删除视频
	
	
	private Long id;
	/*视频标题*/
	private String title;
	/*视频摘要*/
	private String desc;
	/*关键字*/
	private String keywords;
	/*状态*/
	private Integer status;
	/*关键字*/
	private Integer playcount=0; 
	/*创建时间*/
	private Date crtime;
	/*创建者*/
	private String cruser;
	/*评论平均分*/
	private Float grade = 0.0f; //评论平均分
	/*评论*/
	private Collection<Comment> comments;
	/*所属栏目*/
	private Channel channel;
	/*所属类型*/
	private Classify classify;
	 
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mediaid")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length=200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(length=400,name = "mediadesc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(length=50)
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	 
	@Column
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column
	public Integer getPlaycount() {
		return playcount;
	}
	public void setPlaycount(Integer playcount) {
		this.playcount = playcount;
	}
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	@Column(length=50)
	public String getCruser() {
		return cruser;
	}
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}
	@Column
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	@JoinColumn(name = "channelid",referencedColumnName = "channelid")
    @ManyToOne
    @Basic(fetch=FetchType.LAZY)
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.REFRESH} , mappedBy = "media",fetch=FetchType.LAZY)
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@JoinColumn(name = "classifyid",referencedColumnName = "classifyid")
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	public Classify getClassify() {
		return classify;
	}
	public void setClassify(Classify classify) {
		this.classify = classify;
	}
}
