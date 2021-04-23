package org.launchcode.data;

import org.launchcode.models.BookCategory;
import org.springframework.data.repository.CrudRepository;

public interface BookCategoryRepository extends CrudRepository<BookCategory, Integer> {
}
