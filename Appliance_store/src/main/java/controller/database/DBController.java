package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AddProductModel;
import model.RegisterAdminModel;
import model.RegisterUserModel;
import model.UpdateProductModel;
import utils.StringUtils;

public class DBController {
	
	/// Connecting to the database
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}
	
	/// ------- FOR ADMIN REGISTRATION ------------------///
	public int addAdmin( RegisterAdminModel registerAdminModel) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.REGISTER_ADMIN);
			
			st.setString(1, registerAdminModel.getAdmin_name());
			st.setString(2, registerAdminModel.getPassword());
			st.setString(3, registerAdminModel.getEmail());
			st.setString(4, registerAdminModel.getNumber());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	
	/// ------- FOR ADMIN REGISTRATION ------------------///
		public int UpdateAdmin( RegisterAdminModel registerAdminModel, int adminID) {
			try (Connection con = getConnection()){
				PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_ADMIN);
				
				
				st.setString(1, registerAdminModel.getAdmin_name());
				st.setString(2, registerAdminModel.getPassword());
				st.setString(3, registerAdminModel.getEmail());
				st.setString(4, registerAdminModel.getNumber());
				st.setInt(5, adminID);
				
				int result = st.executeUpdate();
				return result > 0 ? 1 : 0;
				
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1;
			}
		}
	
	
	
	/// ------- FOR USER REGISTRATION ------------------///
	public int addUser( RegisterUserModel registerUserModel) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.REGISTER_USER);
			
			st.setString(1, registerUserModel.getUser_name());
			st.setString(2, registerUserModel.getUser_password());
			st.setString(3, registerUserModel.getUser_email());
			st.setString(4, registerUserModel.getUser_number());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		
	}
	
	/// ------- FOR ADMIN REGISTRATION ------------------///
			public int UpdateUser( RegisterUserModel registerUserModel, int userID) {
				try (Connection con = getConnection()){
					PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_USER);
					
					
					st.setString(1, registerUserModel.getUser_name());
					st.setString(2, registerUserModel.getUser_password());
					st.setString(3, registerUserModel.getUser_email());
					st.setString(4, registerUserModel.getUser_number());
					st.setInt(5, userID);
					
					int result = st.executeUpdate();
					return result > 0 ? 1 : 0;
					
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -1;
				}
			}
	
	/// ------- FOR ADMIN LOG IN ------------------///
	public int getAdminLoginInfo(String admin_name, String password) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_ADMIN_INFO);
			
			st.setString(1, admin_name);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				/// IF the admin name and pass matches
				return rs.getInt("admin_id");
			}else {
				return 0;
			}
	}catch (SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return -1;
		}
	}
	
	
	
		
	/// ------- FOR USER LOG IN ------------------///
		public int getUserLoginInfo(String user_name, String user_password) {
			try (Connection con = getConnection()){
				PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
				
				st.setString(1, user_name);
				st.setString(2, user_password);
				ResultSet rs = st.executeQuery();
				
				if(rs.next()) {
					/// IF the user name and pass matches
					return rs.getInt("user_id");
				}else {
					return 0;
				}
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
			
	///// --------------- FOR ADDING PRODUCT ----------------------------
		public int addProduct(AddProductModel product) {

			try {
				// Prepare a statement using the predefined query for student registration
				PreparedStatement stmt = getConnection().prepareStatement(StringUtils.ADD_PRODUCT);

				// Set the student information in the prepared statement
				stmt.setString(1, product.getProduct_name());
				stmt.setString(2, product.getDescription());
				stmt.setDouble(3, product.getPrice());
				stmt.setInt(4, product.getStock()); 
				stmt.setString(5, product.getImageUrlFromPart());

				// Execute the update statement and store the number of affected rows
				int result = stmt.executeUpdate();

				// Check if the update was successful (i.e., at least one row affected)
				if (result > 0) {
					return 1; // added successful
				} else {
					return 0; //  failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
					// Print the stack trace for debugging purposes
					ex.printStackTrace();
					return -1; // Internal error
			}
		}
		
		public ArrayList<AddProductModel> getAllProductInfo() {
			try {
				PreparedStatement stmt = getConnection().prepareStatement(StringUtils.GET_PRODUCT_INFO);
				ResultSet result = stmt.executeQuery();

				ArrayList<AddProductModel> products = new ArrayList<AddProductModel>();

				while (result.next()) {
					AddProductModel product = new AddProductModel();
					
					product.setProduct_name(result.getString("product_name"));
					product.setDescription(result.getString("description"));
					product.setPrice(result.getDouble("price"));
					product.setStock(result.getInt("stock"));
					product.setImageUrlFromDB(result.getString("imageUrlFromPart"));
					
					products.add(product);
				}
				return products;
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
	///// --------------- FOR UPDATING PRODUCT ----------------------------
		/// The code from the above method (addProduct) can be reused here with minor changes
			public int updateProduct(UpdateProductModel productUpdate) {

				try {
					// Prepare a statement using the predefined query for student registration
					PreparedStatement stmt = getConnection().prepareStatement(StringUtils.UPDATE_PRODUCT);

					// Set the student information in the prepared statement
					
					stmt.setString(1, productUpdate.getProduct_name());
					stmt.setString(2, productUpdate.getDescription());
					stmt.setDouble(3, productUpdate.getPrice());
					stmt.setInt(4, productUpdate.getStock()); 
					stmt.setString(5, productUpdate.getImageUrlFromPart());
					stmt.setInt(6, productUpdate.getProduct_id());

					// Execute the update statement and store the number of affected rows
					int result = stmt.executeUpdate();

					// Check if the update was successful (i.e., at least one row affected)
					if (result > 0) {
						System.out.println("Here");
						return 1; // Registration successful
					} else {
						System.out.println("Here2");
						return 0; // Registration failed (no rows affected)
					}

				} catch (ClassNotFoundException | SQLException ex) {
						// Print the stack trace for debugging purposes
						ex.printStackTrace();
						return -1; // Internal error
				}

			}
			
			
		///// --------------- FOR ADDING PRODUCT ----------------------------
			public int addToCart(int user_id, int product_id, int quantity) {

				try {
					// Prepare a statement using the predefined query for student registration
					PreparedStatement stmt = getConnection().prepareStatement(StringUtils.ADD_TO_CART);
					
					stmt.setInt(1, user_id);
					stmt.setInt(2, product_id);
					stmt.setInt(3, quantity);
					
					System.out.println(user_id);
					
					int result = stmt.executeUpdate();
					return result > 0 ? 1 : 0;
					
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -1;
				}
					
			}
			
}
