package com.emazon.msvc_stock.adapters.driving.http.controller;

import com.emazon.msvc_stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.emazon.msvc_stock.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.msvc_stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.emazon.msvc_stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.emazon.msvc_stock.domain.api.ICategoryServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;



    public CategoryRestControllerAdapter(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper, ICategoryResponseMapper categoryResponseMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request){
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String order) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(
                categoryServicePort.listCategories(order, page, size)));
    }
}
