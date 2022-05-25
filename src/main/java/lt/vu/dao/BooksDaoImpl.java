package lt.vu.dao;

import lt.vu.dao.interfaces.BooksDAO;
import lt.vu.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Alternative
public class BooksDaoImpl implements BooksDAO {

    @Inject
    private EntityManager em;

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Integer id){
        return em.find(Book.class, id);
    }

    public Book update(Book book){
        return em.merge(book);
    }

    public void createLock(Book book) {
        book.setName(book.getDescription() + " ");
        em.merge(book);
    }
}
