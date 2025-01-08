package com.ritik.dreamshop.service.user;

import com.ritik.dreamshop.model.user.User;
import com.ritik.dreamshop.request.user.CreateUserRequest;
import com.ritik.dreamshop.request.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
