package org.launchcode.controllers;

import org.launchcode.data.BookCategoryRepository;
import org.launchcode.models.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class BookCategoryController {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @GetMapping("bookCategory")
    public String displayAllCategories(Model model){
        model.addAttribute("categories", bookCategoryRepository.findAll());
        model.addAttribute("title", "All categories!");
        return "bookCategory/index";
    }

    @RequestMapping("bookCategory/create")
    public String displayCreateCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new BookCategory());
        return "bookCategory/create";
    }

    @PostMapping("bookCategory/create")
    public String processCreateCategoryForm(@ModelAttribute @Valid BookCategory bookCategory, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Book");
            return "bookCategory/create";
        }
        bookCategoryRepository.save(bookCategory);
        return "redirect:";
    }
}
