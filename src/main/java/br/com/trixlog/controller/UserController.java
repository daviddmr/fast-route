package br.com.trixlog.controller;

import br.com.trixlog.model.Role;
import br.com.trixlog.model.User;
import br.com.trixlog.repository.RoleRepository;
import br.com.trixlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by kaynan on 21/01/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/login")
    public Principal login(Principal user) {
        return user;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        Role role = user.getRoles().get(0);
        if (roleRepository.findByName(role.getName()) == null) {
            roleRepository.save(role);
        }
        role = roleRepository.findByName(role.getName());
        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
