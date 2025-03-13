package com.example.securityRevise.SecurityRevise.repository;

import com.example.securityRevise.SecurityRevise.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
