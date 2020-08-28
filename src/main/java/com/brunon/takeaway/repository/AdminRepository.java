package com.brunon.takeaway.repository;

import com.brunon.takeaway.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByLogin(String login);
}
