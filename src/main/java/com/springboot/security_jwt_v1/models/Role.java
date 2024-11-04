package com.springboot.security_jwt_v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.security_jwt_v1.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "users")
//@EqualsAndHashCode(exclude = "users")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.REMOVE)
    private List<UserEntity> users= new ArrayList<>();

    public Role(){
    }

    @Override
    public String getAuthority() {
        return roleName.toString();
    }
}
