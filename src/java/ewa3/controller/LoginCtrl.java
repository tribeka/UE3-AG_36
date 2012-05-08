/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.Spieler;

/**
 *
 * @author MeL
 */
@ManagedBean(name="login")
@SessionScoped
public class LoginCtrl implements Serializable {
    @ManagedProperty(value="#{spieler}")
    private Spieler player;
    @ManagedProperty(value = "false")
    private boolean loginfailed;
    
    private String password;
    private String username;
    
    /**
     * Creates a new instance of LoginCtrl
     */
    public LoginCtrl() {
    }   
     
    
    
    public boolean isLoginfailed() {
        return loginfailed;
    }

    public void setLoginfailed(boolean loginfailed) {
        this.loginfailed = loginfailed;
    }
    
    // Login new player 
    
    
    public String login() {
        if (this.getPassword().equals(getPlayer().getPassword()))
        {    
            loginfailed = false;
            return "/table.xhtml";
        }    
        else
        {
            loginfailed = true;
            return "/login.xhtml";
        }
    }
    
    //Validation of the username
    public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        loginfailed = false;
        this.setUsername((String)value);

        if(!username.equals(player.getName()))
        {
            ResourceBundle b = ResourceBundle.getBundle("i18n", 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
            String msgText = b.getString("wrongusername");
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,msgText, null);
            throw new ValidatorException(msg);
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the player
     */
    public Spieler getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Spieler player) {
        this.player = player;
    }
}
