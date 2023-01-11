package ru.rsatu.hibernatetutorial;

import io.quarkus.runtime.StartupEvent;
import ru.rsatu.hibernatetutorial.pojo.entity.User;

import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

public class Main {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        User.deleteAll();
        User.add("admin", "admin", "admin");
        User.add("user", "user", "user");
        User.add("test", "test", "user");
    }
}
