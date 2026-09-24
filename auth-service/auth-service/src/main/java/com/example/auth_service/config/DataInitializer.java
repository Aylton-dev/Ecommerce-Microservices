package com.example.auth_service.config;

import com.example.auth_service.model.Role;
import com.example.auth_service.model.RoleName;
import com.example.auth_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args){
        createRoleIfNotExists(RoleName.ROLE_USER);
        createRoleIfNotExists(RoleName.ROLE_ADMIN);
    }
    private void createRoleIfNotExists(RoleName roleName){
        if(roleRepository.findByRoleName(roleName).isEmpty()){
            Role role = new Role();
            role.setRoleName(roleName);
            roleRepository.save(role);
        }
    }
}
