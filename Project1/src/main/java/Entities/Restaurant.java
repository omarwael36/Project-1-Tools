package Entities;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless
//@Table(name="Restaurants")
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	protected String name;
	
	protected int OwnerId;
	
	@OneToMany(mappedBy="restaurant" , fetch = FetchType.EAGER)
	private Set<Meal> meals;
	
	@OneToOne(optional=false, mappedBy="restaurant")
	private User user;
	
	@OneToMany(mappedBy="restaurant" , fetch = FetchType.EAGER)
	private Set<Order> orders;
	
	//@OneToMany(mappedBy="restaurant")
	//private ArrayList<Order> order = new ArrayList<Order>();
	
	public Restaurant() {
		
	}

	protected ArrayList<Meal> ListOfMeals = new ArrayList<Meal>();
	//@Column(name="RestaurantName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//@Column(name="OwnerId")
	public int getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(int ownerId) {
		OwnerId = ownerId;
	}
	//@Column(name="ListOfMeal")
	public ArrayList<Meal> getListOfMeals() {
		return ListOfMeals;
	}

	public void setListOfMeals(ArrayList<Meal> listOfMeals) {
		ListOfMeals = listOfMeals;
	}
	
}
