package com.kyungmin.examplespringbootsecurity.service;

import com.kyungmin.examplespringbootsecurity.entity.Role;
import com.kyungmin.examplespringbootsecurity.entity.User;

import java.util.List;

public interface RegistrationService { //회원가입을 위한 Service Interface

    User createUser(User user, List<Role> userRoles); //사용자 생성

    boolean checkEmailExists(String email); //이미 가입된 사용자인지 확인(Email 기반)

    Role findByRolename(String rolename); //이미 가입된 사용자인지 확인(Role 기반)
}
