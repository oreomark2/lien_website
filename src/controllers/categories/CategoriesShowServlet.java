package controllers.categories;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import utils.DBUtil;

/**
 * Servlet implementation class CategoriesShowServlet
 */
@WebServlet("/categories/show")
public class CategoriesShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Category c = em.find(Category.class, Integer.parseInt(request.getParameter("id")));

		em.close();

		request.setAttribute("category", c);

		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/categories/show.jsp");
		rd.forward(request, response);
	}

}
