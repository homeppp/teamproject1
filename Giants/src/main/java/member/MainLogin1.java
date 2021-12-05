package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/login1")
public class MainLogin1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 확인
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
				
		if(userId != null && userPw != null) {
			// 문자열 앞 뒤 공백 제거
			userId = userId.trim();
			userPw = userPw.trim();
		}
				
		if(userId == null || userPw == null || userId.isEmpty() || userPw.isEmpty()) {
			// 로그인에 필요한 파라미터를 전달 받지 못했다면 SC_BAD_REQUEST 응답
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					
			response.setContentType("text/html;charset=UTF-8");
					
			PrintWriter out = response.getWriter();
			
			out.print("브라우저의 뒤로 가기 버튼을 눌러 ID 또는 PW를 입력하세요.");
			
			out.close();
			
			return ;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/main/login2");
		rd.forward(request, response);
	}

}
