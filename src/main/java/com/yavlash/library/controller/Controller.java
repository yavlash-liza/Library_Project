package com.yavlash.library.controller;

import com.yavlash.library.controller.command.Command;
import com.yavlash.library.controller.command.CommandType;
import com.yavlash.library.controller.command.RequestParameter;
import com.yavlash.library.controller.command.Router;
import com.yavlash.library.controller.command.PagePath;
import com.yavlash.library.util.FlywayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() {
        FlywayService service = new FlywayService();
        service.migrate();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandType = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.defineCommand(commandType);
        Router router = command.execute(request);
        switch (router.getRouterType()) {
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPagePath());
                break;
            default:
                logger.error("Invalid router type");
                request.getRequestDispatcher(PagePath.ERROR_404).forward(request, response);
        }
    }
}