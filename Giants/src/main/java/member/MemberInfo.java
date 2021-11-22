package member;

import java.util.Objects;

public class MemberInfo {
	private String id;
	private String name;
	private String pw;
	private String birth;
	private String phone;
	private String email;
	
	@Override
	public boolean equals(Object obj) {
		MemberInfo member = (MemberInfo) obj;
		String memberId = member.getId();
		String memberName = member.getName();
		String memberPw = member.getPw();
		String memberBirth = member.getBirth();
		String memberPhone = member.getPhone();
		String memberEmail = member.getEmail();
		
		boolean sameId = id.equals(memberId);
		boolean sameName = name.equals(memberName);
		boolean samePw = pw.equals(memberPw);
		boolean sameBirth = birth.equals(memberBirth);
		boolean samePhone = phone.equals(memberPhone);
		boolean sameEmail = email.equals(memberEmail);
		
		return sameId && samePw && sameName && sameBirth && samePhone && sameEmail;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, pw);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
