/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import model.Spieler;

/**
 *
 * @author bernhard
 */
@ManagedBean(name="register")
@SessionScoped
public class RegisterCtrl {
    @ManagedProperty(value="#{spieler}")
    Spieler player;
    @ManagedProperty(value = "true")
    private boolean displaypersonal;    

    /** Creates a new instance of RegisterCtrl */
    public RegisterCtrl() {
    }

    //Getter and Setter
    public Spieler getPlayer() {
        return player;
    }

    public void setPlayer(Spieler player) {
        this.player = player;
    }
    
    public boolean isDisplaypersonal() {
        return displaypersonal;
    }

    public void setDisplaypersonal(boolean displaypersonal) {
        this.displaypersonal = displaypersonal;
    }
    
    // Register new player
    public String register()
    {
        return "/login.xhtml";
    }
    
    //Checks if the display checkbox changed
    public void displayChanged(ValueChangeEvent e){
        Boolean show = (Boolean) e.getNewValue();
        if(show != null)
            displaypersonal = show;

        FacesContext.getCurrentInstance().renderResponse();
    }
    
    //Validation of the Geburtsdatum
    public void validateGeburtsdatum(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        // @TODO 
        String username = (String)value;

        if(!username.equals("Markus") && !username.equals("Heidi"))
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Wrong username!", null);
            throw new ValidatorException(msg);
        }
    }
}
