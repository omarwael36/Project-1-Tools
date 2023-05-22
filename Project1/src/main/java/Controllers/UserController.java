package Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Entities.Meal;
import Entities.Restaurant;
import Entities.User;

@Stateless
@Path("UserController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
	@PersistenceContext
	private EntityManager em;
	public int NumberOfUsers = 0;
	public User user;
	
	
	public UserController() {
		
	}

	@Path("SignUp")
	@POST
	public String SignUp(User u) {
		TypedQuery<User> query = em.createQuery("SELECT users FROM User users", User.class);
		List<User> users = query.getResultList();
		if(users.size()>0) {
			for(int i = 0;i<NumberOfUsers;i++) {
				if(users.get(i).getName().equals(u.getName())) {
					return "User already registered !!";
				}
			}
		}
		em.persist(u);
		NumberOfUsers++;
		return "Signup Done successfully !!";
	}
	
	@GET
	@Path("Login/{name}/{password}")
	public String Login(@PathParam("name") String name, @PathParam("password") String password) {
		TypedQuery<User> query = em.createQuery("SELECT users FROM User users", User.class);
		List<User> users = query.getResultList();
		for(int i = 0;i<NumberOfUsers;i++) {
			if(users.get(i).getName().equals(name) && users.get(i).getPassword().equals(password)) {
				user = users.get(i);
				return "Login done successfully !!";
			}
		}
		return "No Such user !!";
	}
	
	@POST
	@Path("CreateMenu")
	public String CreateMenu(Restaurant restaurant) {
		
//		Restaurant restaurant = new Restaurant();
//		System.out.println(ira.getMeals().size());
//		System.out.println(ira.getMeals());
//		if(ira != null) {
//			try {
//				for(int i = 0;i < ira.getMeals().size();i++) {
//				
//					restaurant.getMeals().add(ira.getMeals().get(i));
//				}
//				em.persist(restaurant);
//				return "Menu created successfully !!";
//			}
//			
//			catch(Exception e){
//				throw e;
//			}
//		}
//		else {
//			return "null";
//		}
		//restaurant.setOwnerId(user.getId());
		em.persist(restaurant);
		return "Menu created successfully !!";
	}
	
	@GET
	@Path("ListRestaurants")
	public List<Restaurant> ListRestaurants() {
		TypedQuery<Restaurant> query = em.createQuery("SELECT rest FROM Restaurant rest", Restaurant.class);
		List<Restaurant> rest = query.getResultList();
		return rest;
	}
	
	
	
}
