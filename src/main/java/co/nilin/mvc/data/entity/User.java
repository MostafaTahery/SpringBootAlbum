package co.nilin.mvc.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="USERSS")
public class User {
	
	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String FullName;
	private String UserName;
	private String Password;
	private String Email;
	private Date BirthDate;
	private byte[] ProfilePic;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="Creator")
	private List<Album> albums;
		
	
	public User() {
		super();
	}
		
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}
	public byte[] getProfilePic() {
		return ProfilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		ProfilePic = profilePic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BirthDate == null) ? 0 : BirthDate.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((FullName == null) ? 0 : FullName.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + ((ProfilePic == null) ? 0 : ProfilePic.hashCode());
		result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
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
		User other = (User) obj;
		if (BirthDate == null) {
			if (other.BirthDate != null)
				return false;
		} else if (!BirthDate.equals(other.BirthDate))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (FullName == null) {
			if (other.FullName != null)
				return false;
		} else if (!FullName.equals(other.FullName))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (ProfilePic == null) {
			if (other.ProfilePic != null)
				return false;
		} else if (!ProfilePic.equals(other.ProfilePic))
			return false;
		if (UserName == null) {
			if (other.UserName != null)
				return false;
		} else if (!UserName.equals(other.UserName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", FullName=" + FullName + ", UserName=" + UserName + ", Password=" + Password
				+ ", Email=" + Email + ", BirthDate=" + BirthDate + ", ProfilePic=" + ProfilePic + "]";
	}

	
	
	
	

}
