package org.alexcoders.primeMinisters;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.alexcoders.primeMinisters.services.PrimeMinisterService;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/primeMinisters")
public class PrimeMinistersWebService {

	@GET
	@Path("/users")
	public Response getAllUsers() {
		String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/ageOf/{name}")
	public Response getAge(@PathParam("name") String name) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService) appContext
				.getBean("primeMinisterService");
		String result = "";
		try {
			result = primeMinisterService.getAge(name);
		} catch (RepositoryException | MalformedQueryException
				| QueryEvaluationException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/birthPlaceOf/{name}")
	public Response getBirthPlace(@PathParam("name") String name) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService) appContext
				.getBean("primeMinisterService");
		String result = "";
		try {
			result = primeMinisterService.getBirthPlace(name);
		} catch (RepositoryException | MalformedQueryException
				| QueryEvaluationException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/answerQuestion/{question}")
	public Response getAnswer(@PathParam("question") String question) {
		
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService) appContext
				.getBean("primeMinisterService");
		String result = "";
		try {
			result = primeMinisterService.getAnswer(question);
		} catch (RepositoryException | MalformedQueryException
				| QueryEvaluationException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();
	}

}