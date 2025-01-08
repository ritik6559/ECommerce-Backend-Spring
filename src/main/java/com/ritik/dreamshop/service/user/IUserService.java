package com.ritik.dreamshop.service.user;

import com.ritik.dreamshop.model.user.User;
import com.ritik.dreamshop.request.user.CreateUserRequest;
import com.ritik.dreamshop.request.user.UpdateUserRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

}
