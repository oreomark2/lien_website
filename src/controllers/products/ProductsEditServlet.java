package controllers.products;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import models.Product;
import utils.DBUtil;

/**
 * Servlet implementation class ProductsEditServlet
 */
@WebServlet("/products/edit")
public class ProductsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		List<Category> categoryList = em.createNamedQuery("getAllCategories", Category.class).getResultList();


		Product p = em.find(Product.class, Integer.parseInt(request.getParameter("id")));

		em.close();
		request.setAttribute("categories", categoryList);
		request.setAttribute("product", p);
		request.setAttribute("_token", request.getSession().getId());
		request.getSession().setAttribute("product_id", p.getId());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products/edit.jsp");
		rd.forward(request, response);
	}

}
