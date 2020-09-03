package controllers.histories;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.Member;
import models.Product;
import utils.DBUtil;

/**
 * Servlet implementation class HistoriesPurchaseServlet
 */
@WebServlet("/histories/purchase")
public class HistoriesPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesPurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            History h = new History();
//            Member m = em.find(Member.class, Integer.parseInt(request.getParameter("member")));
            Product p = em.find(Product.class, Integer.parseInt(request.getParameter("id")));

            h.setMember((Member)request.getSession().getAttribute("login_member"));
            h.setProduct(p);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            h.setPurchased_at(currentTime);

//HistoryValidatorをどうするか
//            List<String> errors = HistoryValidator.validate(h, true, true);
//            if(errors.size() > 0) {
//
//
//                request.setAttribute("_token", request.getSession().getId());
//                request.setAttribute("history", h);
//                request.setAttribute("errors", errors);
//
//                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products/show.jsp");
//                rd.forward(request, response);
//            } else {

                em.getTransaction().begin();
                em.persist(h);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "購入が完了しました。");

                response.sendRedirect(request.getContextPath() + "/histories/index");
            }
        }
	}


