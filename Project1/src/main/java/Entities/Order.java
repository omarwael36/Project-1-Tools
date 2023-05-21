package Entities;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	@OneToMany(mappedBy="order" , fetch = FetchType.EAGER)
	protected Set<Meal> ItemList;
	protected double Total_Price;
	protected int fk_runnerId;
	protected int fk_restaurantId;
	protected String order_status;
	
	@OneToOne(optional=false, mappedBy="order")
	private Runner runner;
	
	@ManyToOne
	@JoinColumn(name="restaurantID")
	private Restaurant restaurant;
	
	public Set<Meal> getItemList() {
		return ItemList;
	}
	public void setItemList(Set<Meal> itemList) {
		ItemList = itemList;
	}
	public double getTotal_Price() {
		return Total_Price;
	}
	public void setTotal_Price(double total_Price) {
		Total_Price = total_Price;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public int getFk_runnerId() {
		return fk_runnerId;
	}
	public void setFk_runnerId(int fk_runnerId) {
		this.fk_runnerId = fk_runnerId;
	}
	public int getFk_restaurantId() {
		return fk_restaurantId;
	}
	public void setFk_restaurantId(int fk_restaurantId) {
		this.fk_restaurantId = fk_restaurantId;
	}
	
}
