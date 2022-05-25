package lt.vu.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Reader;

@Getter @Setter
public class ReaderDTO {

    private Integer id;

    private String name;

    public ReaderDTO() {
    }

    public ReaderDTO(Reader reader) {
        this.id = reader.getId();
        this.name = reader.getName();
    }
}
