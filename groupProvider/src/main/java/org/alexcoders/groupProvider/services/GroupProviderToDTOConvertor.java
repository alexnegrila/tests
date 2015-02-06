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
import org.springframework.stereotype.Component;

@Component
public class GroupProviderToDTOConvertor {

	public GroupProviderDTO toDTO(GroupProvider groupProvider) {
		if (groupProvider != null) {
			GroupProviderDTO groupProviderDTO = new GroupProviderDTO();
			Group group = groupProvider.getGroup();
			groupProviderDTO.setId(groupProvider.getId());
			GroupDTO groupDTO = toDTO(group);
			groupProviderDTO.setGroup(groupDTO);
			return groupProviderDTO;
		}
		return null;
	}

	public GroupDTO toDTO(Group group) {
		if (group != null) {
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.setId(group.getId());
			List<Service> services = group.getServices();
			if (services != null) {
				List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
				for (Service service : services) {
					ServiceDTO serviceDTO = toDTO(service);
					servicesDTO.add(serviceDTO);
				}
				groupDTO.setServices(servicesDTO);

			}
			return groupDTO;
		}
		return null;
	}

	public ServiceDTO toDTO(Service service) {
		if (service != null) {
			ServiceDTO serviceDTO = new ServiceDTO();
			serviceDTO.setId(service.getId());
			serviceDTO.setName(service.getName());
			serviceDTO.setImportCommand(service.getImportCommand());
			List<ServiceCall> serviceCalls = service.getServiceCalls();
			if (serviceCalls != null) {
				List<ServiceCallDTO> serviceCallsDTO = new ArrayList<ServiceCallDTO>();
				for (ServiceCall serviceCall : serviceCalls) {
					ServiceCallDTO serviceCallDTO = toDTO(serviceCall);
					serviceCallsDTO.add(serviceCallDTO);
				}
				serviceDTO.setServiceCalls(serviceCallsDTO);
			}
			return serviceDTO;
		}
		return null;
	}

	public ServiceCallDTO toDTO(ServiceCall serviceCall) {
		ServiceCallDTO serviceCallDTO = new ServiceCallDTO();
		serviceCallDTO.setId(serviceCall.getId());
		serviceCallDTO.setName(serviceCall.getName());
		serviceCallDTO.setImportCommandParameters(serviceCall.getImportCommandParameters());
		return serviceCallDTO;
	}
}
