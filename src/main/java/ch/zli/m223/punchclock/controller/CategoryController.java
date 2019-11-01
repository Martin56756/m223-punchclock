package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService service) {
        categoryService = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("catId") long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@Valid @RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
