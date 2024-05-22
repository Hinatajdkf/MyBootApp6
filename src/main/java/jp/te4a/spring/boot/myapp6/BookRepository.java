package jp.te4a.spring.boot.myapp6;

import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.*;

@Repository
public class BookRepository {//データ(BookBean)を取り扱う(保存等)
    private final ConcurrentMap<Integer, BookBean>bookMap = new ConcurrentHashMap<>();

    public BookBean save(BookBean bookBean){//保存用メソッド
        return bookMap.put(bookBean.getId(),bookBean);
    }

    public void delete(Integer bookId) {//削除用メソッド
        bookMap.remove(bookId);
    }

    public List<BookBean> findAll() {//(全件)取得用メソッド
        return new ArrayList<>(bookMap.values());
    }
}
