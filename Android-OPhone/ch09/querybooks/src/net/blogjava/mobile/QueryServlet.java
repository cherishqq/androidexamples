package net.blogjava.mobile;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8"); 
		String queryStr = "";
		if ("post".equals(request.getMethod().toLowerCase()))
			queryStr = "POST���󣻲�ѯ�ַ�����" + new String(request.getParameter("bookname").getBytes(
					"iso-8859-1"), "utf-8");
		else if ("get".equals(request.getMethod().toLowerCase()))
			queryStr = "GET���󣻲�ѯ�ַ�����" + request.getParameter("bookname");

		String s =queryStr
				+ "[Java Web������ѧ����;Java����ָ��˼�루��4�棩;Java EE�������䣻C#��������]";
		PrintWriter out = response.getWriter();
		out.println(s);
	}
}
