package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookCategory extends AbstractEntity{

    @Size(min = 3, message = "name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "category")
    private final List<Book> books = new ArrayList<>();

    public BookCategory(@Size(min = 3, message = "name must be at least 3 characters long") String name){
        this.name = name;
    }

    public BookCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
