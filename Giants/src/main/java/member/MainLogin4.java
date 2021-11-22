package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main/login4")
public class MainLogin4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 성공
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		// 세션에 로그인 성공! 기록
		HttpSession session = request.getSession();
		if(session.isNew() || session.getAttribute("isLogin")== null) {
			session.setAttribute("isLogin", true);
			
			out.print("로그인 되었습니다.");
			
		} else {
			out.print("현재 로그인 되어 있는 상태입니다.");
			response.setStatus(208);
		}
		
		out.close();

	}
}
