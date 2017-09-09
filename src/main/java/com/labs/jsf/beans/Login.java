package com.labs.jsf.beans;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import com.labs.jsf.util.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min = 4, max = 10)
	@NotEmpty
	private String pwd;
	private String msg;
	@Size(min = 4, max = 10)
	@NotEmpty
	private String user;
	private Date birthDate;
	private String email;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// @PostConstruct
	// public void ifNotAuthenticated() {
	// HttpSession session = SessionUtils.getSession();
	// if (session.getAttribute("username") == null ||
	// session.getAttribute("username").equals("") == true) {
	// RequestContext context = RequestContext.getCurrentInstance();
	// context.execute("PF('myDialogVar').show();");
	// }
	// }

	public String loginUser() {
		System.out.println("entering, with login");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			HttpSession session = SessionUtils.getSession();
			if (session.getAttribute("username") == null || session.getAttribute("username").equals("") == true) {
				request.login(user, pwd);
				user = StringUtils.capitalize(user);
				session.setAttribute("username", user);
				// } else {
				// FacesMessage msg = new
				// FacesMessage(FacesMessage.SEVERITY_INFO, "",
				// "Congratulations! You've successfully logged in.");
				// FacesContext.getCurrentInstance().addMessage("loginForm:password",
				// msg);
			}
			if (request.isUserInRole("admin")) {
				System.out.println("im a administrator user");
				session.setAttribute("isadmin", true);
				//FacesContext.getCurrentInstance().getExternalContext().redirect("admin/startpage.xhtml");
				return "adminUser";
			} else {
				session.setAttribute("isadmin", false);
				System.out.println("im a common user");
				//FacesContext.getCurrentInstance().getExternalContext().redirect("customer/startpage.xhtml");
				return "commonUser";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd", "Please enter correct username and Password");
			context.addMessage("loginForm:loginButton", message);
			return "login";
		}
	}

	public String logout() {
		System.out.println("fez logout");
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "logout";
	}

	public String registerUser() {
		System.out.println("registering");
		return "registered";
	}

	public void forgotPassword() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Default user name: BootsFaces");
		FacesContext.getCurrentInstance().addMessage("loginForm:username", msg);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Default password: rocks!");
		FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
	}
}