package model;
import java.util.*;

import javax.persistence.*;
@Entity
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String username;
	private String email;
	private String password_hash;
	private Date created_at;
	private Date last_login;
	

	public user(String username, String email, String password_hash, Date created_at, Date last_login) {
		super();
		this.username = username;
		this.email = email;
		this.password_hash = password_hash;
		this.created_at = created_at;
		this.last_login = last_login;
	}

	public user() {
		super();
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword_hash() {
		return password_hash;
	}


	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Date getLast_login() {
		return last_login;
	}


	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

//
//	
//
//	public Set<categories> getListCat() {
//		return listCat;
//	}
//
//	public void setListCat(Set<categories> listCat) {
//		this.listCat = listCat;
//	}
//
//	public Set<transactions> getListTran() {
//		return listTran;
//	}
//
//	public void setListTran(Set<transactions> listTran) {
//		this.listTran = listTran;
//	}
//
//	public Set<budgets> getListBud() {
//		return listBud;
//	}
//
//	public void setListBud(Set<budgets> listBud) {
//		this.listBud = listBud;
//	}
//
//	public Set<notifications> getListNon() {
//		return listNon;
//	}
//
//	public void setListNon(Set<notifications> listNon) {
//		this.listNon = listNon;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		user other = (user) obj;
		return user_id == other.user_id;
	}

	@Override
	public String toString() {
		return  user_id +"-" + username;
	}

}
