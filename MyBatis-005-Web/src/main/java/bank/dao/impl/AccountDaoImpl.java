package bank.dao.impl;

import bank.dao.AccountDao;
import bank.pojo.Account;
import bank.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.selectOne("account.selectByActno", actno);
    }

    @Override
    public int updateByActno(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.update("account.updateByActno", act);
    }
}
