package co.nilin.mvc.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PICTURES")
public class Picture {

	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long PicId;
	@Column
	private byte[] Image;
	@Column
	private String Comment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AlbumId",nullable=false)
	private Album PicAlbum;

	
	
	public Picture() {
		super();
	}

	public Long getPicId() {
		return PicId;
	}

	public void setPicId(Long picId) {
		PicId = picId;
	}

	public byte[] getImage() {
		return Image;
	}

	public void setImage(byte[] image) {
		Image = image;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Album getPicAlbum() {
		return PicAlbum;
	}

	public void setPicAlbum(Album picAlbum) {
		PicAlbum = picAlbum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Comment == null) ? 0 : Comment.hashCode());
		result = prime * result + ((Image == null) ? 0 : Image.hashCode());
		result = prime * result + ((PicAlbum == null) ? 0 : PicAlbum.hashCode());
		result = prime * result + ((PicId == null) ? 0 : PicId.hashCode());
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
		Picture other = (Picture) obj;
		if (Comment == null) {
			if (other.Comment != null)
				return false;
		} else if (!Comment.equals(other.Comment))
			return false;
		if (Image == null) {
			if (other.Image != null)
				return false;
		} else if (!Image.equals(other.Image))
			return false;
		if (PicAlbum == null) {
			if (other.PicAlbum != null)
				return false;
		} else if (!PicAlbum.equals(other.PicAlbum))
			return false;
		if (PicId == null) {
			if (other.PicId != null)
				return false;
		} else if (!PicId.equals(other.PicId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Picture [PicId=" + PicId + ", Image=" + Image + ", Comment=" + Comment + ", PicAlbum=" + PicAlbum
				+ "]";
	}
	
	
}
