package Entities;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Stateless
@Entity
public class Runner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	protected String name;
	
	protected String status;
	
	protected double delivery_fees;
	
	@OneToOne(optional=false)
	@JoinColumn(name="orderID")
	private Order order;
	
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
	
	
}
