/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author bernhard
 */
@ManagedBean(name = "playerbase")
@ApplicationScoped
public class SpielerDatenbank implements Serializable {
    private HashMap<String, Spieler> players;
    
    public SpielerDatenbank() {
        players = new HashMap<String, Spieler>();
    }
    
    public void addPlayer(Spieler player) {
        this.players.put(player.getName(), player);
    }

    public Spieler getPlayer(String name) {
        return this.players.get(name);
    }
    
    /**
     * @return the players
     */
    public HashMap<String, Spieler> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(HashMap<String, Spieler> players) {
        this.players = players;
    }
}
