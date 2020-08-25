package controllers.products;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import models.Product;
import utils.DBUtil;

/**
 * Servlet implementation class ProductsUpdateServlet
 */
@WebServlet("/products/update")
public class ProductsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsUpdateServlet() {
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

			p.setCategory((Category)request.getSession().getAttribute("categories"));
			p.setName(request.getParameter("name"));
			p.setPrice(Integer.parseInt(request.getParameter("price")));
			p.setContent(request.getParameter("content"));
			p.setUpdated_at(new Timestamp(System.currentTimeMillis()));

			//Product Validatorをどうするか
			/*List<String> errors = ReportValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("report", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp");
                rd.forward(request, response);
            } else { */

			em.getTransaction().begin();
			em.getTransaction().commit();
			em.close();
			request.getSession().setAttribute("flush", "更新が完了しました。");

			request.getSession().removeAttribute("product_id");

			response.sendRedirect(request.getContextPath() + "/products/index");

		}
	}

}
