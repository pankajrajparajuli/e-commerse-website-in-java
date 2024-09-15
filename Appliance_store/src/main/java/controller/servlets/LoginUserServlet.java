package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import utils.StringUtils;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginUserServlet" })
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBController dbController = new DBController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		
		System.out.println(user_name);
		System.out.println(user_password);
		
		int userId = dbController.getUserLoginInfo(user_name, user_password);
		
		if (userId > 0) {
			// Sucessful login 
			HttpSession session = request.getSession();
			session.setAttribute("user_id", userId);
			session.setAttribute("user_name", user_name);
			//userSession.setAttribute("admin_id", adminId);
			session.setMaxInactiveInterval(30*30);
			
			System.out.println(userId);
			
			Cookie userCookie = new Cookie("admin",user_name);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			response.sendRedirect(request.getContextPath() + "/pages/Home.jsp");
			
		}else if (userId == 0) {
			// User name password doesn't match
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_WRONG_INFO);
			request.getRequestDispatcher(StringUtils.LOGIN_USER_URL).forward(request, response);
			
		}else {
			// Server error or some other error
		}
	}

}
