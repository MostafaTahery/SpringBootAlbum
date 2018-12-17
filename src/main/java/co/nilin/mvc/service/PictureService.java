package co.nilin.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.Picture;
import co.nilin.mvc.repository.AlbumRepository;
import co.nilin.mvc.repository.PictureRepository;

@Component
public class PictureService implements IPictureService {

	@Autowired
	private PictureRepository picrepo;
	@Autowired
	private AlbumRepository albumrepo;
	
	
	@Override
	public Picture findById(Long PicId) {
		Picture pic=picrepo.findById(PicId).get();
		return pic;
	}

	@Override
	public List<Picture> findPicByAlbumId(Long AlbumId) {
		List<Picture> pics=new ArrayList<Picture>();
		for(Picture p:picrepo.findAll())if(p.getPicAlbum().equals(albumrepo.findById(AlbumId).get()))pics.add(p);
		return pics;
	}

	@Override
	public List<Picture> findPicByUserId(Long Id) {
		List<Album> albums=new ArrayList<Album>();
		List<Picture> pics=new ArrayList<Picture>();
		for(Album a:albumrepo.findAll())if(a.getCreator().getId().equals(Id))albums.add(a);
		for(Album a:albums) {
			
			for(Picture p:picrepo.findAll()) {
				if(p.getPicAlbum().equals(a))pics.add(p);
			}
		}
		return pics;
	}

	@Override
	public List<Picture> findPicByUserName(String UserName) {
		List<Album> albums=new ArrayList<Album>();
		List<Picture> pics=new ArrayList<Picture>();
		for(Album a:albumrepo.findAll())if(a.getCreator().getUserName().equals(UserName))albums.add(a);
		for(Album a:albums) {
			
			for(Picture p:picrepo.findAll()) {
				if(p.getPicAlbum().equals(a))pics.add(p);
			}
		}
		return pics;
	}

	
	@Override
	public void addPicture(byte[] file, String comment, Album picAlbum) {
		Picture pic=new Picture();
		pic.setComment(comment);
		pic.setImage(file);
		pic.setPicAlbum(picAlbum);
		picrepo.save(pic);
		
		
	}

	@Override
	public void deletePicture(Long Id) {
		picrepo.deleteById(Id);
		
	}

	@Override
	public void addPicture(byte[] file, String comment, Long picAlbumId) {
		// TODO Auto-generated method stub
		
	}

}
