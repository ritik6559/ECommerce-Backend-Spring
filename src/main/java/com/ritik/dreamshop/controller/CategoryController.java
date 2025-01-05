package com.ritik.dreamshop.controller;


import com.ritik.dreamshop.exception.AlreadyExistsException;
import com.ritik.dreamshop.model.Category;
import com.ritik.dreamshop.response.ApiResponse;
import com.ritik.dreamshop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController("${api.prefix}/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try{
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found", categories));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Success", theCategory));
        } catch(AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok().body(new ApiResponse("Found", category));
        } catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }






}
