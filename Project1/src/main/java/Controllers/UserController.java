package Controllers;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
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

import Entities.Orders;
import Entities.Restaurant;
import Entities.User;
import Entities.Runner;

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
	@PermitAll
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
		return "Signup Done successfully !--!";
	}
	@PermitAll
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
	@RolesAllowed("owner")
	@POST
	@Path("CreateMenu")
	public String CreateMenu(Restaurant restaurant) {
		em.persist(restaurant);
		return "Menu created successfully !!";
	}
	@RolesAllowed("customer")
	@GET
	@Path("ListRestaurants")
	public List<Restaurant> ListRestaurants() {
		TypedQuery<Restaurant> query = em.createQuery("SELECT rest FROM Restaurant rest", Restaurant.class);
		List<Restaurant> rest = query.getResultList();
		return rest;
	}
	@RolesAllowed("owner")
	@POST
	@Path("EditRestaurant")
	public String editRestaurant(Restaurant updatedRestaurant) {
	    try {
	        Restaurant originalRestaurant = em.find(Restaurant.class, updatedRestaurant.getId());
	        if (originalRestaurant == null) {
	            return "Restaurant not found";
	        }
	        originalRestaurant.setName(updatedRestaurant.getName());
	        originalRestaurant.setListOfMeals(updatedRestaurant.getListOfMeals());
	        em.merge(originalRestaurant);
	        return "Menu edited successfully !!";
	    } catch (Exception e) {
	        return "Failed to edit menu: " + e.getMessage();
	    }
	}
	@RolesAllowed("customer")
	@GET
	@Path("GetRestaurant/{id}")
	public Restaurant GetRestaurant(@PathParam("id") int id) {
		Restaurant rest;
		rest = em.find(Restaurant.class, id);
		return rest;
	}
	@RolesAllowed("customer")
	@Path("CreateOrder")
	@POST
	public String CreateOrder(Orders orders) {
		RunnerController rc = new RunnerController();
		Runner r = new Runner();
		for(int i = 0;i < rc.ShowRunners().size();i++) {
			if(rc.ShowRunners().get(i).getStatus().equals("available")) {
				rc.ShowRunners().get(i).setStatus("busy");
				rc.ShowRunners().get(i).getListOfOrders().add(orders);
				rc.ShowRunners().get(i).setListOfOrders(rc.ShowRunners().get(i).getListOfOrders());
				r = rc.ShowRunners().get(i);
				break;
			}
		}
		for(int i = 0;i<orders.getItemList().size();i++) {
			orders.setTotal_Price(orders.getItemList().get(i).getPrice() + orders.getTotal_Price());
		}
		orders.setTotal_Price(r.getDelivery_fees() + orders.getTotal_Price());
		orders.setRunnerName(r.getName());
		orders.setOrder_status("preparing");
		orders.setCustomerId(user.getId());
		em.persist(orders);
		return "Order is Done successfully";
	}
	@RolesAllowed("customer")
	@GET
	@Path("ListOrders")
	public List<Orders> ListOrders() {
		TypedQuery<Orders> query = em.createQuery("SELECT orders FROM Orders orders", Orders.class);
		List<Orders> orders = query.getResultList();
		return orders;
	}
	@RolesAllowed("customer")
	@POST
	@Path("EditOrder")
	public String editOrder(Orders updatedOrder) {
	    try {
	        Orders originalOrder = em.find(Orders.class, updatedOrder.getId());
	        if (originalOrder == null) {
	            return "Order not found";
	        }
	        if (originalOrder.getOrder_status().equals("cancelled")) {
	            return "Sorry you cannot edit this order";
	        }
	        originalOrder.setItemList(updatedOrder.getItemList());
	        em.merge(originalOrder);
	        return "Order edited successfully !!";
	    } catch (Exception e) {
	        return "Failed to edit Order: " + e.getMessage();
	    }
	}
	
	
}
