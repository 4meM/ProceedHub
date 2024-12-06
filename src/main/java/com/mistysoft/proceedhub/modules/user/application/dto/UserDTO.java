package com.mistysoft.proceedhub.modules.user.application.dto;

import com.mistysoft.proceedhub.modules.user.domain.Role;
import com.mistysoft.proceedhub.modules.user.domain.User;
import com.mistysoft.proceedhub.modules.user.domain.UserId;
import lombok.Getter;

import java.util.Set;

@Getter
public class UserDTO {
    private final UserId id;
    private final String username;
    private final String email;
    private final Set<Role> roles;
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

}