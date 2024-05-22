package jp.te4a.spring.boot.myapp6;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookBean {//データの入れ物
    Integer id;
    String title;
    String writer;
    String publisher;
    Integer price;
}
