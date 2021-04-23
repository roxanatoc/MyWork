package org.launchcode.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Book extends AbstractEntity{

    @NotBlank
    @Size(min = 3, max = 50,message = "Size should be between 3 and 50 characters!")
    private String name;

    @ManyToOne
    @NotNull(message = "Category is required")
    private BookCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private BookDetails bookDetails;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Book(String name, BookCategory category) {
        this.name = name;
        this.category = category;

    }

    public Book() {
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void addTag(Tag tag){
        this.tags.add(tag);
    }
    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory bookCategory) {
        this.category = bookCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
