package jp.te4a.spring.boot.myapp13.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import jp.te4a.spring.boot.myapp13.bean.UserBean;
import lombok.Data;

//User、権限、パスワード等を持つ
@Data
public class LoginUserDetalis extends User{
    //認証に使うユーザクラス
    private final UserBean user;

    //認証ユーザ作成(コンストラクタ)
    public LoginUserDetalis(UserBean userBean){
        super(userBean.getUsername(), userBean.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = userBean;
    }
}

//AuthorityUtils.createAuthorityList("ROLE_USER")
//ユーザに紐づけたい権限

//条件によって付与する権限を変える
/*
 * public class LoginUserDetails extends User {
    private final UserBean user;
    public LoginUserDetails(UserBean userBean) {
        List<GrantedAuthority> authList = null;
        if(管理者の条件) {
            authList = AuthorityUtils.createAuthorityList(“ROLE_ADMIN”, "ROLE_USER“, “ROLE_OTHER”);
        } else if(一般ユーザの条件) {
            authList = AuthorityUtils.createAuthorityList("ROLE_USER“, “ROLE_OTHER”);
        } else {
           authList = AuthorityUtils.createAuthorityList("ROLE_OTHER");
        }
        super(userBean.getUsername(), userBean.getPassword(), authList);));
        this.user = userBean;
    }
}

 */