package com.ritik.dreamshop.controller.user;


import com.ritik.dreamshop.dto.user.UserDto;
import com.ritik.dreamshop.exception.AlreadyExistsException;
import com.ritik.dreamshop.exception.ResourceNotFoundException;
import com.ritik.dreamshop.model.user.User;
import com.ritik.dreamshop.request.user.CreateUserRequest;
import com.ritik.dreamshop.request.user.UpdateUserRequest;
import com.ritik.dreamshop.response.ApiResponse;
import com.ritik.dreamshop.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
        try{
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertToDto(user);
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request){
        try{
            User user = userService.createUser(request);
            UserDto userDto = userService.convertToDto(user);
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } catch ( AlreadyExistsException e ){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request,@PathVariable Long userId){
        try{
            User user = userService.updateUser(request, userId);
            UserDto userDto = userService.convertToDto(user);
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } catch ( ResourceNotFoundException e ){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch ( ResourceNotFoundException e ){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }



}
