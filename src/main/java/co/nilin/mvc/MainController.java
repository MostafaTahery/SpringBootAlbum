package co.nilin.mvc;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.service.AlbumService;
import co.nilin.mvc.service.PictureService;
import co.nilin.mvc.service.UserService;

@Controller
@RequestMapping("/")

public class MainController {

	@Autowired
	public UserService usrservice;

	@Autowired
	public AlbumService albumservice;

	@Autowired
	public PictureService pictureservice;

	@RequestMapping(value = "/home/", method = RequestMethod.GET)
	public String greetingg(HttpSession session, Model model) {
		System.out.println("home get method");

		return "home";
	}

	@RequestMapping(value = "/home/", method = RequestMethod.POST, params = { "FullName", "UserName", "Password",
			"Email", "BirthDate" })
	public String addUser(Model model, HttpSession session, @RequestParam("FullName") String FullName,
			@RequestParam("UserName") String UserName, @RequestParam("Password") String Password,
			@RequestParam("Email") String Email, @RequestParam("BirthDate") String BirthDate) {
		if (usrservice.isAvailable(UserName)) {
			usrservice.addUser(FullName, UserName, Password, Email, Date.valueOf(BirthDate));
			session.setAttribute("token", UserName);
			return "redirect:/albums/";
		} else {
			model.addAttribute("statusup", "Username is not available, Please choose another one");
			return "redirect:/home/";
		}
	}

	@RequestMapping(value = "/home/", method = RequestMethod.POST, params = { "name_in", "pass_in" })
	public String signinUser(Model model, HttpSession session, @RequestParam("name_in") String name_in,
			@RequestParam("pass_in") String pass_in) {
		System.out.println("sign in posted");
		if (usrservice.isValid(name_in, pass_in)) {
			model.addAttribute("name_in", session.getAttribute("token"));
			return "redirect:/albums/";
		} else {
			model.addAttribute("statusin", "Username and Password Error!");
			return "redirect:/home/";
		}
	}

	@RequestMapping(value = "/editprofile/", method = RequestMethod.GET)
	public String editprofile(HttpSession session, Model model) {
		String un = (String) session.getAttribute("token");
		Long UserId = usrservice.findIdByUserName(un);
		session.setAttribute("UserId", UserId);
		model.addAttribute("user", usrservice.findById(UserId));
		return "editprofile";
	}

	@RequestMapping(value = "/editprofile/", method = RequestMethod.POST)
	public String saveeditprofile(Model model, HttpSession session, @RequestParam("FullName") String FullName,
			@RequestParam("UserName") String UserName, @RequestParam("Email") String Email,
			@RequestParam("BirthDate") String BirthDate, @RequestParam("file") MultipartFile file) {
		byte[] bb = null;
		Long UserId = (Long) session.getAttribute("UserId");
		User usr = usrservice.findById(UserId);
		usr.setBirthDate(Date.valueOf(BirthDate));
		usr.setEmail(Email);
		usr.setFullName(FullName);
		usr.setUserName(UserName);

		try {
			bb = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		usr.setProfilePic(bb);
		usrservice.updateUser(usr);

		return "editprofile";
	}

	@RequestMapping("/albums/")
	public String getAlbums(HttpSession session, Model model) {
		String username = (String) session.getAttribute("token");
		model.addAttribute("username", username);
		List<Album> albums = new ArrayList<Album>();
		albums = albumservice.findAlbumByUserName(username);
		model.addAttribute("albums", albums);
		System.out.println("albums page");
		return "albums";
	}

	@RequestMapping(value = "/album/{AlbumId}")
	public String findAlbum(HttpSession session, Model model, @PathVariable Long AlbumId) {
		model.addAttribute("pics", this.pictureservice.findPicByAlbumId(AlbumId));
		model.addAttribute("album", this.albumservice.findAlbumById(AlbumId));
		session.setAttribute("AlbumId", AlbumId);
		return "album";
	}

	@RequestMapping(value = "/album/", method = RequestMethod.POST)
	public String newpic(HttpSession session, Model model, @RequestParam("file") MultipartFile file,
			@RequestParam("comment") String comment) {
		Long id = (Long) session.getAttribute("AlbumId");
		byte[] bb = null;
		try {
			bb = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pictureservice.addPicture(bb, comment, id);
		return "album" + "/" + id;
	}

	@RequestMapping(value = "/album/deletepic/{picId}")
	public String deletepic(HttpSession session, Model model, @PathVariable Long picId) {
		pictureservice.deletePicture(picId);
		return "album" + "/" + session.getAttribute("AlbumId");
	}
}
