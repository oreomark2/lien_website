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
 * Servlet implementation class ProductsCreateServlet
 */
@WebServlet("/products/create")
public class ProductsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsCreateServlet() {
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

			Product p = new Product();
			Category c = em.find(Category.class, Integer.parseInt(request.getParameter("category")));

			p.setCategory(c);
			p.setName(request.getParameter("name"));
			p.setPrice(Integer.parseInt(request.getParameter("price")));
			p.setContent(request.getParameter("content"));

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			p.setCreated_at(currentTime);
			p.setUpdated_at(currentTime);

			//Product Validatorをどうするか
			/*List<String> errors = ReportValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("product", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
                rd.forward(request, response);
            } else { */

			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			em.close();
			request.getSession().setAttribute("flush", "登録が完了しました");

			response.sendRedirect(request.getContextPath() + "/products/index");


		}
	}

}
