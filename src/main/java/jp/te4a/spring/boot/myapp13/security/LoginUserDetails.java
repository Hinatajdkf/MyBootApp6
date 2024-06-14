package jp.te4a.spring.boot.myapp13.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import jp.te4a.spring.boot.myapp13.bean.UserBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

// パスワードや権限を含む形式でユーザ情報を保持する
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUserDetails extends User{
    
    // 認証に使うユーザクラス
    private final UserBean user;

    // 認証ユーザ作成(コンストラクタ)
    public LoginUserDetails(UserBean userBean,List<GrantedAuthority> authList ){
        super(userBean.getUsername(),userBean.getPassword(), authList);
        this.user=userBean;
    }
}