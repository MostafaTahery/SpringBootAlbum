package co.nilin.mvc.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALBUMS")
public class Album {
	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long AlbumId;
	
	@Column
	private String Name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id",nullable=false)
	private User Creator;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="PicAlbum")
	private List<Picture> pictures;
	
	public Album() {
		super();
	}


	public Long getAlbumId() {
		return AlbumId;
	}


	public void setAlbumId(Long albumId) {
		AlbumId = albumId;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public User getCreator() {
		return Creator;
	}


	public void setCreator(User user) {
		this.Creator = user;
	}


	@Override
	public String toString() {
		return "Album [AlbumId=" + AlbumId + ", Name=" + Name + ", Creator=" + Creator + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AlbumId == null) ? 0 : AlbumId.hashCode());
		result = prime * result + ((Creator == null) ? 0 : Creator.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (AlbumId == null) {
			if (other.AlbumId != null)
				return false;
		} else if (!AlbumId.equals(other.AlbumId))
			return false;
		if (Creator == null) {
			if (other.Creator != null)
				return false;
		} else if (!Creator.equals(other.Creator))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}
	
	

}
