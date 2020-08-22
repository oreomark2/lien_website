package controllers.members;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Member;
import models.validators.MemberValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class MembersUpdateServlet
 */
@WebServlet("/members/update")
public class MembersUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembersUpdateServlet() {
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

			Member m = em.find(Member.class, (Integer)(request.getSession().getAttribute("member_id")));

			Boolean email_duplicate_check = true;
			if(m.getEmail().equals(request.getParameter("email"))){
				email_duplicate_check = false;
			} else {
				m.setEmail(request.getParameter("email"));
			}

			Boolean password_check_flag = true;
			String password = request.getParameter("password");
			if(password == null || password.equals("")){
				password_check_flag = false;
			} else {
				m.setPassword(EncryptUtil.getPasswordEncrypt(password, (String)this.getServletContext().getAttribute("pepper")));
			}

			m.setName(request.getParameter("name"));
			m.setPhone(request.getParameter("phone"));
			m.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
			m.setUpdated_at(new Timestamp(System.currentTimeMillis()));

			List<String> errors = MemberValidator.validate(m, email_duplicate_check, password_check_flag);
			if(errors.size() > 0){
				em.close();

				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("member", m);
				request.setAttribute("errors", errors);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/edit.jsp");
				rd.forward(request, response);
			} else {
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "更新が完了しました。");

				request.getSession().removeAttribute("member_id");

				response.sendRedirect(request.getContextPath() + "/members/index");
			}

		}

	}

}
