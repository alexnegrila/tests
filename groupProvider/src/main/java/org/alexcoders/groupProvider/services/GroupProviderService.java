package org.alexcoders.groupProvider.services;

import java.util.List;

import org.alexcoders.groupProvider.dtos.GroupProviderDTO;
import org.alexcoders.groupProvider.dtos.ServiceCallDTO;
import org.alexcoders.groupProvider.dtos.ServiceDTO;
import org.alexcoders.groupProvider.models.GroupProvider;
import org.alexcoders.groupProvider.models.Service;
import org.alexcoders.groupProvider.models.ServiceCall;

public interface GroupProviderService {

	GroupProvider save(GroupProviderDTO groupProvider);

	List<ServiceCallDTO> getServiceCalls(Long groupId, String serviceId);

	ServiceDTO getService(Long groupId, String serviceId);

	List<ServiceDTO> getServices(Long groupId);
}
