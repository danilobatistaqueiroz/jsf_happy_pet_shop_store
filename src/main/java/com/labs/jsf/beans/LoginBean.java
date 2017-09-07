package com.labs.jsf.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.labs.jsf.util.SessionUtils;

@ManagedBean
@RequestScoped
public class LoginBean {
	@Size(min = 4, max = 10)
	@NotEmpty
	private String username;

	@Size(min = 4, max = 10)
	@NotEmpty
	private String password;

	public void setUsername(String name) {
		this.username = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			HttpSession session = SessionUtils.getSession();
			if (session.getAttribute("username") == null || session.getAttribute("authenticated").equals("") == true) {
				request.login(username, password);
				session.setAttribute("username", username);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Congratulations! You've successfully logged in.");
				FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
			}
			if (request.isUserInRole("admin")) {
				return "adminUser";
			} else {
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

	public void forgotPassword() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Default user name: BootsFaces");
		FacesContext.getCurrentInstance().addMessage("loginForm:username", msg);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Default password: rocks!");
		FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
	}
}