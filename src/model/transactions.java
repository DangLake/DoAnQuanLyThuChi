package model;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
@Entity
public class transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	private int amount;
	private Date date;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private categories cat;
		
		
	@ManyToOne
	@JoinColumn(name = "user_id")
	private user user;

	public transactions() {
		super();
	}


	public transactions(int amount, Date date, String description, categories cat, user user) {
		super();
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.cat = cat;
		this.user = user;
	}


	
	public transactions(int amount, Date date, String description, categories cat) {
		super();
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.cat = cat;
	}


	public int getTransaction_id() {
		return transaction_id;
	}


	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public categories getCat() {
		return cat;
	}


	public void setCat(categories cat) {
		this.cat = cat;
	}


	public user getUser() {
		return user;
	}


	public void setUser(user user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return  transaction_id  + amount + ", date=" + date
				+ ", description=" + description + ", cat=" + cat + ", user=" + user + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(transaction_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		transactions other = (transactions) obj;
		return transaction_id == other.transaction_id;
	}
	
	
	
}
