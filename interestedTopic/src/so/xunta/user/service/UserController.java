package so.xunta.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserController extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd==null)
		{
			System.err.println("cmd is null");
			return;
		}
		switch (cmd) {
		case "xunta_user_login":
			xunta_user_login(req,resp);
			break;
		default:
			break;
		}
		resp.getWriter().write("hello");
	}

	private void xunta_user_login(HttpServletRequest req,
			HttpServletResponse resp) {
			System.out.println("xunta_user_login...");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
