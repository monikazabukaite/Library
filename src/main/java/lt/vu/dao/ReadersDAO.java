package lt.vu.dao;

import lt.vu.entities.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ReadersDAO {

    @Inject
    private EntityManager em;

    public List<Reader> loadAll() {
        return em.createNamedQuery("Reader.findAll", Reader.class).getResultList();
    }

    public void persist(Reader reader) {
        this.em.persist(reader);
    }

    public Reader findOne(Integer id) {
        return em.find(Reader.class, id);
    }

    public Reader update(Reader reader) {
        return em.merge(reader);
    }
}
