package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
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
		       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 회원 정보 조회
		String userId = request.getParameter("id");
		String memberId = null;
		String memberPw = null;
		
		
		boolean exist = false;
		Set<MemberInfo> memberList = InputMember.memberList;
		if(memberList == null) {
			memberList = new HashSet<>();
			
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId("id");
			memberInfo.setPw("pw");
			
			memberList.add(memberInfo);
		}
		
		for(MemberInfo member : memberList) {
			exist = userId.equals(member.getId());
			if(exist) {
				memberId = member.getId();
				memberPw = member.getPw();
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
