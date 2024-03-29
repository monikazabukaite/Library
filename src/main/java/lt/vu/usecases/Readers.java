package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Reader;
import lt.vu.dao.ReadersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Model
public class Readers implements Serializable {

    @Inject
    private ReadersDAO readersDAO;

    @Getter @Setter
    private Reader readerToCreate = new Reader();

    @Getter
    private List<Reader> readerList;

    @PostConstruct
    public void init(){
        loadAllReaders();
    }

    @Transactional
    public void createUser(){
        this.readersDAO.persist(readerToCreate);
    }

    private void loadAllReaders(){
        this.readerList = readersDAO.loadAll();
    }
}