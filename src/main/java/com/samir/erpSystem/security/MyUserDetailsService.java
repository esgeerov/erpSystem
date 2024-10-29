package com.samir.erpSystem.security;

import com.samir.erpSystem.entity.Users;
import com.samir.erpSystem.myenum.EnumAviable;
import com.samir.erpSystem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UsersRepository usersReposirtory;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=usersReposirtory.findUsersByEmailAndActive(username, EnumAviable.ACTIVE.value);
        if (user==null){
            throw   new UsernameNotFoundException("Not found "+ username);
        }
        MyUserDetails myUserDetails=new MyUserDetails(user);

        return myUserDetails;
    }
}
