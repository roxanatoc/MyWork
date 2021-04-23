package org.launchcode.models.dto;

import org.launchcode.models.Book;
import org.launchcode.models.Tag;

import javax.validation.constraints.NotNull;

public class BookTagDTO {

    @NotNull
    private Book book;

    @NotNull
    private Tag tag;

    public BookTagDTO() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
