
package com.jsf;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable{ 
  @NotNull
    protected String allName;
   @Past
     protected Date dob;
    protected String sex;
    protected String email;
    protected String name="软件1225";
   // @Size(min=8, max=20)

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = unicodeToChinese(allName);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void validateEmail(FacesContext context, UIComponent toValidate,
            Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("请确认邮箱输入是否正确");
            throw new ValidatorException(message);
        }
    }
  
    public String addConfirmedUser() { 
	FacesMessage doneMessage = null;
	String outcome = null;
	if (allName.equals(name)) {
	    doneMessage = new FacesMessage("软件1225用户已存在，请重新注册");
	    outcome = "register";
	} else {
	    doneMessage = new FacesMessage("注册成功");
	    outcome = "success";
	}
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return outcome;
    }
    
public static String unicodeToChinese(String source) {
String changedStr = "";
try {
changedStr = new String(source.getBytes("ISO-8859-1"), "UTF-8");
} catch (UnsupportedEncodingException ex) {
}
return changedStr;
}

}
