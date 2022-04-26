package lt.vu.controllers;

import lombok.Getter;
import lombok.Setter;
import lt.vu.dao.AuthorDAO;
import lt.vu.dao.BooksDAO;
import lt.vu.entities.Author;
import lt.vu.entities.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class BooksForAuthor implements Serializable {

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter
    @Setter
    private Author author;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorDAO.findOne(authorId);
    }

    @Transactional
    public void createBook() {
        bookToCreate.setAuthor(this.author);
        booksDAO.persist(bookToCreate);
    }
}
