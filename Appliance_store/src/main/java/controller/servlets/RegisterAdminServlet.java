package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.RegisterAdminModel;
import utils.StringUtils;

/**
 * Servlet implementation class RegisterAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterAdminServlet" })
public class RegisterAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DBController dbController = new DBController();
	
    public RegisterAdminServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String Admin_name = request.getParameter("admin_name");
		String Password = request.getParameter("password");
		String Email = request.getParameter("email");
		String Number = request.getParameter("number");
		
		System.out.println(Admin_name);
		System.out.println(Password);
		System.out.println(Email);
		System.out.println(Number);
		
		RegisterAdminModel registerAdminModel = new RegisterAdminModel (Admin_name, Password,
				Email, Number);
		
		int result = dbController.addAdmin(registerAdminModel);
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/loginAdmin.jsp");
		} else {
			
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ENTER_UNIQUE_INFO);
			request.getRequestDispatcher(StringUtils.REGISTER_ADMIN_URL).forward(request, response);
		}
		
	}

}
