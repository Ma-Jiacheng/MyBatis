package bank.web;

import bank.exception.MoneyNotEnoughException;
import bank.exception.TransferException;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表单数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");

        double money = Double.parseDouble(request.getParameter("money"));
        //调用service的转账方法完成转账
        try {
            accountService.transfer(fromActno, toActno, money);
            //完成转账，调用视图完成展示
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/failed.html");
        } catch (TransferException e) {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
