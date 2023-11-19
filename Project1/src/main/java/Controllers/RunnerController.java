package Controllers;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
import Entities.Runner;

@Stateless
@Path("RunnerController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RunnerController {
	@PersistenceContext
	private EntityManager em;
	public int NumberOfRunners = 0;
	public Runner runner;
	@RolesAllowed("runner")
	@Path("RunnerSignUp")
	@POST
	public String SignUp(Runner r){
		TypedQuery<Runner> query = em.createQuery("SELECT runner FROM Runner runner", Runner.class);
		List<Runner> runner = query.getResultList();
		if(runner.size()>0) {
			for(int i = 0;i<NumberOfRunners;i++) {
				if(runner.get(i).getName().equals(r.getName())) {
					return "Runner already registered !!";
				}
			}
		}
		em.persist(r);
		NumberOfRunners++;
		return "Runner SignUp successfully";
	}
	@PermitAll
	@Path("ShowRunners")
	@GET
	public List<Runner> ShowRunners(){
		TypedQuery<Runner> query = em.createQuery("SELECT runner FROM Runner runner", Runner.class);
		List<Runner> runner = query.getResultList();
		return runner;
	}
	@RolesAllowed("runner")
	@GET
	@Path("Login/{name}/{password}")
	public String Login(@PathParam("name") String name, @PathParam("password") String password) {
		TypedQuery<Runner> query = em.createQuery("SELECT runner FROM Runner runner", Runner.class);
		List<Runner> runners = query.getResultList();
		for(int i = 0;i<NumberOfRunners;i++) {
			if(runners.get(i).getName().equals(name) && runners.get(i).getPassword().equals(password)) {
				runner = runners.get(i);
				return "Login done successfully !!";
			}
		}
		return "No Such Runner !!";
	}
	@RolesAllowed("runner")
	@Path("MarkOrderDelivered/{id}")
	public String MarkOrderedDelivered(@PathParam("id")int id) {
	    TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.Id = :id", Orders.class);
	    query.setParameter("id", id);
	    try {
	        Orders order = query.getSingleResult();
	        if (order != null) {
	            order.setOrder_status("Delivered");
	            runner.setStatus("available");
	            return "Order is delivered successfully";
	        } else {
	            return "There is no such order";
	        }
	    } catch (NoResultException e) {
	        return "There is no such order";
	    } catch (Exception e) {
	        return "An error occurred while processing the request";
	    }
	}
	@RolesAllowed("runner")
	@GET
	@Path("GetNumberOfOrdersDeliverd")
	public int GetNumberOfOrdersDeliverd() {
		TypedQuery<Orders> query = em.createQuery("SELECT orders FROM Orders orders WHERE orders.fk_runnerId = ?1", Orders.class);
		int numberofDelivered = 0;
		query.setParameter(1, runner.getId());
		List<Orders> orders = query.getResultList();
		for(int i = 0;i<orders.size();i++) {
			if(orders.get(i).getOrder_status().equals("Delivered")) {
				numberofDelivered++;
			}
		}
		runner.setNumberOfOrdersDelivered(numberofDelivered);
		return numberofDelivered;
	}
}
