package com.iqbal.securitybasic.repository;

import com.iqbal.securitybasic.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}