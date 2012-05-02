package gameapi;

import java.util.Collections;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Class representing a player playing in a {@link Game}.
 */
@ManagedBean(name="player")
@ApplicationScoped
public class Player {

	/**
	 * The name of this user
	 */
	private String name;
        
        /**
         * The current position of the user's counter
         */
        private int position;
        
        /**
         * stores last dice score
         */
        private String diceScore;
       
        /**
         * Sequence of fields in the game the player has to cover
         */
        private List<Integer> route;
        
	/**
	 * Initializes a {@link Player} with the specified <code>name</code>.
	 * 
	 * @param name to set
	 */
	public Player(String name) {
		super();
		this.name = name;
                diceScore = "0";
	}

	/**
	 * Returns the name of this player.
	 * 
	 * @return the name
	 */
	public String getName() {
            return name;
	}
        
        /**
         * Return the actual position of this player's counter
         * 
         * @return the actual position of this player's counter
         */
        public int getPosition() {
            return this.position;
        }
        
        /**
         * Sets the actual position of this player's counter
         * 
         * @param pos actual position of this player's counter
         */
        public void setPosition(int pos){
            this.position = pos;
        }
        
        /**
         * Get the sequence of fields the player has to cover
         * 
         * @return the sequence of fields the player has to cover 
         */
        public List<Integer> getRoute() {
            return Collections.unmodifiableList(this.route);
        }
        
        /**
         * Set the sequence of fields the player has to cover
         * 
         * @param route sequence of fields the player has to cover
         */
        public void setRoute(List<Integer> route) {
            this.route = route;
        }
        
        public String getDiceScore() {
            return diceScore;
        }
        
        public void setDiceScore(Integer i) {
            diceScore = i.toString();
        }
        
        public String getDiceScoreImg() {
            return "img/wuerfel" + diceScore + ".png";
        }
        
        public String getDiceScoreText() {
            return "W&uuml;rfel Zahl " + diceScore;
        }
}
