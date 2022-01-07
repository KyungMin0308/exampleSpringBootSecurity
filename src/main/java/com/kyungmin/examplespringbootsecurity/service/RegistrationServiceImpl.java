package com.kyungmin.examplespringbootsecurity.service;

import com.kyungmin.examplespringbootsecurity.entity.Role;
import com.kyungmin.examplespringbootsecurity.entity.User;
import com.kyungmin.examplespringbootsecurity.repo.RoleRepository;
import com.kyungmin.examplespringbootsecurity.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user, List<Role> userRoles) {
        for (Role ur : userRoles) {
            roleRepository.save(ur);
        }

        //패스워드 암호화 후 저장
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setRoles(userRoles);

        User newUser = userRepository.save(user);

        return newUser;
    }

    //이미 가입된 사용자인지 확인(Email 기반)
    public boolean checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return true;
        }

        return false;
    }

    //이미 가입된 사용자인지 확인(Role 기반)
    public Role findByRolename(String rolename) {
        Optional<Role> role = roleRepository.findByRolename(rolename);
        return role.orElseGet(() -> new Role("ROLE_USER"));
    }

}
