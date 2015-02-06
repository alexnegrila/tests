package primeMinisters;

import junit.framework.Assert;

import org.alexcoders.primeMinisters.services.PrimeMinisterService;
import org.junit.Test;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrimeMinisterServiceTest {

	@Test
	public void getAgeTest() {
		ClassPathXmlApplicationContext appContext = 
  	    	  new ClassPathXmlApplicationContext("spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService)appContext.getBean("primeMinisterService");
    	String result = "";
    	try {
    		result = primeMinisterService.getAge("Tony Blair");
    	} catch (RepositoryException|MalformedQueryException|QueryEvaluationException e) {
			e.printStackTrace();
		}
    	Assert.assertTrue("good", result.equals("61"));
	}
	
	@Test
	public void getBirthPlaceTest() {
		ClassPathXmlApplicationContext appContext = 
  	    	  new ClassPathXmlApplicationContext("spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService)appContext.getBean("primeMinisterService");
    	String result = "";
    	try {
    		result = primeMinisterService.getBirthPlace("David Cameron");
    	} catch (RepositoryException|MalformedQueryException|QueryEvaluationException e) {
			e.printStackTrace();
		}
    	Assert.assertTrue("good", result.equals("Oxfordshire,London"));
	}
	
	@Test
	public void getAnswerTest() {
		ClassPathXmlApplicationContext appContext = 
  	    	  new ClassPathXmlApplicationContext("spring-config.xml");
		PrimeMinisterService primeMinisterService = (PrimeMinisterService)appContext.getBean("primeMinisterService");
    	String result1 = "";
    	String result2 = "";
    	try {
    		result1 = primeMinisterService.getAnswer("How old is Tony Blair?");
    		result2 = primeMinisterService.getAnswer("Where was David Cameron born?");
    	} catch (RepositoryException|MalformedQueryException|QueryEvaluationException e) {
			e.printStackTrace();
		}
    	Assert.assertTrue("good", result1.equals("61"));
    	Assert.assertTrue("good", result2.equals("Oxfordshire,London"));
	}
}
