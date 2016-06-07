//package ar.com.nolg.rest;
//
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import com.em.ejb.beans.interfaces.StudentDAORemote;
//import com.em.jpa.entity.Student;
//
//@RequestScoped
//@Path("/students")
//public class StudentEndpoint {
//	@EJB
//	StudentDAORemote studentRemote;
//	
//	@POST
//	@Consumes("application/json")
//	public Student create(Student student) {
//		return studentRemote.create(student);
//	}
//
//	@GET
//	@Path("/{id:[0-9][0-9]*}")
//	@Produces("application/json")
//	public Response findById(@PathParam("id") int id) {
//		Student student = studentRemote.getStudent(id);
//		if (student == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		return Response.ok(student).build();
//	}
//
//	@GET
//	@Produces("application/json")
//	public List listAll(
//			@QueryParam("start") final Integer startPosition,
//			@QueryParam("max") final Integer maxResult) {
//		List students = studentRemote.getAllStudents();
//		return students;
//	}
//
//	@PUT
//	@Consumes("application/json")
//	@Produces("text/plain")
//	public Response update(Student student){ 
//		Response r = null;
//		try{
//			studentRemote.update(student);
//			r = Response.ok("OK").build();
//		}
//		catch(Exception e){
//			System.out.println("exception in create "+e);
//			r = Response.ok("error").build();
//		}
//		return r;
//	}
//
//	@DELETE
//	@Path("/{id:[0-9][0-9]*}")
//	@Produces("text/plain")
//	public Response deleteById(@PathParam("id") int id) {
//		Response r = null;
//		try{
//			studentRemote.remove(id);
//			r = Response.ok("OK").build();
//		}
//		catch(Exception e){
//			System.out.println("exception in create "+e);
//			r = Response.ok("error").build();
//		}
//		return r;
//	}
//}