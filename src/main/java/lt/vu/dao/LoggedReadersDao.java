package lt.vu.dao;

import lt.vu.entities.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class LoggedReadersDao extends ReadersDAO {

    @Override
    public void persist(Reader reader){
        System.out.println("Book is being added: " + reader);
        super.persist(reader);
    }
}
