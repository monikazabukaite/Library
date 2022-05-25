package lt.vu.dao;

import lt.vu.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Alternative
public class BooksDaoImplWithLogging implements lt.vu.dao.interfaces.BooksDAO {

    @Inject
    private EntityManager em;

    public void persist(Book book) {
        System.out.println("Inserting book: " + book);
        this.em.persist(book);
    }

    public Book findOne(Integer id) {
        System.out.println("Retrieving book, id: " + id);
        return em.find(Book.class, id);
    }

    public Book update(Book book) {
        System.out.println("Updating book: " + book);
        return em.merge(book);
    }

    public void createLock(Book book) {
        book.setDescription(book.getDescription() + " ");
        em.merge(book);
    }
}
