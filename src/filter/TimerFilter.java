package filter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.LoginBean2;
import edu.umsl.java.beans.UserBean;


public class TimerFilter implements javax.servlet.Filter
{ 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
	    HttpServletRequest req = (HttpServletRequest)request;
	    HttpServletResponse res = (HttpServletResponse)response;
	    HttpSession session = req.getSession(false);
	    String url = null;
//	    String queryString = null;
	    if (request instanceof HttpServletRequest) {
	    	 url = ((HttpServletRequest)request).getRequestURL().toString();
//	    	 queryString = ((HttpServletRequest)request).getQueryString();
	    	 System.out.println(url);
	    	}
	    
	    if (session == null /*|| session.getAttribute("ssoId") == null*/) {
	    		System.out.println("Session is null");
	    		HttpServletResponse httpResponse = (HttpServletResponse) response;
	    		httpResponse.sendRedirect("login.jsp");
	    } else {   	    	 	
	   	    	String user = (String) session.getAttribute("userName");
	   	    	if(user == null) {
	   	    		System.out.println("Session is null");
		    		HttpServletResponse httpResponse = (HttpServletResponse) response;
		    		httpResponse.sendRedirect("login.jsp");
	   	    	}
	   	    	else { // User is not null
	   	    		System.out.println("Session refreshed.");
	   	    	 	System.out.println("Timer Filter reporting: " + session.getId());
	   	    	 	session.setMaxInactiveInterval(60);
	   	    	 	
	   	    	 	LoginBean2 loginBean = new LoginBean2();
	   	    	 	String query = "SELECT * FROM tblusers WHERE TblUsers_SSO = " + user;
	   	    	 	ResultSet rs2 = loginBean.getAllData(query);
					try {
						rs2.next();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   	    		if (Pattern.matches("(.*)/admin.jsp", url)) {
	   	   	    	 	try {
							if (rs2.getString("TblUsers_UsertypeID").equals("ADM")) {
								res.sendError(403, "User is not an admin.");
								System.out.println("User is not an admin.");
							}
							else // User is an admin
								chain.doFilter(request, response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // Logged-in user found, so just continue request.
	   	    	 	}
	   	    		else if (Pattern.matches("(.*)/instructor.jsp", url)) {
	   	    			try {
							if (rs2.getString("TblUsers_UsertypeID").equals("INS")) {
								res.sendError(403, "User is not an instructor.");
								System.out.println("User is not an instructor.");
							}
							else // User is an admin
								chain.doFilter(request, response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // Logged-in user found, so just continue request.
	   	    		}
	   	    		else // User is not accessing admin page
	   	    			chain.doFilter(request, response); // Logged-in user found, so just continue request.
	   	    	} 	
	    }
    }
}