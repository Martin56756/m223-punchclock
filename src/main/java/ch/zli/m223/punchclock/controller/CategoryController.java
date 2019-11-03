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

    /**
     * Gets all categories from the database
     * @return All categories that have been found in the db
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    /**
     * Adds a new category to the database
     * @param category The category to add
     * @return The newly added category
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Deletes the category with the specified ID from the database
     * @param categoryId The ID of the category to delete
     */
    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("catId") long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    /**
     * Updates the specified category in the database
     * @param category The category to update
     * @return The updated category
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@Valid @RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
