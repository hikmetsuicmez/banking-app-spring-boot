package com.hikmetsuicmez.banking.service;

import com.hikmetsuicmez.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    List<AccountDto> getAllAccounts();
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void deleteAccount(Long id);
}
