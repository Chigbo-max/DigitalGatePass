package org.bytebuilders.services.role;

import org.bytebuilders.data.model.User;

public interface RoleServiceFactory {

    RoleService getService(User user);
}
