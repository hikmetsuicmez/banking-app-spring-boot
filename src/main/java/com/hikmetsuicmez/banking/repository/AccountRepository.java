package com.hikmetsuicmez.banking.repository;

import com.hikmetsuicmez.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
