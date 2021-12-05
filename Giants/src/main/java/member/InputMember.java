package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input/member")
public class InputMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Set<MemberInfo> memberList;
	
	@Override
	public void init() throws ServletException {
		memberList = new HashSet<MemberInfo>();
		System.out.println("회원정보 저장할 공간 확보");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 페이지에서 정보 가져오기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		String year = request.getParameter("year"); 
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String phone1 = request.getParameter("tel");
		String phone2 = request.getParameter("tel2");
		String phone3 = request.getParameter("tel3");
		String email1 = request.getParameter("email1");
		String email2= request.getParameter("email2");
						
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//전달 받은 값이 null이 없는지 체크
		if(id == null || name == null || pw == null || pw2 == null || year == null || phone1 == null || phone2 == null || email1 == null || email2 == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			out.print("각 항목을 채워 주세요~!");
			
			return ;
		}
			
		// 사용자가 입력한 값에 앞 뒤 공백이 있으면 제거
		id = id.trim();
		pw = pw.trim();
		pw2 = pw2.trim();
		year = year.trim();
		phone1 = phone1.trim();
		phone2 = phone2.trim();
		phone3 = phone3.trim();
		email1 = email1.trim();
		email2 = email2.trim();
				
		// 사용자가 입력한 값에 중간에 공백이 있는 것 제거
		id = id.replace(" ", "");
		pw = pw.replace(" ", "");
		pw2 = pw2.replace(" ", "");
		year = year.replace(" ", "");
		month= month.replace(" ", "");
		day = day.replace(" ", "");
		phone1 = phone1.replace(" ", "");
		phone2 = phone2.replace(" ", "");
		phone3 = phone3.replace(" ", "");
		email1 = email1.replace(" ", "");
		email2 = email2.replace(" ", "");
				
		Calendar cal = Calendar.getInstance();
		int nowyear = cal.get(cal.YEAR); // 현재 년도 구하기
		int yy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);

		// 각 정보의 길이 확인
		if (id.length() < 4 && id.length() > 20 && pw.length() < 8 && pw.length() > 15 && pw2.length() < 8 && pw2.length() > 15 ) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("ID와 PW 길이를 확인 해 주세요!!");
			return ;
		} else if (phone1.length() !=3 || phone2.length() != 4 || phone3.length() != 4) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("전화번호 길이를 확인하세요.");
			return ;
		} else if(yy > nowyear){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("태어난 년도를 다시 확인하세요~");
			return;
		} else if(mm > 12 ||(mm==1 && dd >31) || (mm==2 && dd>29) ||(mm==3 && dd>31)|| (mm==4 && dd>30)|| (mm==5 && dd>31)||(mm==6 && dd>30)|| (mm==7 && dd>31)|| (mm==8 && dd>31)|| (mm==9 && dd>30) ||(mm==10 && dd > 31)||(mm==11 && dd>30)|| (mm==12 && dd>31)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("생년월일을 다시 확인하세요~");
			return;
		}
		String[] specialChar = {"!", "@", "?", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+", "=", "|", "\\", "~", "<", ">", ",", "."};
		boolean isSpecial = false;
		for( int i=0; i < specialChar.length;i++) {
			isSpecial = pw.indexOf(specialChar[i]) > -1;
			if(isSpecial) break;
		}

		String[] alpha ={"a", "b", "c", "d", "f", "e","f","g","h","i","j","k", "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A", "B","C", "D", "E", "F", "G", "H", "I", "J","K","L","M","N","O","P", "Q", "R","S", "T", "U","V", "W", "X", "Y", "Z"};
		boolean isAlpha = false;
		for( int i = 0; i<alpha.length; i++) {
			isAlpha = pw.indexOf(alpha[i]) >-1;
			if(isAlpha) break;
		}
		String[] num = {"0","1","2","3", "4","5", "6","7","8","9"};
		boolean isNum = false;
		for(int i = 0; i<num.length; i++) {
			isNum = pw.indexOf(num[i]) > -1;
			if(isNum) break;
		}

		if(!isSpecial || !isAlpha || !isNum) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("비밀번호에 특수 문자, 숫자, 문자를 포함하세요.");
			return;
		}
		
		String phone = phone1 + "-" + phone2 + "-" + phone3;
				
		// 패스워드랑 패스워드 확인이 같은지 확인
		if(!pw.equals(pw2)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("비밀번호가 일치하지 않습니다.");
			return;
		}
				
		String email = email1 + "@" + email2;
		String birth = year + "-" + month + "-" + day;
				
		MemberInfo member = new MemberInfo();
			
		member.setId(id);
		member.setName(name);
		member.setPw(pw);
		member.setBirth(birth);
		member.setPhone(phone);
		member.setEmail(email);
				
		// 전달받은  정보를 set에 저장하기
		boolean exist = false;
		for(MemberInfo mem : memberList) {
			exist = id.equals(mem.getId());
			if(exist) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("이미 등록된 아이디입니다. 다른 아이디를 사용하세요~!");
				return ;
			}
		}
		
		boolean exist1 = memberList.add(member);
		System.out.println(member.getId()+ "\t" + member.getPw());
		
		if(exist1) {
			System.out.println("회원정보 저장 완료!");
			// 가입완료 페이지로 이동하기
			response.sendRedirect("/Giants/membership5.html");
			
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("이미 등록된 정보랑 같습니다. 등록할 수 업습니다.");
			return ;
		}
		out.close();	
	}
}
