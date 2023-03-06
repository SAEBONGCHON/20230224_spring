package kh.spring.s03.member.model.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class MemberVo {
//	@Validated  //유효성 체크, 웹소켓시 필요 프로젝트 진행시 사용할 수도 안할수도,
	private String id;
	private String passwd;
	private String name;
	private String email;
	
	
	
	@Override
	public String toString() {
		return "MemberDao [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}
	
	public MemberVo() {
		super();
	}
	public MemberVo(String id, String passwd, String name, String email) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
