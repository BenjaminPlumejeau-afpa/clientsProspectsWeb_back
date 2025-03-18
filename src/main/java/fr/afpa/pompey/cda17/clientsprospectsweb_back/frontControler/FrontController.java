package fr.afpa.pompey.cda17.clientsprospectsweb_back.frontControler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.ICommand;
import fr.afpa.pompey.cda17.clientsprospectsweb_back.controllers.PageAccueilController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/frontController")
public class FrontController extends HttpServlet {
    private Map commands = new HashMap();
    private final Logger LOGGER = Logger.getLogger(FrontController.class.getName());

    public void init() {
        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void destroy() {
    }


    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String urlSuite = "";

        try {
            String cmd = request.getParameter("cmd");
            ICommand command = (ICommand) commands.get(cmd);
            urlSuite = command.execute(request, response);
        } catch (Exception e) {
            urlSuite = "WEB-INF/JSP/erreur.jsp";
        } finally {
            try {
                request.getRequestDispatcher(urlSuite).forward(request, response);
            } catch (ServletException | IOException e) {
                LOGGER.severe("pb forward" + e.getMessage());
            }
        }

    }
}
