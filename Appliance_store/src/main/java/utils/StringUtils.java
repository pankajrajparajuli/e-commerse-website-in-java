package utils;

public class StringUtils {
	
	
	// Connectiong to the database
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/appliance_store";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	
	
	/// IMAGE FROM DB
	public static final String IMAGE_ROOT_PATH = "Users\\Subham\\eclipse-workspace\\Appliance_store\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
	//public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
	
	
	// Query to register admin
	public static final String REGISTER_ADMIN = "INSERT INTO admins (admin_name, password, email, number) VALUES (?, ?, ?, ?)";
	
	// Query to register admin
		public static final String UPDATE_ADMIN = "UPDATE admins SET admin_name = ?, password = ?, email = ?, number = ? WHERE admin_id = ?";
	
//----------------------------------------------------------------------------------------------------------------------------------------	
		// Query to Log in admin
		
		public static final String GET_LOGIN_ADMIN_INFO = "SELECT * FROM admins WHERE admin_name = ? AND password = ? ";
		
		// End Query
//----------------------------------------------------------------------------------------------------------------------------------------
	// Query to register user
	public static final String REGISTER_USER = "INSERT INTO users (user_name, user_password, user_email, user_number) VALUES (?, ?, ?, ?)";
	
	// Query to Log in User
	
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM users WHERE user_name = ? AND user_password = ? ";
			
		// End Query
			
// Query to register admin
	public static final String UPDATE_USER = "UPDATE users SET user_name = ?, user_password = ?, user_email = ?, user_number = ? WHERE user_id = ?";
	
//----------------------------------------------------------------------------------------------------------------------------------------
		
	// Query to add product
	public static final String ADD_PRODUCT = "INSERT INTO products (product_name, description, price, stock, imageUrlFromPart) VALUES (?, ?, ?, ?, ?)";
			
	// Query to update product
	public static final String UPDATE_PRODUCT = "UPDATE Products SET product_name=?, description=?, price=?, stock=?, imageUrlFromPart=? WHERE product_id=?";
	
//-----------------------------------------------------------------------------------------------------------------------------------------
	
	// Query to get product info
	public static final String GET_PRODUCT_INFO = "SELECT * FROM products";
	
//----------------------------------------------------------------------------------------------------------------------------------------
	
	// Query to add product
	public static final String ADD_TO_CART = "INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?, ?, ?)";
	
	
	// Query to delete product
	public static final String DELETE_PRODUCT ="DELETE FROM products WHERE product_id = ?";
	
//-----------------------------------------------------------------------------------------------------------------------------------------
	// Start: JSP Route
		public static final String PRODUCT_URL = "/pages/AdminViewProduct.jsp";
		public static final String USER_HOME = "/pages/Home.jsp";
		public static final String CART_URL = "/pages/Cart.jsp";
		public static final String REGISTER_ADMIN_URL = "/pages/registerAdmin.jsp";
		public static final String REGISTER_USER_URL = "/pages/registerUser.jsp";
		public static final String LOGIN_ADMIN_URL = "/pages/loginAdmin.jsp";
		public static final String LOGIN_USER_URL = "/pages/loginUser.jsp";
		public static final String LOGIN_USER_SERVLET = "../LoginUserServlet.java";
		public static final String LOGOUT_SERVLET = "../LogoutServlet.java";
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------	
	public static final String PRODUCT_NAME = "product_name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String STOCK = "stock";
	public static final String IMAGE = "image";
	
	
	// Start: Validation Messages
	
		// Basic Errors 
		public static final String MESSAGE_SUCCESS = "successMessage";
		public static final String MESSAGE_ERROR = "errorMessage";
		public static final String MESSAGE_REDIRECT = "redirectMessage";
	
	
		// Home Page Messages
		public static final String MESSAGE_ADDED_TO_CART = "Successfully Added to the cart";
		public static final String MESSAGE_ALREADY_IN_CART = "This item already exists in your cart";
		public static final String LOGIN_TO_ACCESS_CART = "Please Login to accesss your cart";
		public static final String MESSAGE_ENTER_UNIQUE_INFO = "Please enter unique number and username";
		public static final String MESSAGE_WRONG_INFO = "WRONG Username and password";
		
//-----------------------------------------------------------------------------------------------------------------------------------------	
	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String STUDENT_LISTS = "studentLists";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";

}
