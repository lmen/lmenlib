package com.github.lmen.lib.simplemvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

@WebServlet( name = "HolidaysServlet", urlPatterns = { "/admin/holidays" } )
public class HolidaysServlet extends HttpServlet {

    private static final long serialVersionUID = 7033636731488402962L;

    private Logger logger;


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
            HolidaysRequestHandler handler = new HolidaysRequestHandler(  );
            handler.handleRequest( req, resp );
        } catch ( IOException e ) {
            logger.error("Error", e);
        }
    }

}
