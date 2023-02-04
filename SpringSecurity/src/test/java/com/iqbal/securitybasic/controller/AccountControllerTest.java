package com.iqbal.securitybasic.controller;

import com.iqbal.securitybasic.model.Accounts;
import com.iqbal.securitybasic.model.Customer;
import com.iqbal.securitybasic.repository.AccountsRepository;
import com.iqbal.securitybasic.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @InjectMocks
    AccountController accountController;

    @Mock
    AccountsRepository accountsRepository;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void getAccountDetails_accountNotFound_returnsNull() {
        when(accountsRepository.findByCustomerId(anyInt()))
                .thenReturn(null);

        Accounts accounts = accountController.getAccountDetails("test@234.com");

        verify(accountsRepository, times(1)).findByCustomerId(anyInt());
    }
}