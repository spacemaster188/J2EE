package by.gsu.epamlab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
     
    @Id
    @Column(name="idUsers")
    @GeneratedValue
    private Integer idUsers;
     
    @Column(name="login")
    private String login;
    
    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstname;
 
    @Column(name="lastname")
    private String lastname;
     
    @Column(name="email")
    private String email;

	public Integer getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(Integer idUsers) {
		this.idUsers = idUsers;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
     
}