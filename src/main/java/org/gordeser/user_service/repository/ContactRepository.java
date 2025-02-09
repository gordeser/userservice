package org.gordeser.user_service.repository;

import org.gordeser.user_service.entitiy.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}