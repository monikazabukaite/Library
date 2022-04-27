package lt.vu.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@RequestScoped
@Named
        public class AntrasKomponentas {

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
