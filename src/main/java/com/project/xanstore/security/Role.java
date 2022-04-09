package com.project.xanstore.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.project.xanstore.security.Permision.ADMIN_READ;
import static com.project.xanstore.security.Permision.ADMIN_WRITE;


public enum Role {
   ADMIN(Sets.newHashSet(ADMIN_READ,ADMIN_WRITE));
    private final Set<Permision> permisionsSet;

    Role(Set<Permision> permisionsSet) {
        this.permisionsSet = permisionsSet;
    }

    public Set<Permision> getPermisionsSet() {
        return permisionsSet;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
Set<SimpleGrantedAuthority> permisions = getPermisionsSet().stream()
                .map(permision -> new SimpleGrantedAuthority(permision.getPermisionInfo()))
        .collect(Collectors.toSet());

        return permisions;
    }
}
