package Controllers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Entities.User;

@Stateless
@Path("UserController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
	@PersistenceContext
	private EntityManager em;
	
	@Path("AddUser")
	@POST
	public void SignUp(User user) {
		em.persist(user);
	}
	
	@Path("GetUsers")
	@GET
	public List<User> ShowUsers() {
		TypedQuery<User> query = em.createQuery("SELECT users FROM User users", User.class);
		List<User> users = query.getResultList();
		return users;
	}
	
}
