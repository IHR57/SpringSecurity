package com.iqbal.securitybasic.services;

import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyUserNamePasswordAuthenticationProviderTest {

    @InjectMocks
    MyUserNamePasswordAuthenticationProvider myUserNamePasswordAuthenticationProvider;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void authenticate_UserNameNotFound_ShouldThrowBadCredentialsException() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getCredentials()).thenReturn("test");
        when(authentication.getName()).thenReturn("test");

        when(customerRepository.findByEmail(anyString()))
                .thenReturn(new ArrayList<>());

        assertThrows(BadCredentialsException.class,
                () -> myUserNamePasswordAuthenticationProvider.authenticate(authentication));
    }

    @Test
    void authenticate_InvalidPassword_ShouldThrowBadCredentialsException() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getCredentials()).thenReturn("test");
        when(authentication.getName()).thenReturn("test");

        when(customerRepository.findByEmail(anyString()))
                .thenReturn(List.of(new Customer()));

        when(passwordEncoder.matches(any(CharSequence.class), anyString()))
                .thenReturn(false);

        assertThrows(BadCredentialsException.class,
                () -> myUserNamePasswordAuthenticationProvider.authenticate(authentication));
    }

    @Test
    void authenticate_ValidUserNamePassword_ShouldSuccess() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getCredentials()).thenReturn("test");
        when(authentication.getName()).thenReturn("test");

        when(customerRepository.findByEmail(anyString()))
                .thenReturn(List.of(new Customer()));

        when(passwordEncoder.matches(any(CharSequence.class), anyString()))
                .thenReturn(true);

        UsernamePasswordAuthenticationToken result = (UsernamePasswordAuthenticationToken) myUserNamePasswordAuthenticationProvider.authenticate(authentication);

        verify(passwordEncoder, times(1))
                .matches(any(CharSequence.class), anyString());
    }
}