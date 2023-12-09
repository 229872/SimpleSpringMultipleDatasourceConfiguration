package com.example.dataaccess.repository.impl.moz;

import com.example.dataaccess.model.Product;
import com.example.dataaccess.repository.api.CRURepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductMozJpaRepository extends JpaRepository<Product, Long>, CRURepository<Product, Long> {
}
