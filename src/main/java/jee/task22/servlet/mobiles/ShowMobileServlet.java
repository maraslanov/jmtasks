package jee.task22.servlet.mobiles;

import ConnectionManager.ConnectionManager;
import jee.task22.dao.IMobileDao;
import jee.task22.dao.MobileDaoJDBC;
import jee.task22.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showmobile")
public class ShowMobileServlet extends HttpServlet {

    private IMobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        //mobileDao = (IMobileDao) getServletContext().getAttribute("dao");
        super.init();
        ConnectionManager cm = ConnectionManager.getInstance();
        mobileDao = new MobileDaoJDBC(cm);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = mobileDao.getMobileById(Integer.valueOf(mobileId));
        if (mobile == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("mobile", mobile);
        req.getRequestDispatcher("/showmobile.jsp").forward(req, resp);
    }
}