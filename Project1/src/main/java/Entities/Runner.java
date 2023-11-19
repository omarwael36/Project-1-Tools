package Entities;

import java.io.Serializable;
import java.util.Set;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless
@Entity
public class Runner implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	protected String name;
	
	protected String status;
	
	protected double delivery_fees;
	
	protected String password;
	
	protected int NumberOfOrdersDelivered;
	
	protected ArrayList<Orders> ListOfOrders = new ArrayList<Orders>();
	
	@OneToMany(mappedBy="runner", fetch=FetchType.EAGER)
	private Set<Orders> order;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getDelivery_fees() {
		return delivery_fees;
	}

	public void setDelivery_fees(double delivery_fees) {
		this.delivery_fees = delivery_fees;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumberOfOrdersDelivered() {
		return NumberOfOrdersDelivered;
	}

	public void setNumberOfOrdersDelivered(int numberOfOrdersDelivered) {
		NumberOfOrdersDelivered = numberOfOrdersDelivered;
	}

	public ArrayList<Orders> getListOfOrders() {
		return ListOfOrders;
	}

	public void setListOfOrders(ArrayList<Orders> listOfOrders) {
		ListOfOrders = listOfOrders;
	}
	
}
