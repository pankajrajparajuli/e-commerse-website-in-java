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
import model.AddProductModel;
import utils.StringUtils;


/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/// Creating dbController object of data base controller
	DBController dbController = new DBController();
   
	
	
    public AddProductServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Getting product details from the jsp page
		String product_name = request.getParameter(StringUtils.PRODUCT_NAME);
		String description = request.getParameter(StringUtils.DESCRIPTION);
		String priceStr = request.getParameter(StringUtils.PRICE);
		String stockStr = request.getParameter(StringUtils.STOCK);
		Part imagePart = request.getPart("image");
		
		double price = 0.0;
		int stock = 0;

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
		
		/// Can be used for checking errors
		System.out.println(product_name);
		System.out.println(description);
		System.out.println(price);
		System.out.println(imagePart);
		
		
		// Create a StudentModel object to hold student information
		AddProductModel product = new AddProductModel(product_name, description, price, stock, imagePart);
		
		// Call DBController to register the student
		int result = dbController.addProduct(product);
		
		if (result == 1) {
			
			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = product.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
			  // Construct the full image save path by combining the directory path and filename
			  String savePath = StringUtils.IMAGE_DIR_PRODUCT;
			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			  response.sendRedirect(request.getContextPath() + StringUtils.PRODUCT_URL);
			}
			
		} else if (result == 0) {
			//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
			//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		} else {
			//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
		
	}

}
