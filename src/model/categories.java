package model;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
@Entity
public class categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	private String name;
	private boolean type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private user user;
	
	public categories(String name, boolean type, model.user user) {
		super();
		this.name = name;
		this.type = type;
		this.user = user;
	}

	
	public categories(String name, boolean type) {
		super();
		this.name = name;
		this.type = type;
	}


	public categories() {
		super();
	}

	

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return category_id + " - " + name ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		categories other = (categories) obj;
		return category_id == other.category_id;
	}
	

}
