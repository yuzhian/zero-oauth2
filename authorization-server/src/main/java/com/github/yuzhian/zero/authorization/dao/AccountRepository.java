package com.github.yuzhian.zero.authorization.dao;

import com.github.yuzhian.zero.authorization.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    Account getByUsername(String username);

    Account getByEmail(String email);

    Account getByPhone(String phone);
}
