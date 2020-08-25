package controllers.products;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product;
import utils.DBUtil;

/**
 * Servlet implementation class ProductsDestroyServlet
 */
@WebServlet("/products/destroy")
public class ProductsDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())){
			EntityManager em = DBUtil.createEntityManager();

			Product p = em.find(Product.class, (Integer)(request.getSession().getAttribute("product_id")));

			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			em.close();
			request.getSession().setAttribute("flush","削除が完了しました。");

			response.sendRedirect(request.getContextPath() + "/products/index");
		}
	}

}
