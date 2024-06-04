package jp.te4a.spring.boot.myapp13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.te4a.spring.boot.myapp13.bean.UserBean;

//ユーザ登録処理(DB)
public interface UserRepository extends JpaRepository<UserBean, String> {
}
