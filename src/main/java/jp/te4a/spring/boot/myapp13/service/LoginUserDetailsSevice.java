package jp.te4a.spring.boot.myapp13.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jp.te4a.spring.boot.myapp13.bean.UserBean;
import jp.te4a.spring.boot.myapp13.security.LoginUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import jp.te4a.spring.boot.myapp13.repository.UserRepository;


@Service
public class LoginUserDetailsSevice implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username)throws
    UsernameNotFoundException{
        Optional<UserBean> opt = userRepository.findById(username);
        System.out.println("username:" + username);
        UserBean user = opt.orElseThrow(() -> new UsernameNotFoundException("The requested user is not found."));
        System.out.println(user.getPassword());
        String[] list = {"ROLE_ADMIN","ROLE_USER"};
        return new LoginUserDetails(user,AuthorityUtils.createAuthorityList(list));
    }

    
}