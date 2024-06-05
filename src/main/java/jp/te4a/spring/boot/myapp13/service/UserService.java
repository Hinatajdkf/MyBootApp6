package jp.te4a.spring.boot.myapp13.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.myapp13.bean.UserBean;
import jp.te4a.spring.boot.myapp13.form.UserForm;
import jp.te4a.spring.boot.myapp13.repository.UserRepository;

//ユーザ登録処理(画面→DB)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //ユーザ作成時にパスワードをエンコードする
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserForm create(UserForm userForm) {
        //ユーザ作成時にパスワードをエンコードする{
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

        //画面用ユーザ情報(Form) → DB用ユーザ情報(Bean)
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);

        //ユーザをDBに追加
        userRepository.save(userBean);
        return userForm;
    }
}
