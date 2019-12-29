package jee.task22.servlet.user;

import ConnectionManager.ConnectionManager;
import jee.task22.dao.IUserDao;
import jee.task22.dao.UserDaoJDBC;
import jee.task22.pojo.UserPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorisation")
public class AuthorisationServlet extends HttpServlet {

    private IUserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionManager cm = ConnectionManager.getInstance();
        userDao = new UserDaoJDBC(cm);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New User");
        req.setAttribute("PageBody", "form.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String button = req.getParameter("button");
        String login = req.getParameter("login");
        String pswd = req.getParameter("password");
        if ("regbutton".equals(button)) {
            //с веб страницы создаем нового пользователя
            UserPojo user = new UserPojo(null, login, pswd);
            userDao.addUser(user);
            resp.sendRedirect(req.getContextPath() + "/main.jsp");
        }
        if ("signinbutton".equals(button)) {
            //авторизуемся
            if (userDao.getUserByParam(login, pswd)!=null){
                resp.sendRedirect(req.getContextPath() + "/main.jsp");
            }
        }

    }
}