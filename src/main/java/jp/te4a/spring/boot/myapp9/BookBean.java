package jp.te4a.spring.boot.myapp9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor//引数なしコンストラクタ自動生成
//DB(テーブル)と連携
public class BookBean {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;
    private String title;
    private String writer;
    private String publisher;
    private Integer price;
}
