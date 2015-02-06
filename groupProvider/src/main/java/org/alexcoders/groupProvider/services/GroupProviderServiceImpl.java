package org.alexcoders.groupProvider.services;

import java.util.ArrayList;
import java.util.List;

import org.alexcoders.groupProvider.dtos.GroupDTO;
import org.alexcoders.groupProvider.dtos.GroupProviderDTO;
import org.alexcoders.groupProvider.dtos.ServiceCallDTO;
import org.alexcoders.groupProvider.dtos.ServiceDTO;
import org.alexcoders.groupProvider.models.Group;
import org.alexcoders.groupProvider.models.GroupProvider;
import org.alexcoders.groupProvider.models.Service;
import org.alexcoders.groupProvider.models.ServiceCall;
import org.alexcoders.groupProvider.repositories.GroupProviderRepository;
import org.alexcoders.groupProvider.repositories.GroupRepository;
import org.alexcoders.groupProvider.repositories.ServiceCallRepository;
import org.alexcoders.groupProvider.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class GroupProviderServiceImpl implements GroupProviderService {

	@Autowired
	GroupProviderRepository groupProviderRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ServiceCallRepository serviceCallRepository;
	
	@Autowired
	GroupProviderToModelConvertor groupProviderToModelConvertor;
	
	@Autowired
	GroupProviderToDTOConvertor groupProviderToDTOConvertor;
	
	@Override
	public GroupProvider save(GroupProviderDTO groupProviderDTO) {
		GroupProvider groupProvider = groupProviderToModelConvertor.toModel(groupProviderDTO);
		return groupProviderRepository.save(groupProvider);
	}

	@Override
	public ServiceDTO getService(Long groupId, String serviceId) {

		List<Service> services = serviceRepository.findOne(groupId, serviceId);
		if (services != null && services.size()>0) {
			return groupProviderToDTOConvertor.toDTO(services.get(0));
		}
		return null;
		
	}

	@Override
	public List<ServiceDTO> getServices(Long groupId) {
		List<Service> services = serviceRepository.findAll(groupId);
		if (services != null) {
			List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
			for (Service service : services) {
				servicesDTO.add(groupProviderToDTOConvertor.toDTO(service));
			}
			return servicesDTO;
		}
		return null;
	}

	@Override
	public List<ServiceCallDTO> getServiceCalls(Long groupId, String serviceId) {
		List<ServiceCall> serviceCalls = serviceCallRepository.findAll(groupId, serviceId);
		if (serviceCalls != null) {
			List<ServiceCallDTO> serviceCallsDTO = new ArrayList<ServiceCallDTO>();
			for (ServiceCall serviceCall : serviceCalls) {
				serviceCallsDTO.add(groupProviderToDTOConvertor.toDTO(serviceCall));
			}
			return serviceCallsDTO;
		}
		return null;
	}

}
