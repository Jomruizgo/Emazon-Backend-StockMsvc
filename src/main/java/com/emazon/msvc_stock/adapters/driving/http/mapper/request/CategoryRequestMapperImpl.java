package com.emazon.msvc_stock.adapters.driving.http.mapper.request;

import com.emazon.msvc_stock.adapters.driving.http.dto.request.AddCategoryRequestDto;
import com.emazon.msvc_stock.domain.model.Category;

public class CategoryRequestMapperImpl implements ICategoryRequestMapper{
    @Override
    public Category addRequestToCategory(AddCategoryRequestDto addCategoryRequest) {
        if (addCategoryRequest == null) {
            return null;
        }

        Category category = new Category();
        category.setName(addCategoryRequest.getName());
        category.setDescription(addCategoryRequest.getDescription());

        return category;
    }
}
