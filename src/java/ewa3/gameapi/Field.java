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
    
    public Field(){
        occupyingPlayer = -1;
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
}
