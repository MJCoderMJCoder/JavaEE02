
package com.jsf;

import static com.jsf.UserBean.unicodeToChinese;
import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

@Named("login") // 
@SessionScoped
public class Login implements Serializable {
   private String name;
   private String password;
      private String random;

    public String getRandomcheck() {
        return randomcheck;
    }

    public void setRandomcheck(String randomcheck) {
        this.randomcheck = randomcheck;
    }
    private String randomcheck;

    public String getRandom() {
        Random randomGR = new Random();
        int temp = randomGR.nextInt(10000);
        return String.valueOf(temp);

    }

    public void setRandom(String random) {
        this.random = random;
    }

    public   Login(){
       
    }
    public String getName() {
       
        return name;
     
    }

    public void setName(String name) {
        this.name = unicodeToChinese(name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void check(ComponentSystemEvent event) {
        System.out.println("i am here");
        UIComponent source = event.getComponent();
        UIInput r1 = (UIInput) source.findComponent("randomcheck");
        UIInput r2 = (UIInput) source.findComponent("random2");
        String t1 = r1.getLocalValue().toString();
          System.out.println(t1);
        String t2 = r2.getLocalValue().toString();
          System.out.println(t2);
        if (!t1.equals(t2)) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("请确认验证码输入是否正确");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(r1.getClientId(), message);
            context.renderResponse();
        }
    }
    public String confirmedLogin() { 
	FacesMessage doneMessage = null;
	String outcome = null;
	if (password.equals("123")) {
	    doneMessage = new FacesMessage("登陆成功");
	    outcome = "welcome";
	} else {
	    doneMessage = new FacesMessage("密码有误，请重新登陆");
	    outcome = "login";
	}
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return outcome;
    }  
}
