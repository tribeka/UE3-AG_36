/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import model.Spieler;
import model.SpielerDatenbank;

/**
 *
 * @author bernhard
 */
@ManagedBean(name="register")
@SessionScoped
public class RegisterCtrl implements Serializable {
    @ManagedProperty(value="#{spieler}")
    private Spieler player;
    
    @ManagedProperty(value = "false")
    private boolean displaypersonal;    

    @ManagedProperty(value="#{playerbase}")
    private SpielerDatenbank playerbase;
    
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
        // Add new Player to player Database
        getPlayerbase().addPlayer(player);
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
        String dateofbirth = (String)value;
        boolean validDate;
        
        String DATE_PATTERN = 
          "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)";
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(dateofbirth);
        
        if(matcher.matches()){
 
	 matcher.reset();
 
	 if(matcher.find()){
 
             String day = matcher.group(1);
	     String month = matcher.group(2);
	     int year = Integer.parseInt(matcher.group(3));
 
	     if (day.equals("31") && 
		  (month.equals("4") || month .equals("6") || month.equals("9") ||
                  month.equals("11") || month.equals("04") || month .equals("06") ||
                  month.equals("09"))) {
			validDate = false; // only 1,3,5,7,8,10,12 has 31 days
	     } else if (month.equals("2") || month.equals("02")) {
                  //leap year
		  if(year % 4==0){
			  if(day.equals("30") || day.equals("31")){
				  validDate = false;
			  }else{
				  validDate = true;
			  }
		  }else{
		         if(day.equals("29")||day.equals("30")||day.equals("31")){
				  validDate = false;
		         }else{
				  validDate = true;
			  }
		  }
	      }else{				 
		validDate = true;				 
	      }
	   }else{
    	      validDate = false;
	   }		  
     }else{
	  validDate = false;
     }			    
        
        if(!validDate)
        {
            ResourceBundle b = ResourceBundle.getBundle("i18n", 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
            String msgText = b.getString("dateofbirthvalidmsg");
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN, msgText, null);
            throw new ValidatorException(msg);
        }
    }

    /**
     * @return the playerbase
     */
    public SpielerDatenbank getPlayerbase() {
        return playerbase;
    }

    /**
     * @param playerbase the playerbase to set
     */
    public void setPlayerbase(SpielerDatenbank playerbase) {
        this.playerbase = playerbase;
    }
}
