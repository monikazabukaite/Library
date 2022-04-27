package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.BookMapper;
import lt.vu.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class BooksMB {

    @Inject
    private BookMapper bookMapper;

    @Getter
    private List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        this.loadAllBooks();
    }

    private void loadAllBooks() {
        this.allBooks = bookMapper.selectAll();
    }

    @Transactional
    public String createBook() {
        bookMapper.insert(bookToCreate);
        return "/allBooks?faces-redirect=true";
    }
}