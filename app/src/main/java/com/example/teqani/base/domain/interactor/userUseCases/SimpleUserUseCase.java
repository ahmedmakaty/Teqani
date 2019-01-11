package com.example.teqani.base.domain.interactor.userUseCases;

import com.example.teqani.base.data.model.LoginResponse;
import com.example.teqani.base.domain.repository.UserRepository;

import javax.inject.Inject;

public class SimpleUserUseCase {
    UserRepository userRepository;

    @Inject
    public SimpleUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser() {
    }

    public LoginResponse getUser() {
        return userRepository.getUser();
    }
}
