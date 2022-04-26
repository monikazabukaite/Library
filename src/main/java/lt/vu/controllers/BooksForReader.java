package lt.vu.controllers;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.Author;
import lt.vu.entities.Reader;
import lt.vu.dao.BooksDAO;
import lt.vu.dao.AuthorDAO;
import lt.vu.dao.ReadersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
@Getter
@Setter
public class BooksForReader {

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private ReadersDAO readersDAO;

    @Inject
    private AuthorDAO authorDAO;

    private Reader reader;
    private Integer bookId;
    private String result;

    private List<Author> authorList;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer readerId = Integer.parseInt(requestParameters.get("readerId"));

        this.reader = readersDAO.findOne(readerId);
        this.authorList = authorDAO.loadAll();
    }

    @Transactional
    public void createOrder() {
        System.out.println("bookId");
        Book orderedBook = booksDAO.findOne(bookId);

        if (orderedBook == null) {
            result = "This book doesn't exist";
        }

        reader.getBooks().add(orderedBook);
        readersDAO.persist(reader);
    }
}