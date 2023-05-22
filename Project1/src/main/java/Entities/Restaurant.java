package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Stateless

@Entity
public class Restaurant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int Id;
	
	protected String name;
	
	//protected int OwnerId;
	@Lob
	protected ArrayList<Meal> ListOfMeals = new ArrayList<Meal>();
	
	@OneToMany(mappedBy="restaurant")
	private List<Meal> meals;
	
	@OneToOne
	@JoinColumn(name="OwnerId")
	private User user;
	
	@OneToMany(mappedBy="restaurant")
	private Set<Orders> orders;

	public Restaurant() {
		
	}


	public Restaurant(String name, ArrayList<Meal> meals) {
		this.name = name;
		this.meals = meals;
	}


	//@Column(name="RestaurantName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}


	public ArrayList<Meal> getListOfMeals() {
		return ListOfMeals;
	}


	public void setListOfMeals(ArrayList<Meal> listOfMeals) {
		ListOfMeals = listOfMeals;
	}


	
}
