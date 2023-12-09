package com.example.dataaccess.repository.impl.moz;

import com.example.dataaccess.model.Account;
import com.example.dataaccess.repository.api.CRURepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountMozJpaRepository extends JpaRepository<Account, Long>, CRURepository<Account, Long> {
}
