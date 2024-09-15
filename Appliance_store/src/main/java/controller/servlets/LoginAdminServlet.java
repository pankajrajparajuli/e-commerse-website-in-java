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
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginAdminServlet" })
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   DBController dbController = new DBController();
	
    public LoginAdminServlet() {
        super();
        
    }

	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String admin_name = request.getParameter("admin_name");
		String password = request.getParameter("password");
		
		System.out.println(admin_name);
		System.out.println(password);
		
		int adminId = dbController.getAdminLoginInfo(admin_name, password);
		
		
		
		if (adminId > 0) {
			// Sucessful login 
			
			HttpSession session = request.getSession();
			session.setAttribute("admin_id", adminId);
			session.setAttribute("admin_name", admin_name);
			//userSession.setAttribute("admin_id", adminId);
			//userSession.setMaxInactiveInterval(30*30);
			
			
			Cookie userCookie = new Cookie("admin",admin_name);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			/*Cookie adminIdCookie = new Cookie("admin_id", String.valueOf(adminId)); // Create a cookie for admin ID
	        adminIdCookie.setMaxAge(30*60);
	        response.addCookie(adminIdCookie); */
			
			
			response.sendRedirect(request.getContextPath() + "/pages/WelcomeAdmin.jsp");
			
		}else if (adminId == 0) {
			// Admin name password doesn't match
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_WRONG_INFO);
				request.getRequestDispatcher(StringUtils.LOGIN_ADMIN_URL).forward(request, response);
			
		}else {
			// Server error or some other error
		}
	}

}
