package com.iqbal.securitybasic.services;

import com.iqbal.securitybasic.model.Authority;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(userName);
        if(customers.size() > 0) {
            if(passwordEncoder.matches(password, customers.get(0).getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(customers.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        } else {
            throw new BadCredentialsException("User is not registered");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authoritySet) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Authority authority : authoritySet) {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        }

        return authorities;
    }
}
