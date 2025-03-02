package org.openapitools.service;

import lombok.RequiredArgsConstructor;
import org.openapitools.enums.Rol;
import org.openapitools.enums.UserStatus;
import org.openapitools.exceptions.ValueConflictException;
import org.openapitools.mappers.UserMapper;
import org.openapitools.model.User;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Map<String, User> userStore = new ConcurrentHashMap<>();
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRegistration userRegistration) {
        if (userStore.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(userRegistration.getEmail()))) {
            throw new ValueConflictException("Email ya registrado");

        }

        User newUser = userMapper.toUser(userRegistration);
        newUser.setId(UUID.randomUUID().toString());
        userStore.put(newUser.getId(), newUser);

        return userMapper.toUserResponse(newUser);
    }

    @Override
    public Optional<UserResponse> getUser(String id) {
        return Optional.ofNullable(userStore.get(id))
                .map(userMapper::toUserResponse);
    }
}