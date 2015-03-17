package org.lzy.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
 * 栏目
 * @author lzy
 *
 */
@Entity
@Table(name = "Channel")
public class Channel implements Serializable {

	private static final long serialVersionUID = 7238738106321716441L;
	
	private Long id;
	/*唯一名字*/
	private String name;
	/*显示名字*/
	private String showname;
	/*栏目logo */
	private String chnllogo;
	/*栏目描述 */	 
	private String desc;
	/*创建者 */ 
	private String cruser;
	/*创建时间 */
	private Date crtime;
	/*父栏目 */
	private Channel parent;
	/*子栏目 */
	private Collection<Channel> children;
	/*栏目视频 */
	private Collection<Media> medias;
	 
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="channelid")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length=50,name="chnlname")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=50)
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	
	@Column(length=50)
	public String getChnllogo() {
		return chnllogo;
	}
	public void setChnllogo(String chnllogo) {
		this.chnllogo = chnllogo;
	}
	 
	@Column(length=200,name="chnldesc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	public Channel getParent() {
		return parent;
	}
	public void setParent(Channel parent) {
		this.parent = parent;
	}
	 
	@Column(length=50)
	public String getCruser() {
		return cruser;
	}
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}
	@Column
    @Temporal(TemporalType.TIMESTAMP)
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	
	@OneToMany(mappedBy = "parent",fetch = FetchType.LAZY,cascade={CascadeType.REMOVE})
	public Collection<Channel> getChildren() {
		return children;
	}
	public void setChildren(Collection<Channel> children) {
		this.children = children;
	}

	@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.REFRESH} , mappedBy = "channel",fetch=FetchType.LAZY)
	public Collection<Media> getMedias() {
		return medias;
	}
	public void setMedias(Collection<Media> medias) {
		this.medias = medias;
	}
	 
}
