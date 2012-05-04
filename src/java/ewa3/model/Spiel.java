package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.beans.*;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name="mygame")
@SessionScoped
public class Spiel implements Serializable {
    private List<Spieler> Players;
    private Spielfeld Playarea;
    public HashMap<Spieler, LinkedList<Integer>> LastDies;

    private Integer Round;
    private long Starttime;
    private Boolean Over;
    
    public Spiel() {
        Players = Arrays.asList(new Spieler ("Mario"), new Spieler("Computer"));
        
        LastDies = new HashMap<Spieler, LinkedList<Integer>>();

        for (Spieler s : Players)
            LastDies.put(s, new LinkedList<Integer>());


        this.reset();
    }

    public Spiel(Spieler player) {
        Players = Arrays.asList(player, new Spieler("Computer"));

        for (Spieler s : Players)
            LastDies.put(s, new LinkedList<Integer>());


        this.reset();
    }

    public final void reset() {
        Playarea = new SpielfeldImpl(Players);
        Round = 0;
        Over = false;
        Starttime = new Date().getTime();
        for (Spieler s : Players)
            LastDies.get(s).clear();

    }

    public Boolean isOver() {
        return Over;
    }
    
    public Boolean getOver() {
        return Over;
    }

    public Spieler getLeader() {
        Iterator<Spieler> it = Players.iterator();
        Spieler plyr = null, tmpplyr;
        Integer dist = 100, temp; //greater than max distance
        do {
            tmpplyr = it.next();
            temp = Playarea.distanceToFinish(tmpplyr);
            if (temp < dist) {
                dist = temp;
                plyr = tmpplyr;
            }
        } while (it.hasNext());
        return plyr;
    }

    public List<Spieler> getPlayer() {
        return Players;
    }
    
    public Spieler getHumanPlayer() {
        return Players.get(0);
    }
    
    public int getPlayersCnt() {
        return Players.size();
    }

    public String getTime() {
        long time = new Date().getTime();
        time = time - Starttime;
        return (time / (1000 * 60)) + " min " + ((time / 1000) % 60) + " sec";
    }

    public int getRound() {
        return Round;
    }

    public String doRound() {
        if(Over) {
            return "";
        }
        Spieler humanPlayer = Players.get(0);
        Spieler computerPlayer = Players.get(1);

        for (Spieler s : Players)
            LastDies.get(s).clear();

        // wuerfeln
        int wurf = wuerfle();

        // spielzug
        this.spielzug(humanPlayer, wurf);
        LastDies.get(humanPlayer).offer(wurf);

        // if wuerfel == 6, zurueck zur view, da Spieler nochmals an der Reihe ist
        if ((wurf != 6) && (!this.isOver())) {
            do {
                // Computer player - getWuerfel
                wurf = wuerfle();

                // spielzug Computer
                this.spielzug(computerPlayer, wurf);

                // if wuerfel == 6, nochmals
                LastDies.get(computerPlayer).offer(wurf);
            } while ((wurf == 6) && (!this.isOver()));

            // neue Runde - zurueck zur view
            Round++;
        }
        return "";
    }

    protected int wuerfle() {
        double zufall;
        zufall = Math.random();
        zufall = zufall * 6 + 0.5;
        return (int) Math.round(zufall);
    }

    protected void spielzug(Spieler player, int wurf) {

        // Spielstart pruefen:
        if (this.Playarea.isPlayerInStart(player)) {
            if (wurf != 6) {
                return;
            }
        }

        Feld newField = this.Playarea.getNewField(player, wurf);

        // Gegner geschlagen?
        if (newField.getContent() != null) {
            // Set the other player on a start field
            this.Playarea.resetPlayer(newField.getContent());
        }

        // Spielzug durchfuehren
        this.Playarea.setPlayerToField(player, newField);

        // gewonnen?
        if (this.Playarea.isPlayerFinished(player)) {
            this.Over = true;
        }
    }
    
    public Spielfeld getPlayarea() {
        return Playarea;
    }

    public String getPlayer1DiceRolls() throws Exception {
        if (Players.size() < 1)
            throw new Exception("Invalid player");

        Spieler s = Players.get(0);
        Integer i = 0;
        if(!LastDies.get(s).isEmpty()) {
            i = LastDies.get(s).getLast();
        }

        return i.toString();
    }

    public List<Integer> getPlayer2DiceRolls() throws Exception {
        if (Players.size() < 2)
            throw new Exception("Invalid player");

        Spieler s = Players.get(1);

        return LastDies.get(s);
    }

}
