package jee.task22.servlet;

import jee.task22.dao.IMobileDao;
import jee.task22.dao.MobileDaoJDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private Logger logger = LogManager.getLogger(AppContextListener.class);

    @Inject
    private IMobileDao mobileDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String isDao = servletContext.getInitParameter("isDao");
        if (isDao.equals("true")) {
            servletContext.setAttribute("dao", mobileDao);
            logger.info("Added attribute DAO");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute("dao");
        logger.info("Removed attribute DAO");
    }
}
