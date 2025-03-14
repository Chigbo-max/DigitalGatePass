package org.bytebuilders.services.role;

import org.bytebuilders.data.model.User;
import org.bytebuilders.data.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleServiceFactoryImplement implements RoleServiceFactory {

    private final Map<Role, RoleService> roleServices = new HashMap<>();


    public RoleServiceFactoryImplement(List<RoleService> services) {
        services.forEach(service -> roleServices.put(service.getRole(), service));
    }

    @Override
    public RoleService getService(User user) {
        RoleService service = roleServices.get(user.getRole());
        if (service == null) {
            throw new IllegalArgumentException("Role does not exist");        }
        else return service;
    }
}
