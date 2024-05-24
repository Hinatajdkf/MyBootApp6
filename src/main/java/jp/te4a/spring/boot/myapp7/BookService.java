package jp.te4a.spring.boot.myapp7;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class BookService {//Repositoryを使ったサービス(機能)を提供

    @Autowired
    BookRepository bookRepository;

    public BookBean save(BookBean bookBean) {
        return bookRepository.save(bookBean);
    }

    public List<BookBean> findAll() {
        return bookRepository.findAll();
    }
}
