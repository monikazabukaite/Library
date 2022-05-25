package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.dao.AuthorDAO;
import lt.vu.entities.Author;
import lt.vu.usecases.interceptor.LoggedInvocation;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ViewScoped
@Named
@LoggedInvocation
public class Authors implements Serializable {

    @Inject
    private AuthorDAO authorDAO;

    @Getter
    @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> authorList;

    @Getter
    private CompletableFuture<Integer> longTask;

    @Getter
    private String result;

    @PostConstruct
    public void init(){
        loadAllAuthors();
    }

    @Transactional
    public void createAuthor(){
        this.authorDAO.persist(authorToCreate);
    }

    public void startLongTask() {
        this.longTask = CompletableFuture.supplyAsync(this::workForRandomTime);
    }

    private void loadAllAuthors(){
        this.authorList = authorDAO.loadAll();
    }

    private Integer workForRandomTime() {
        int time = (int) ((Math.random() * (20000 - 1000)) + 1000);
        try {
            Thread.sleep(time); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        return time;
    }

    public String checkIfOver() throws InterruptedException, ExecutionException {
        if (this.longTask == null) {
            return null;
        } else if (this.longTask.isDone()) {
            return this.longTask.get().toString();
        }

        return "Not yet completed";
    }

}
