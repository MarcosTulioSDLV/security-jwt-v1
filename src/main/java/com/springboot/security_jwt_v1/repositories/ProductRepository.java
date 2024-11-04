package com.springboot.security_jwt_v1.repositories;

import com.springboot.security_jwt_v1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByProductCode(String productCode);

}
