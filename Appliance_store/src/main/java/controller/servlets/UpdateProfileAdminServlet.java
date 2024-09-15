package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.RegisterAdminModel;

/**
 * Servlet implementation class UpdateProfileAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfileAdminServlet" })
public class UpdateProfileAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DBController dbController = new DBController();
   
    public UpdateProfileAdminServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String admin_idStr = request.getParameter("admin_id");
		
		System.out.println(admin_idStr);
		String admin_name = request.getParameter("admin_name");
		String admin_email = request.getParameter("email");
		String admin_password = request.getParameter("password");
		String admin_number = request.getParameter("number");
		
		System.out.println(admin_name);
		System.out.println(admin_email);
		System.out.println(admin_number);
		
		int admin_id = 0;
		
		try {
			if (admin_idStr != null && !admin_idStr.isEmpty()) {
		        admin_id = Integer.parseInt(admin_idStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		System.out.println(admin_id);
		
		RegisterAdminModel registerAdminModel = new RegisterAdminModel (admin_name, admin_password, admin_email, admin_number);
		
		int result = dbController.UpdateAdmin(registerAdminModel, admin_id);
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/loginAdmin.jsp");
		} else {
			
		}
		
	}

}
