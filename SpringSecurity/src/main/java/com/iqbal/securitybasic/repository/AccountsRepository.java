package com.iqbal.securitybasic.repository;

import com.iqbal.securitybasic.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

    Accounts findByCustomerId(int customerId);
}