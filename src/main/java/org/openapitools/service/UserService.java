package org.openapitools.service;

import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserRegistration user);
    Optional<UserResponse> getUser(String id);
}