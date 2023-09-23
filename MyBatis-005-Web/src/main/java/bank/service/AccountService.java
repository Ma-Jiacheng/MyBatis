package bank.service;

import bank.exception.MoneyNotEnoughException;
import bank.exception.TransferException;

public interface AccountService {
    /**
     * 账户转账业务
     * @param fromActno 转出账户
     * @param toActno 转入账户
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
