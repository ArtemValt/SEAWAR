package com.example.bot.servies;

import com.example.bot.entity.Role;
import com.example.bot.entity.User;
import com.example.bot.repos.RoleRep;
import com.example.bot.repos.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRep userRepository;
    @Autowired
    RoleRep roleRepository;


    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(@Valid User user) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());

//        if (userFromDB != null) {
//            return false;
//        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}