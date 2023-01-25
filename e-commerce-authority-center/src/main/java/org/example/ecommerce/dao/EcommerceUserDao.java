package org.example.ecommerce.dao;

import org.example.ecommerce.entity.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EcommerceUserDao extends JpaRepository<EcommerceUser, Long> {
    EcommerceUser findByUsername(String username);
    EcommerceUser findByUsernameAndPassword(String username, String password);
}
