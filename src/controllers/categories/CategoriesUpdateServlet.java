package controllers.categories;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import utils.DBUtil;

/**
 * Servlet implementation class CategoriesUpdateServlet
 */
@WebServlet("/categories/update")
public class CategoriesUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Category c = em.find(Category.class, (Integer)(request.getSession().getAttribute("category_id")));

            c.setName(request.getParameter("name"));

// CategoryValidatorどうするか
//            List<String> errors = ReportValidator.validate(r);
//            if(errors.size() > 0) {
//                em.close();

//                request.setAttribute("_token", request.getSession().getId());
//                request.setAttribute("report", r);
//                request.setAttribute("errors", errors);

//                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/edit.jsp");
//                rd.forward(request, response);
//            } else {
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました。");

            request.getSession().removeAttribute("category_id");

            response.sendRedirect(request.getContextPath() + "/categories/index");
            }
        }
    }
