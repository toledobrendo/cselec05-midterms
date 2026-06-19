package com.iacademy.cselec05.midterms.factory;

import com.iacademy.cselec05.midterms.repository.UserRepository;
import com.iacademy.cselec05.midterms.repository.impl.UserJdbcRepositoryImpl;
import com.iacademy.cselec05.midterms.service.CipherService;
import com.iacademy.cselec05.midterms.service.impl.Sha256CipherServiceImpl;

public class ObjectFactory {
    public static UserRepository getUserRepository() {
        return new UserJdbcRepositoryImpl();
    }

    public static CipherService getCipherService() {
        return new Sha256CipherServiceImpl();
    }
}
