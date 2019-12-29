package jee.task22.servlet.mobiles;

import ConnectionManager.ConnectionManager;
import jee.task22.dao.IMobileDao;
import jee.task22.dao.MobileDaoJDBC;
import jee.task22.pojo.Mobile;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addmobile")
public class AddMobileServlet extends HttpServlet {

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
        req.setAttribute("PageTitle", "New Mobiles");
        req.setAttribute("PageBody", "form.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(null, model, Integer.valueOf(price), manufacturer);
        mobileDao.addMobile(mobile);

        resp.sendRedirect(req.getContextPath() + "/layout");
    }
}