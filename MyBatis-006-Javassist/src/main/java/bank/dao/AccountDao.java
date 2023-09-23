package bank.dao;

public interface AccountDao {

    void insert();

    int delete(String actno);

    int update(String actno, Double balance);

    String select(String actno);

}
