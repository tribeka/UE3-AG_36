/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameapi;

/**
 * Class representing a field
 */
public class Field {
    private int occupyingPlayer;
    private String img;
    private String text;
    private Integer index;
    
    public Field(){
        occupyingPlayer = -1;
        img = "";
        text = "";
        index = -1;
    }
    
    public Field(int occupyingPlayer){
        this.occupyingPlayer = occupyingPlayer;
    }
    
    public int getOccupyingPlayer(){
        return occupyingPlayer;
    }

    public void setOccupyingPlayer(int player){
        occupyingPlayer = player;
    }
    
    public String getOccupyingPlayerImage() {
        if(occupyingPlayer == -1) {
            return "";
        }
        else {
            return "_player" + occupyingPlayer;
        }
    }
    
    public String getOccupyingPlayerText() {
        if(occupyingPlayer == -1) {
            return "";
        }
        else {
            return ": Spieler " + occupyingPlayer;
        }
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String str) {
        img = str;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String str) {
        text = str;
    }
    
    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer i) {
        index = i;
    }
}
