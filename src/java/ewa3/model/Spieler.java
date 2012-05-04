package model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.beans.*;
import java.io.Serializable;
import java.util.LinkedList;

@ManagedBean
@ApplicationScoped
public class Spieler implements Serializable {

    private String Name;
    
    /**
     * Password
     */
    private String password;
        
    /**
     * Personal data - firstname, lastname, dateofbirth, gender
     */
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;

    public Spieler() {
        Name = "";
    }


    public Spieler(String nm) {
        Name = nm;
    }

    public String getName() {
        return Name;
    }

    public void setName(String value) {
        Name = value;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}