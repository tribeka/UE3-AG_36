package model;

import java.beans.*;
import java.io.Serializable;

public interface Feld extends Serializable {
    
  //  Verwendet stattdessen SpielFeld.setPlayerToField
  //  public void setContent(Spieler player);
    
    public Spieler getContent();
    
    //Gibt an, ob das Feld das erste Feld auf dem Spielplan für einen Spieler ist.
    public Boolean isFirstField();
    
    //Gibt an, ob das Feld das letzte regulaere Feld auf dem Spielplan für einen Spieler ist.
    public Boolean isLastField();
    
    //Owner ändert sich nach erstellen des Spielfelds nie, muss nicht public sein
   // public void setOwner(Spieler player);
    
    //Gibt bei einem Start/Ziel/erstem/letztem Feld an, zu welchem Spieler es gehört
    public PlayerColor getOwner();
}
