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
import java.util.Collection;

@WebServlet(urlPatterns = "/allmobiles", name = "Mobiles")
public class AllMobilesServlet extends HttpServlet {

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
        Collection<Mobile> mobiles = mobileDao.getAllMobile();
        req.setAttribute("mobiles", mobiles);
        req.setAttribute("PageTitle", "Mobiles");
        req.setAttribute("PageBody", "allmobiles.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }
}