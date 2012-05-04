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
}