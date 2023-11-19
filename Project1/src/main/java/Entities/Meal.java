package Entities;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Stateless
//@Table(name="Meals")
@Entity
public class Meal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	protected String name;
	protected double price;

	
	@ManyToOne
	@JoinColumn(name="fk_restaurantId")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name="orderID")
	private Orders order;
	
	public Meal() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}