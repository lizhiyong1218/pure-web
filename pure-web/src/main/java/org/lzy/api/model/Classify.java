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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 类型
 * @author lzy
 *
 */
@Entity
@Table(name = "Classify")
public class Classify implements Serializable {

	private static final long serialVersionUID = -6589353838162969136L;
	/*ID号*/
	private Long id;
	/*类型名称*/
	private String name;
	/*类型描述*/
	private String desc;
	/*创建时间*/
	private Date crtime;
	/*创建者*/
	private String cruser;
	/*类型视频*/
	private Collection<Media> medias;
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="classifyid")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length=50,name="classifyname")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=200,name="classifydedc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	
	@OneToMany(cascade = {CascadeType.REFRESH} , mappedBy = "classify",fetch=FetchType.LAZY)
	public Collection<Media> getMedias() {
		return medias;
	}
	public void setMedias(Collection<Media> medias) {
		this.medias = medias;
	}
}
