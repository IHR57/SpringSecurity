package com.iqbal.securitybasic.services;

import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsTest {

    @InjectMocks
    private MyUserDetails myUserDetails;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Checking with Empty User Name")
    public void testLoadByUserNameWithEmptyUserName() {
        assertThrows(InvalidParameterException.class, () -> myUserDetails.loadUserByUsername(""));
    }

    @Test
    @DisplayName("Checking with Null User Name")
    public void testLoadByUserNameWithNullUserName() {
        assertThrows(InvalidParameterException.class, () -> myUserDetails.loadUserByUsername(null));
    }

    @Test
    public void testLoadByUserNameUserNameNotFound() {
        when(customerRepository.findByEmail(anyString()))
                .thenReturn(new ArrayList<>());

        assertThrows(UsernameNotFoundException.class, () -> myUserDetails.loadUserByUsername("test"));
    }

    @Test
    public void testLoadByUserNameUserFound() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "test", "123456", "admin"));

        when(customerRepository.findByEmail(anyString()))
                .thenReturn(customers);

        User result = (User) myUserDetails.loadUserByUsername("test");

        verify(customerRepository, times(1))
                .findByEmail(anyString());

        assertAll("properties",
                () -> assertEquals("test", result.getUsername()),
                () -> assertEquals("123456", result.getPassword())
        );
    }

}