package Entities;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@OneToOne(mappedBy="runner")
	private Orders order;
	
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
