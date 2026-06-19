package com.iacademy.cselec05.midterms.repository;

import com.iacademy.cselec05.midterms.model.User;

public interface UserRepository {
    User findByUsername(String username);

    User save(User user);
}
