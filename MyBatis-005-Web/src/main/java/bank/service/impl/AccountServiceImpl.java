package bank.service.impl;

import bank.dao.AccountDao;
import bank.dao.impl.AccountDaoImpl;
import bank.exception.MoneyNotEnoughException;
import bank.exception.TransferException;
import bank.pojo.Account;
import bank.service.AccountService;
import bank.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;


public class AccountServiceImpl implements AccountService {

    //AccountDao accountDao = new AccountDaoImpl();

    //在MyBatis中提供了相关机制，可以动态的为我们生成dao接口的实现类（代理类：dao接口的代理）
    //MyBatis当中实际上采用了代理模式，在内存中生成dao接口的代理，然后创建代理类的实例
    //使用MyBatis的这种代理机制模式的前提：SqlMapper.xml文件中namespace必须是dao接口的全限定名称，id必须是接口中的dao方法名
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {

        //添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();

        //判断转出账户的余额是否充足(select)
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            //余额不足，提示用户
            throw new MoneyNotEnoughException("对不起，余额不足！");
        }
        //余额充足，先更新内存余额
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        //更新数据库余额
        int count = accountDao.updateByActno(fromAct);
        count += accountDao.updateByActno(toAct);

        if (count != 2){
            throw new TransferException("转账异常，未知原因！");
        }

        //提交、关闭事务
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
