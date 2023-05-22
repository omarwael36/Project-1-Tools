package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless
@Entity
public class Orders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	
	protected ArrayList<Meal> ItemList;
	protected double Total_Price;
	protected String order_status;
	@OneToOne
	@JoinColumn(name="fk_runnerId")
	private Runner runner;
	
	@OneToMany(mappedBy="order")
	private Set<Meal> itemList;
	
	@ManyToOne
	@JoinColumn(name="fk_restaurantId")
	private Restaurant restaurant;
	
	public ArrayList<Meal> getItemList() {
		return ItemList;
	}
	public void setItemList(ArrayList<Meal> itemList) {
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

}
