package Entities;

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
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	protected String name;
	protected double price;
	protected int fk_restaurantId;
	
	@ManyToOne
	@JoinColumn(name="restaurantID")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name="orderID")
	private Order order;
	
	public Meal() {
		
	}
	//@Column(name="MealName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//@Column(name="MealPrice")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	//@Column(name="Fk_restaurantId")
	public int getFk_restaurantId() {
		return fk_restaurantId;
	}
	public void setFk_restaurantId(int fk_restaurantId) {
		this.fk_restaurantId = fk_restaurantId;
	}
	
}
