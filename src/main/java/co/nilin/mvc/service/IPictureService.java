package co.nilin.mvc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.Picture;

@Component
public interface IPictureService {
Picture findById(Long PicId);
List<Picture> findPicByAlbumId(Long AlbumId);
List<Picture> findPicByUserId(Long Id);
List<Picture> findPicByUserName(String UserName);
void addPicture(byte[] file,String comment,Long picAlbumId); 
void deletePicture(Long Id);
void addPicture(byte[] file, String comment, Album picAlbum);

}
