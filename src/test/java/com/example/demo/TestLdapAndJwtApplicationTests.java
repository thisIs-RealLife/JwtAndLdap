package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class TestLdapAndJwtApplicationTests {

    @Autowired
    private final AuthenticationManager authenticationManager;

    TestLdapAndJwtApplicationTests(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Test
    void contextLoads() {
        Collection<? extends GrantedAuthority> list = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("ben", "benspassword")).getAuthorities();
        for (GrantedAuthority g :
                list) {
            System.out.println(g.getAuthority());
        }
    }

}
