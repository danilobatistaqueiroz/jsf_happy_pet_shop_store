package com.labs.jsf.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.labs.jsf.validations.PhoneValidator")
public class PhoneValidator implements Validator{

	private static final String PHONE_PATTERN = "\\d{2}(-[9]?\\d{4}){1}-\\d{4}";

	private Pattern pattern;
	private Matcher matcher;

	public PhoneValidator(){
		pattern = Pattern.compile(PHONE_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		System.out.println("**** validating phone *****");
		matcher = pattern.matcher(value.toString());
		if (matcher.matches()==false) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone validation failed.", "Phone Number must be in the forms XX-XXXX-XXXX or XX-9XXXX-XXXX");
			throw new ValidatorException(msg);
		}

	}
}