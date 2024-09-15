package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.RegisterUserModel;
import utils.StringUtils;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterUserServlet" })
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DBController dbController = new DBController();
	
    public RegisterUserServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		String User_name = request.getParameter("user_name");
		String User_password = request.getParameter("user_password");
		String User_email = request.getParameter("user_email");
		String User_number = request.getParameter("user_number");
		
		System.out.println(User_name);
		System.out.println(User_password);
		System.out.println(User_email);
		System.out.println(User_number);
		
		RegisterUserModel registerUserModel = new RegisterUserModel (User_name, User_password,
				User_email, User_number);
		
		int result = dbController.addUser(registerUserModel);
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/loginUser.jsp");
		} else {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ENTER_UNIQUE_INFO);
			request.getRequestDispatcher(StringUtils.REGISTER_USER_URL).forward(request, response);
		}
		
	}

}
