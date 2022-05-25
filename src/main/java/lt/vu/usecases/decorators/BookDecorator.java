package lt.vu.usecases.decorators;

import lt.vu.dao.interfaces.BooksDAO;
import lt.vu.entities.Book;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class BookDecorator implements BooksDAO {

    @Inject @Delegate @Any
    BooksDAO booksDAO;

    public Book findOne(Integer id) {
        Book book = booksDAO.findOne(id);
        if (book.getDescription() == null || book.getDescription().isEmpty()) {
            book.setDescription("No description");
        }
        return book;
    }
}
