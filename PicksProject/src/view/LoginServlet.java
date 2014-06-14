package view;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;
import common.Constants;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464761367797107169L;

	/*
	* Gson object to serialize JSON responses to requests to this servlet.
	*/
	  private static final Gson GSON = new Gson();
	
	  /*
	  * Default HTTP transport to use to make HTTP requests.
	  */
	    private static final HttpTransport TRANSPORT = new NetHttpTransport();

	    /*
	  * Default JSON factory to use to deserialize JSON.
	  */
	    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	  
	  /*
	  * This is the Client ID that you generated in the API Console.
	  */
	    private static final String CLIENT_ID = "300346544300.apps.googleusercontent.com";

	    /*
	  * This is the Client Secret that you generated in the API Console.
	  */
	    private static final String CLIENT_SECRET = "XovHdbd6BKiwyFGoez3KkQZS";

	    
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		Map<String, String[]> map = req.getParameterMap();
		
		PrintWriter out = resp.getWriter();
		for(Entry<String, String[]> e : map.entrySet())
		{
			out.println(e.getKey() +":  " + Arrays.toString(e.getValue()));
			out.println("<br/>");
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		   response.setContentType("application/json");

		// Only connect a user that is not already connected.
		String tokenData = (String) request.getSession().getAttribute("token");
		if (tokenData != null) 
		{
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(
					GSON.toJson("Current user is already connected."));
			return;
		}
		
		ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
	      getContent(request.getInputStream(), resultStream);
	      String code = new String(resultStream.toByteArray(), "UTF-8");

		try 
		{
			// Upgrade the authorization code into an access and refresh token.
			GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
					TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, code,
					"postmessage").execute();

			GoogleIdToken idToken = tokenResponse.parseIdToken();
			String gplusId = idToken.getPayload().getSubject();

			
			System.out.println("logged in user   " + gplusId);
			
			User user = UserService.getUserByID(gplusId);

			if (user == null)
			{
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(
						GSON.toJson("Successful Google Login but Unknown Picks User   " + gplusId));
			} else
			{
			
				// Store the token in the session for later use.
				request.getSession()
						.setAttribute("token", tokenResponse.toString());
				request.getSession().setAttribute(Constants.SESSION_USER_ID_KEY,
						user);
	
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(
						GSON.toJson("Successfully connected user."));
			}

		} catch (TokenResponseException e) 
		{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print(
					GSON.toJson("Failed to upgrade the authorization code."));
		} catch (IOException e) 
		{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print(
					GSON.toJson("Failed to read token data from Google. "
							+ e.getMessage()));
		}

	}
	
	
	private static void getContent(InputStream inputStream,	ByteArrayOutputStream outputStream) 
			throws IOException 
	{
		// Read the response into a buffered stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		int readChar;
		while ((readChar = reader.read()) != -1) 
		{
			outputStream.write(readChar);
		}
		reader.close();
	}

	
}
