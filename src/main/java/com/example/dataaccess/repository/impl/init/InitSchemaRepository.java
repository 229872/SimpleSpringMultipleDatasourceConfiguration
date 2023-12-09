package com.example.dataaccess.repository.impl.init;

import com.example.dataaccess.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InitSchemaRepository extends JpaRepository<AbstractEntity, Long> {
}
