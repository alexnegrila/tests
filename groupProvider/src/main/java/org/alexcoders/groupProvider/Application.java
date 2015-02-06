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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
		ConfigurableApplicationContext context = springApplication.run(args);
		
        
        GroupProviderRepository groupProviderRepository = context.getBean(GroupProviderRepository.class);
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
	}
}