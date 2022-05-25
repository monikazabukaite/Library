package lt.vu.dao.interfaces;

import lt.vu.entities.Book;

public interface BooksDAO {

    void persist(Book book);

    Book findOne(Integer id);

    Book update(Book book);

    void createLock(Book book);
}