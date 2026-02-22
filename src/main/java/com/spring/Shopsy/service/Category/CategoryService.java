package com.spring.Shopsy.service.Category;

import com.spring.Shopsy.constant.message.GlobalExceptionMessage;
import com.spring.Shopsy.exception.ApiException;
import com.spring.Shopsy.exception.ResourceNotFoundException;
import com.spring.Shopsy.helper.Pagination;
import com.spring.Shopsy.model.Category;
import com.spring.Shopsy.payload.category.CategoryDTO;
import com.spring.Shopsy.payload.category.CategoryResponse;
import com.spring.Shopsy.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equals("asc")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> categorypage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categorypage.getContent();

        if(categories.isEmpty()){
            throw new ApiException(GlobalExceptionMessage.NO_CATEGORY_PRESENT);
        }

        List<CategoryDTO> response = categories.stream()
            .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .toList();


        Pagination paginationDetails = Helper.getPaginationDetails(categorypage);

        return new CategoryResponse(response, paginationDetails);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);

        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if(savedCategory != null){
            throw new ApiException(GlobalExceptionMessage.CATEGORY_ALREADY_EXIST + category.getCategoryName());
        }

        categoryRepository.save(category);

        return modelMapper.map(category, CategoryDTO.class);

    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return modelMapper.map(category.get(), CategoryDTO.class);
        }else {

            throw new ResourceNotFoundException("Category", "CategoryId", categoryId);

        }
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        Category savedOptionalCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));


        Category category = modelMapper.map(categoryDTO, Category.class);
        savedOptionalCategory.setCategoryName(category.getCategoryName());
        savedOptionalCategory = categoryRepository.save(savedOptionalCategory);

        return modelMapper.map(savedOptionalCategory, CategoryDTO.class);
    }
}
