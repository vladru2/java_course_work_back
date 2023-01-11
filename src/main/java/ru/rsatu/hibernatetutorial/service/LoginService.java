package ru.rsatu.hibernatetutorial.service;

import ru.rsatu.hibernatetutorial.pojo.entity.User;
import ru.rsatu.hibernatetutorial.repository.LoginRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class LoginService {

    @Inject
    LoginRepository loginRepository;

    public List<User> getUser(String name) {
        return loginRepository.getUser(name);
    }
}