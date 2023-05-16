package com.federicogualdi.shipmenthub.services;

import com.federicogualdi.shipmenthub.entities.Supplier;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import liquibase.util.StringUtil;

@ApplicationScoped
public class AuthService {

    public Supplier getSupplier(String jwt) {
        assertJwtValidity(jwt);
        // fake call to Authorization Service that return Supplier Info
        return Supplier.findAll().firstResult();
    }

    private void assertJwtValidity(String jwt) {
        if (!isJwtValid(jwt)) {
            throw new ForbiddenException();
        }
    }

    private boolean isJwtValid(String jwt) {
        return StringUtil.isNotEmpty(jwt);
    }
}
