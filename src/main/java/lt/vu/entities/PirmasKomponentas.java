package lt.vu.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
//@SessionScoped

public class PirmasKomponentas {

    @Inject
    private AntrasKomponentas antrasKomponentas;

    public void getObject() {
        System.out.println(antrasKomponentas.getClass().getName());
    }

    public String sakykLabas() {
    return "Labas " + new Date() + " " + toString();
}

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}

