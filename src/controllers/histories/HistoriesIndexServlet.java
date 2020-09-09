package controllers.histories;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.Member;
import utils.DBUtil;
/**
 * Servlet implementation class HistoriesIndexServlet
 */
@WebServlet("/histories/index")
public class HistoriesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Member login_member = (Member)request.getSession().getAttribute("login_member");

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        List<History> histories = em.createNamedQuery("getAllHistories", History.class)
                				.setParameter("member", login_member)
                				.setFirstResult(15 * (page - 1))
                				.setMaxResults(15)
                				.getResultList();


        long histories_count = (long)em.createNamedQuery("getHistoriesCount", Long.class)
                                .setParameter("member", login_member)
        						.getSingleResult();

        em.close();

        request.setAttribute("histories", histories);
        request.setAttribute("histories_count", histories_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/histories/index.jsp");
        rd.forward(request, response);
	}

}
