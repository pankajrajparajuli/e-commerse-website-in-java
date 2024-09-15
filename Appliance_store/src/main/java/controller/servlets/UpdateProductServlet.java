package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.UpdateProductModel;
import utils.StringUtils;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBController dbController = new DBController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String product_idStr = request.getParameter("product_id");
		System.out.println(product_idStr);
        String product_name = request.getParameter("product_name");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String stockStr = request.getParameter("stock");
        Part imagePart = request.getPart("image");
        System.out.println(imagePart.getSubmittedFileName());
       
        
        double price = 0.0;
		int stock = 0;
		Integer product_id = 0;

		try {
		    price = Double.parseDouble(priceStr);
		} catch (NumberFormatException e) {
		    // Handle parsing error for price
		}

		try {
			if (stockStr != null && !stockStr.isEmpty()) {
		        stock = Integer.parseInt(stockStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		try {
			if (product_idStr != null && !product_idStr.isEmpty()) {
		        product_id = Integer.parseInt(product_idStr);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		/// Can be used for checking errors
		System.out.println(product_id);
		System.out.println(product_name);
		System.out.println(description);
		System.out.println(price);
		System.out.println(imagePart);
		
		// Create a StudentModel object to hold student information
		UpdateProductModel productUpdate = new UpdateProductModel(product_id, product_name, description, price, stock, imagePart.getSubmittedFileName());
		
				
		// Call DBController to register the student
			int result = dbController.updateProduct(productUpdate);
			
				
			if (result == 1) {
					
			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = productUpdate.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
			// Construct the full image save path by combining the directory path and filename
			String savePath = StringUtils.IMAGE_DIR_PRODUCT;
			imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			response.sendRedirect(request.getContextPath() + StringUtils.PRODUCT_URL);
					}
					System.out.println("1");

					//request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
					//response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN+ "?success=true");
				} else if (result == 0) {
					//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
					//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
					System.out.println("2");
				} else {
					//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
					//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
					System.out.println("3");
				}
        
        
    }
	

}
