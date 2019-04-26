package com.slib.var.webapp.pages.holidays;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slib.tek.logging.Logger;
import com.slib.var.core.history.config.HistoryConfig;
import com.slib.var.core.holidays.HolidaysStore;

@WebServlet( name = "HolidaysServlet", urlPatterns = { "/admin/holidays" } )
public class HolidaysServlet extends HttpServlet {

    private static final long serialVersionUID = 7033636731488402962L;

    @Inject
    private Logger logger;

    @Inject
    private HolidaysStore holidaysStore;
    
    @Inject
    private HistoryConfig historyConfig;    

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        handleHttpRequest( req, resp );

    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        handleHttpRequest( req, resp );

    }
    
    private void handleHttpRequest( HttpServletRequest req, HttpServletResponse resp )  {
        try {
            HolidaysRequestHandler handler = new HolidaysRequestHandler( historyConfig, holidaysStore );
            handler.handleRequest( req, resp );
        } catch ( IOException e ) {
            logger.error("Error", e);
        }
    }

}
