package controllers.categories;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;

/**
 * Servlet implementation class CategoriesNewServlet
 */
@WebServlet("/categories/new")
public class CategoriesNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("category", new Category());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/categories/new.jsp");
		rd.forward(request, response);
	}

}
