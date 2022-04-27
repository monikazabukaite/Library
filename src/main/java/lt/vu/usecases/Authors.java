package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.dao.AuthorDAO;
import lt.vu.entities.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {

    @Inject
    private AuthorDAO authorDAO;

    @Getter
    @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> authorList;

    @PostConstruct
    public void init(){
        loadAllAuthors();
    }

    @Transactional
    public void createAuthor(){
        this.authorDAO.persist(authorToCreate);
    }

    private void loadAllAuthors(){
        this.authorList = authorDAO.loadAll();
    }
}
