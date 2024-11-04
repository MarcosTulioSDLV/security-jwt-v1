package com.springboot.security_jwt_v1.repositories;

import com.springboot.security_jwt_v1.enums.RoleName;
import com.springboot.security_jwt_v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    boolean existsByRoleName(RoleName roleName);

}
