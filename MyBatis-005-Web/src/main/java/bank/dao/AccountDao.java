package bank.dao;

//账户的Dao对象，负责表中数据的CRUD

import bank.pojo.Account;

public interface AccountDao {

    Account selectByActno(String actno);

    int updateByActno(Account act);
}
