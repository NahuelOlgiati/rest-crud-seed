package ar.com.nolg.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.nolg.model.Employee;
import ar.com.nolg.service.EmployeeManager;
import ar.com.nolg.service.exception.ServiceException;
import ar.com.nolg.util.CompareUtil;

@Path("/employee")
@RequestScoped
public class EmployeeRest {

	@Inject
	@Named("employeeManager")
	EmployeeManager employeeManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee[] get() {
		return employeeManager.getAll().toArray(new Employee[0]);
	}

	@GET
	@Path("/get:{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		final Employee employee = employeeManager.get(id);
		if (CompareUtil.isEmpty(employee)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(employee).build();
	}

	@POST
	@Consumes("application/json")
	public Response create(Employee employee) {
		Response r = null;
		try {
			employeeManager.save(employee);
		} catch (ServiceException e) {
			System.out.println("exception in create " + e);
			r = Response.ok("error").build();
		}
		return r;
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public Response update(Employee employee) {
		Response r = null;
		try {
			employeeManager.save(employee);
		} catch (ServiceException e) {
			System.out.println("exception in update " + e);
			r = Response.ok("error").build();
		}
		return r;
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	@Produces("text/plain")
	public Response deleteById(@PathParam("id") Long id) {
		Response r = null;
		try {
			employeeManager.delete(id);
			r = Response.ok("OK").build();
		} catch (Exception e) {
			System.out.println("exception in create " + e);
			r = Response.ok("error").build();
		}
		return r;
	}
}
