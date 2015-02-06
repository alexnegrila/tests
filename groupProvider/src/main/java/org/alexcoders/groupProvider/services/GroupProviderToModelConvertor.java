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
public class GroupProviderToModelConvertor {

	public GroupProvider toModel(GroupProviderDTO groupProviderDTO) {
		if (groupProviderDTO != null) {
			GroupProvider groupProvider = new GroupProvider();
			groupProvider.setId(groupProviderDTO.getId());
			GroupDTO groupDTO = groupProviderDTO.getGroup();

			toModel(groupProvider, groupDTO);

			return groupProvider;
		}
		return null;
	}

	public Group toModel(GroupProvider groupProvider, GroupDTO groupDTO) {
		if (groupDTO != null) {
			Group group = new Group();
			group.setId(groupDTO.getId());
			groupProvider.setGroup(group);
			List<ServiceDTO> servicesDTO = groupDTO.getServices();
			if (servicesDTO != null) {
				List<Service> services = new ArrayList<Service>();
				for (ServiceDTO serviceDTO : servicesDTO) {
					Service service = toModel(group, serviceDTO);
					services.add(service);
				}
				group.setServices(services);

			}
			return group;
		}
		return null;
	}

	public Service toModel(Group group, ServiceDTO serviceDTO) {
		Service service = new Service();
		service.setId(serviceDTO.getId());
		service.setName(serviceDTO.getName());
		service.setGroupParent(group);
		service.setImportCommand(serviceDTO.getImportCommand());
		List<ServiceCallDTO> serviceCallsDTO = serviceDTO.getServiceCalls();
		if (serviceCallsDTO != null) {
			List<ServiceCall> serviceCalls = new ArrayList<ServiceCall>();
			for (ServiceCallDTO serviceCallDTO : serviceCallsDTO) {
				ServiceCall serviceCall = toModel(service, serviceCallDTO);
				serviceCalls.add(serviceCall);
			}
			service.setServiceCalls(serviceCalls);
		}
		return service;
	}

	public ServiceCall toModel(Service service, ServiceCallDTO serviceCallDTO) {
		ServiceCall serviceCall = new ServiceCall();
		serviceCall.setId(serviceCallDTO.getId());
		serviceCall.setName(serviceCallDTO.getName());
		serviceCall.setServiceParent(service);
		serviceCall.setImportCommandParameters(serviceCallDTO.getImportCommandParameters());
		return serviceCall;
	}
}
