package com.badbanana.poll.api.service;

import com.badbanana.poll.api.DTO.UserDTO;
import com.badbanana.poll.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    User update(User user, UserDTO params);

    Optional<User> findUser(Long id);

    Optional<User> findUserByNickname(String nickname);

    Optional<User> findUserByUsername(String username);

    List<User> findAllAdmin();

    List<User> findAllReviewer();

    List<User> findAllUser();

    User createUser(UserDTO userDTO);
}
