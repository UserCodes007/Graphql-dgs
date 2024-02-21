package com.example.GraphqlDgs.Repository;

import com.example.GraphqlDgs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {}
