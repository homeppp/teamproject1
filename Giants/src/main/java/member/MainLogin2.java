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

@WebServlet("/main/login2")
public class MainLogin2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
	protected void doPost(HttpServletRequest request, HttpServletResponse response, MemberInfo[] memberList) throws ServletException, IOException {
		// 회원 정보 조회
		String userId = request.getParameter("id");
		String memberId = null;
		String memberPw = null;
		
		
		boolean exist = false;
		for(MemberInfo member1 : memberList) {
			exist = userId.equals(member1.getId());
			if(exist) {
				memberId = member1.getId();
				memberPw = member1.getPw();
				break;
			} // end if
		} //end for
		
		if(!exist) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			response.setContentType("text/html;charset=UTF-8");
					
			PrintWriter out = response.getWriter();
			
			out.print("브라우저의 뒤로 가기 버튼을 눌러 ID 다시 입력하세요.");
			
			out.close();
			
			return ;
		} 
		
		request.setAttribute("memberId", memberId);
		request.setAttribute("memberPw", memberPw);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/main/login3");
		rd.forward(request, response);
	}

}
