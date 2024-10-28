package model;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
@Entity
public class budgets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int budget_id;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private categories cat;
	
	private int amount;
	private Date start_date;
	private Date end_date;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private user user;

	
	
	public budgets(categories cat, int amount, Date start_date, Date end_date) {
		super();
		this.cat = cat;
		this.amount = amount;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public budgets(categories cat, int amount, Date start_date, Date end_date, model.user user) {
		super();
		this.cat = cat;
		this.amount = amount;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user = user;
	}

	public budgets() {
		super();
	}

	public int getBudget_id() {
		return budget_id;
	}

	public void setBudget_id(int budget_id) {
		this.budget_id = budget_id;
	}

	public categories getCat() {
		return cat;
	}

	public void setCat(categories cat) {
		this.cat = cat;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "budgets [budget_id=" + budget_id + ", cat=" + cat + ", amount=" + amount + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(budget_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		budgets other = (budgets) obj;
		return budget_id == other.budget_id;
	}
	
	
	
	
	
}
