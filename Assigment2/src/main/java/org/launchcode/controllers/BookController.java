package org.launchcode.controllers;
import org.launchcode.data.BookCategoryRepository;
import org.launchcode.data.BookRepository;
import org.launchcode.data.TagRepository;
import org.launchcode.models.Book;
import org.launchcode.models.BookCategory;
import org.launchcode.models.Tag;
import org.launchcode.models.dto.BookTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllBooks(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null) {
            model.addAttribute("title", "All books!");
            model.addAttribute("books", bookRepository.findAll());
        } else{
            Optional<BookCategory> result = bookCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid category: " + categoryId);
            }else{
                BookCategory category = result.get();
                model.addAttribute("title", "Books in category: " + category.getName());
                model.addAttribute("books", category.getBooks());
            }
        }
        return "books/index";

    }

    @GetMapping("create")
    public String renderCreateBookForm(Model model){
        model.addAttribute("title", "Create Book");
        model.addAttribute(new Book());
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return "books/create";
    }

    @PostMapping("create")
    public String createBook(@ModelAttribute @Valid Book newBook, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Book");
            return "books/create";
        }
        bookRepository.save(newBook);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteBookForm(Model model){
        model.addAttribute("title", "Delete Books");
        model.addAttribute("books",bookRepository.findAll());
        return "books/delete";
    }

    @PostMapping("delete")
    public String processDeleteBookForm(@RequestParam(required = false) int[] bookIds){

        if(bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("update")
    public String displayUpdateBookForm(Model model){
        model.addAttribute("title", "Update Books");
        model.addAttribute(new Book());
        model.addAttribute("bookIds",bookRepository.findAll());
        model.addAttribute("categories",bookCategoryRepository.findAll());
        return "books/update";
    }

    @PostMapping("update")
    public String updateBook(@RequestParam(required = false) int[] bookIds, Model model, @ModelAttribute @Valid Book newBook, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Update Book");
            return "books/update";
        }

        if(bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }
        bookRepository.save(newBook);
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayBookDetails(@RequestParam Integer bookId, Model model){

        Optional<Book> result = bookRepository.findById(bookId);

        if(result.isEmpty()){
            model.addAttribute("title" + "Invalid book Id : " + bookId);
        }else{
            Book book = result.get();
            model.addAttribute("title", book.getName() + " details ");
            model.addAttribute("book", book);
            model.addAttribute("tags", book.getTags());
        }
        return "books/detail";
    }
    
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer bookId, Model model){
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();

        model.addAttribute("title", "Add tag to: " + book.getName());
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("book", book);
        BookTagDTO bookTag = new BookTagDTO();
        bookTag.setBook(book);
        model.addAttribute("bookTag", bookTag);

        return "books/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid BookTagDTO bookTag, Errors errors, Model model){
        if(!errors.hasErrors()){
            Book book = bookTag.getBook();
            Tag tag = bookTag.getTag();

            if( !book.getTags().contains(tag)){
                book.addTag(tag);
                bookRepository.save(book);
            }

            return "redirect:detail?bookId=" + book.getId();
        }

        return "books/add-tag";
    }

}
