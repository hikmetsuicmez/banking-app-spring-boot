package com.hikmetsuicmez.banking.service.impl;

import com.hikmetsuicmez.banking.dto.AccountDto;
import com.hikmetsuicmez.banking.entity.Account;
import com.hikmetsuicmez.banking.exception.ResourceNotFoundException;
import com.hikmetsuicmez.banking.mapper.AccountMapper;
import com.hikmetsuicmez.banking.repository.AccountRepository;
import com.hikmetsuicmez.banking.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDto> accountDtos = allAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

        return accountDtos;
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exists"));
        accountRepository.deleteById(id);

    }
}
