package ewa3.model;

import java.beans.*;
import java.io.Serializable;
import java.util.LinkedList;

public class Spieler implements Serializable {
    
    private String Name;
    
    public LinkedList<Integer> LastDies;
    
    public Spieler() {
        Name = "";
        LastDies = new LinkedList<Integer>();
    }
    
    
    public Spieler(String nm) {
        Name = nm;
        LastDies = new LinkedList<Integer>();
    }
    
    public String getName() { return Name; }
    
    public void setName(String value) { Name = value; }
}