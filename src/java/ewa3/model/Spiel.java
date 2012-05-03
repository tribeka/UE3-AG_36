package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.beans.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

@ManagedBean(name="mygame")
@SessionScoped
public class Spiel implements Serializable {
    public List<Spieler> Player;
    public Spielfeld Playarea;

    private Integer Round;
    private long Starttime;
    private Boolean Over;

    /*TODO:
    * parameterlose funktionen für text -> feld.getID (fieldXX), feld.getString(), feld.getImgString()
    * liste statt iteratoren
    */
    public Spiel(Spieler player) {
        Player.add(player);
        Player.add(new Spieler("Computer"));

        this.reset();
    }

    public void reset() {
        Playarea = new SpielfeldImpl(Player);
        Round = 0;
        Over = false;
        Starttime = new Date().getTime();
    }

    public Boolean isOver() {
        return Over;
    }

    public Spieler getLeader() {
        Iterator<Spieler> it = Player.iterator();
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

    public int getPlayerCnt() {
        return Player.size();
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
        Spieler humanPlayer = Player.get(0);
        Spieler computerPlayer = Player.get(1);

        // wuerfeln
        int wurf = wuerfle();

        // spielzug
        this.spielzug(humanPlayer, wurf);
        humanPlayer.LastDies.offer(wurf);

        // if wuerfel == 6, zurueck zur view, da Spieler nochmals an der Reihe ist
        if ((wurf != 6) && (!this.isOver())) {
            do {
                // Computer player - getWuerfel
                wurf = wuerfle();

                // spielzug Computer
                this.spielzug(computerPlayer, wurf);

                // if wuerfel == 6, nochmals
                computerPlayer.LastDies.offer(wurf);
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
}
