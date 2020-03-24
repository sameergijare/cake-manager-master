package com.waracle.cakemgr;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {CakeConstants.HOME_URL_PATTERN, CakeConstants.CAKES_URL_PATTERN})
public class CakeServlet extends HttpServlet {

    private CakeService cakeService = new CakeService();

    @Override
    public void init() throws ServletException {
        super.init();
        loadInitialCakes();
    }

    private void loadInitialCakes() throws ServletException {
        System.out.println("Cake initialisation started");
        List<CakeEntity> initialCakes = new ArrayList(cakeService.getInitialCakes());
        cakeService.saveCakes(initialCakes);
        System.out.println("Cake initialisation complete");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CakeConstants.JSON_CONTENT_TYPE.equals(request.getContentType()) ||
                request.getHeader("Accept").contains(CakeConstants.JSON_CONTENT_TYPE)) {
            new ObjectMapper().writeValue(response.getOutputStream(), cakeService.getAllCakes());
        } else {
            RequestDispatcher view = request.getRequestDispatcher(CakeConstants.HTML_REDIRECT_SUFFIX);
            view.forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CakeEntity newCake = new CakeEntity();
        newCake.setTitle(request.getParameter(CakeConstants.TITLE_PARAM));
        newCake.setDescription(request.getParameter(CakeConstants.DESCRIPTION_PARAM));
        newCake.setImageUrl(request.getParameter(CakeConstants.IMAGE_PARAM));
        cakeService.saveCake(newCake);
    }

    @Override
    public void destroy() {
        super.destroy();
        HibernateUtil.shutdown();
    }
}
