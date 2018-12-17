package co.nilin.mvc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;

@Component
public interface IAlbumService {

	List<Album> findAll();
	Album findAlbumById(Long AlbumId);
	List<Album> findAlbumByUserName(String UserName);
	void addAlbum(Long UserId,String name);
	void deleteAlbum(Long Id);
}
