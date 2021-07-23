package com.github.yuzhian.zero.authorization.service.impl;

import com.github.yuzhian.zero.authorization.dao.AccountRepository;
import com.github.yuzhian.zero.authorization.entity.Account;
import com.github.yuzhian.zero.authorization.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account getAccount(String account) {
        if (isMatch(account, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
            return accountRepository.getByEmail(account);
        if (isMatch(account, "^[1]\\d{10}$"))
            return accountRepository.getByPhone(account);
        return accountRepository.getByUsername(account);
    }

    private boolean isMatch(CharSequence chars, String regex) {
        return StringUtils.hasText(chars) && Pattern.matches(regex, chars);
    }
}
