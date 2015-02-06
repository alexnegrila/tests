package org.alexcoders.groupProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.alexcoders.groupProvider.models.Group;
import org.alexcoders.groupProvider.models.GroupProvider;
import org.alexcoders.groupProvider.models.Service;
import org.alexcoders.groupProvider.models.ServiceCall;
import org.alexcoders.groupProvider.repositories.GroupProviderRepository;
import org.alexcoders.groupProvider.repositories.GroupRepository;
import org.alexcoders.groupProvider.repositories.ServiceCallRepository;
import org.alexcoders.groupProvider.repositories.ServiceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class GroupProviderIntegrationTests {

	@Autowired
	GroupProviderRepository groupProviderRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ServiceCallRepository serviceCallRepository;

	RestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void testJpa() {
		GroupProvider groupProvider = new GroupProvider();
		Group group = new Group();
		
		Service service = new Service();
		Random rand = new Random();
		int n = rand.nextInt(1000) + 1;
		service.setId("1" + n);
		service.setName("test");
		service.setImportCommand("testCom");
		List<Service> services = new ArrayList<Service>();
		services.add(service);
		service.setGroupParent(group);
		group.setServices(services);
		
		ServiceCall serviceCall = new ServiceCall();
		n = rand.nextInt(1000) + 1;
		serviceCall.setId("1" + n);
		serviceCall.setName("test");
		Map<String, String> commandParam = new HashMap<String, String>();
		commandParam.put("testkey", "testValue");
		serviceCall.setImportCommandParameters(commandParam);
		List<ServiceCall> serviceCalls = new ArrayList<ServiceCall>();
		serviceCalls.add(serviceCall);
		serviceCall.setServiceParent(service);
		service.setServiceCalls(serviceCalls);
		
		groupProvider.setGroup(group);
		groupProviderRepository.save(groupProvider);
		
		//GroupProvider
		System.out.println("gp" + groupProviderRepository.count());
		Assert.assertTrue("bad", groupProviderRepository.count()>0);
//		groupProviderRepository.findOne(Long.valueOf(1))
//		groupRepository.findOne(Long.valueOf(1))
		groupRepository.save(group);
		
		//Group
		System.out.println("g" + groupRepository.count());
		Assert.assertTrue("bad", groupRepository.count()>0);
//		serviceRepository.findOne("1483")
		serviceRepository.save(service);
		
		//Service
		System.out.println("g" + serviceRepository.count());
		Assert.assertTrue("bad", serviceRepository.count()>0);
		
		Assert.assertTrue("bad", serviceRepository.findAll(Long.valueOf(1)).size()>0);
		
		Assert.assertTrue("bad", serviceRepository.findOne(Long.valueOf(1), service.getId()) != null);
		
		ResponseEntity<Service> retrivedService = restTemplate.getForEntity("http://localhost:8080/serviceRepository/"+service.getId()+"/", Service.class);
		Assert.assertTrue("bad", retrivedService.getBody().getName().equals(service.getName()));
		
		serviceCallRepository.save(serviceCall);
		
		//ServiceCall
		System.out.println("g" + serviceCallRepository.count());
		Assert.assertTrue("bad", serviceCallRepository.count()>0);
		
		Assert.assertTrue("bad", serviceCallRepository.findAll(Long.valueOf(1), service.getId()).size()>0);
		
		ResponseEntity<ServiceCall> retrivedServiceCall = restTemplate.getForEntity("http://localhost:8080/serviceCallRepository/"+serviceCall.getId()+"/", ServiceCall.class);
		Assert.assertTrue("bad", retrivedServiceCall.getBody().getName().equals(serviceCall.getName()));
	}
	
	@Test
	public void testCreateRequest() throws Exception {
		//if @RestResource(exported = false) is activated on the JPA models to enable RestRepository creation then RestRepository read is not possible 
		
		//https://jira.spring.io/browse/DATAREST-396
		//http://stackoverflow.com/questions/24569399/how-to-post-nested-entities-with-spring-data-rest
		
		
//		String json = "{\"group\":{\"services\": [{\"id\": 1418653274801,\"name\": \"CurrentTravelInformation\",\"importCommand\": \"GetCMSPage\",\"serviceCalls\": [{\"id\": 1418653295864,\"name\": \"CurrentTravelInformation English\",\"importCommandParameters\": {\"country\": \"de\",\"domain\": \"lh_mobile\",\"language\": \"en\",\"page\": \"/CurrentTravelInformation\"}}]}]}}";
//		
//		Gson gson = new Gson();
//		GroupProvider fromJson = gson.fromJson(json, GroupProvider.class);
//		URI url = new URI("http://localhost:8080/groupProviderRepository");
//		RequestEntity<GroupProvider> requestEntity = new RequestEntity<GroupProvider>(fromJson, HttpMethod.POST , url );
//		restTemplate.postForEntity("http://localhost:8080/groupProviderRepository", requestEntity , String.class);
//		
//		ServiceCall serviceCall = fromJson.getGroup().getServices().get(0).getServiceCalls().get(0);
//		ResponseEntity<ServiceCall> retrivedServiceCall = restTemplate.getForEntity("http://localhost:8080/serviceCallRepository/"+serviceCall .getId()+"/", ServiceCall.class);
//		Assert.assertTrue("bad", retrivedServiceCall.getBody().getId().equals(serviceCall.getId()));
	}

}