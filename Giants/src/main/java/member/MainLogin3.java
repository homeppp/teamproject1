package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/login3")
public class MainLogin3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 확인하는 단계
		String userPw = request.getParameter("pw");
		String memberPw = (String) request.getAttribute("memberPw");
		
		if(!userPw.equals(memberPw)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.print("브라우저의 뒤로 가기 버튼을 눌러 ID 또는 PW를 확인하세요.");
			
			out.close();
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/main/login4");
			rd.forward(request, response);
		}
		return;
	}
}
