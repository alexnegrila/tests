package org.alexcoders.groupProvider.webServices;

import java.util.List;

import org.alexcoders.groupProvider.dtos.GroupProviderDTO;
import org.alexcoders.groupProvider.dtos.ServiceCallDTO;
import org.alexcoders.groupProvider.dtos.ServiceDTO;
import org.alexcoders.groupProvider.models.Group;
import org.alexcoders.groupProvider.models.GroupProvider;
import org.alexcoders.groupProvider.models.Service;
import org.alexcoders.groupProvider.models.ServiceCall;
import org.alexcoders.groupProvider.services.GroupProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class GroupProviderWebService {

	@Autowired
	GroupProviderService groupProviderService; 
	
	@RequestMapping("/groupProviders/{group}")
	public void update(@PathVariable("group") final String groupJSONString) {
		Gson gson = new Gson();
		final GroupProviderDTO groupProviderDTO = gson.fromJson(groupJSONString, GroupProviderDTO.class);
		
		groupProviderService.save(groupProviderDTO);
	}
	
	@RequestMapping("/groupProviders")
	public Long update(@RequestBody final GroupProviderDTO groupProvider) {
		
		GroupProvider saved = groupProviderService.save(groupProvider);
		return saved.getId();
	}
	
	@RequestMapping("/services/{group}")
	public@ResponseBody List<ServiceDTO> getServices(@PathVariable("group") final Long groupId) {
		return groupProviderService.getServices(groupId);
	}
	
	@RequestMapping("/services/{group}/{id}")
	public@ResponseBody ServiceDTO getService(@PathVariable("group") final Long groupId, @PathVariable("id") final String serviceId) {
		ServiceDTO serviceDTO = groupProviderService.getService(groupId, serviceId);
		return serviceDTO;
	}
	

	@RequestMapping("/service/{group}/{id}/service-calls")
	public List<ServiceCallDTO> getServiceCalls(@PathVariable("group") final Long groupId, @PathVariable("id") final String serviceId) {
		return groupProviderService.getServiceCalls(groupId, serviceId);
	}
	
}
//{\"group\":"\"services\": [{\"id\": 1418653274801,\"name\": \"CurrentTravelInformation\",\"importCommand\": \"GetCMSPage\",\"serviceCalls\": [{\"id\": 1418653295864,\"name\": \"CurrentTravelInformation English\",\"importCommandParameters\": {\"country\": \"de\",\"domain\": \"lh_mobile\",\"language\": \"en\",\"page\": \"/CurrentTravelInformation\"}}]}]}}

//{"group": {"services": [{"id": 1418653274801,"name": "CurrentTravelInformation","importCommand": "GetCMSPage","serviceCalls": [{"id": 1418653295864,"name": "CurrentTravelInformation English","importCommandParameters": {"country": "de","domain": "lh_mobile","language": "en","page": "/CurrentTravelInformation"}}]}]}}