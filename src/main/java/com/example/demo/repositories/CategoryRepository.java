package com.example.demo.repositories;

import com.example.demo.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String > {
}
