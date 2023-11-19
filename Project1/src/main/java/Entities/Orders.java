package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	protected String runnerName;
	protected String restName;
	protected int CustomerId;
	
	@Lob
	protected ArrayList<Meal> ItemList = new ArrayList<Meal>();
	protected double Total_Price = 0;
	protected String order_status;
	
	@ManyToOne
	@JoinColumn(name="fk_runnerId")
	private Runner runner;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getRunnerName() {
		return runnerName;
	}
	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

}
