package gameapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;

/**
 * Class representing a Mensch aergere Dich nicht game
 */
@ManagedBean(name="game")
@SessionScoped
public class Game {

    /**
     * Current round of the game
     */
    private int round = 1;
    /**
     * List of player's participating in this game
     */
    private List<Player> players;
    /**
     * Current player
     */
    private Player currentplayer = null;
    /**
     * Sequence of fields in the game the player 1 has to cover
     */
    private List<Integer> routeplayer1 = Arrays.asList(43, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 57, 58, 59, 60);
    /**
     * Sequence of fields in the game the player 2 has to cover
     */
    private List<Integer> routeplayer2 = Arrays.asList(45, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 61, 62, 63, 64);
    /**
     * Sequence of fields in the game the player 3 has to cover
     */
    private List<Integer> routeplayer3 = Arrays.asList(50, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 65, 66, 67, 68);
    /**
     * Sequence of fields in the game the player 4 has to cover
     */
    private List<Integer> routeplayer4 = Arrays.asList(56, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 69, 70, 71, 72);
    /**
     * Sequence of fields in the game the players have to cover
     */
    private List<List<Integer>> routes = Arrays.asList(routeplayer1, routeplayer2, routeplayer3, routeplayer4);
    /**
     * Dice that is used in this game
     */
    private Dice dice = new Dice();
    /**
     * Specifies if the game is over (
     * <code>true</code>) or not (
     * <code>false</code)
     */
    private boolean gameOver = false;
    /**
     * Starting time of the game
     */
    private long gamestarttime = System.currentTimeMillis();
    /**
     * Time already spent in this game
     */
    private long spenttime;
    /**
     * Player currently leading the game
     */
    private Player leader = null;
    /**
     * Current score of the computer-controlled opponent
     */
    private List<Integer> computeropponentscore = null;
    /**
     * real opponent or computer opponent
     */
    private Boolean computeropponent = null;
    /**
     * Fields of the board
     */
    private List<Field> boardFields = null;

    /**
     * Constructs a new {@link Game} for the specified players
     * <code>player</code> and
     * <code>opponent</code>.
     *
     * @param player Player (real) participating in the game
     * @param opponent Computer-controlled (virtual) player participating in the
     * game
     */
    public Game(List<Player> player, Boolean computeropponent) {
        // guard illegal arguments
        if (player.size() < 1) {
            throw new IllegalArgumentException("At least one player is necessary to play.");
        }

        for (int i = 0; i < player.size(); ++i) {
            player.get(i).setRoute(routes.get(i));
            player.get(i).setPosition(routes.get(i).get(0));
        }
        this.computeropponent = computeropponent;
        this.players = new ArrayList<Player>();
        this.players.addAll(player);
        this.currentplayer = this.players.get(0);

        boardFields = new ArrayList<Field>();
        for (int i = 0; i < 72; ++i) {
            Field f = new Field();
            f.setIndex(i+1);
            switch(i) {
                case 0: f.setImg("img/field1");
                        f.setText("Feld 1: erstes Feld Spieler 1");
                        break;
                case 9: f.setImg("img/field");
                        f.setText("Feld 10: letztes Feld Spieler 2");
                        break;
                case 10: f.setImg("img/field2");
                        f.setText("Feld 11: erstes Feld Spieler 2");
                        break;
                case 19: f.setImg("img/field");
                        f.setText("Feld 20: letztes Feld Spieler 3");
                        break;
                case 20: f.setImg("img/field3");
                        f.setText("Feld 21: erstes Feld Spieler 3");
                        break;
                case 29: f.setImg("img/field");
                        f.setText("Feld 30: letztes Feld Spieler 4");
                        break;
                case 30: f.setImg("img/field4");
                        f.setText("Feld 31: erstes Feld Spieler 4");
                        break;
                case 39: f.setImg("img/field");
                        f.setText("Feld 40: letztes Feld Spieler 1");
                        break;
                default:
                    if(i < 40) {
                        f.setImg("img/field");
                        f.setText("Feld "+ (i+1));
                    } else if (i < 44) {
                        f.setImg("img/field1");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 1");
                    } else if (i < 48) {
                        f.setImg("img/field2");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 2");
                    } else if (i < 52) {
                        f.setImg("img/field3");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 3");
                    } else if (i < 56) {
                        f.setImg("img/field4");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 4");
                    } else if (i < 60) {
                        f.setImg("img/field1");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 1");
                    } else if (i < 64) {
                        f.setImg("img/field2");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 2");
                    } else if (i < 68) {
                        f.setImg("img/field3");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 3");
                    } else if (i < 72) {
                        f.setImg("img/field4");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 4");
                    }
            }
            boardFields.add(f);
        }
        boardFields.get(routeplayer1.get(0) - 1).setOccupyingPlayer(1);
        boardFields.get(routeplayer2.get(0) - 1).setOccupyingPlayer(2);
    }
    
    public Game() {
        Boolean computeropponent = true;
        List<Player> player = Arrays.asList(new Player("Mario"));
        
        // guard illegal arguments
        if (player.size() < 1) {
            throw new IllegalArgumentException("At least one player is necessary to play.");
        }

        for (int i = 0; i < player.size(); ++i) {
            player.get(i).setRoute(routes.get(i));
            player.get(i).setPosition(routes.get(i).get(0));
        }
        this.computeropponent = computeropponent;
        this.players = new ArrayList<Player>();
        this.players.addAll(player);
        this.currentplayer = this.players.get(0);

        boardFields = new ArrayList<Field>();
        for (int i = 0; i < 72; ++i) {
            Field f = new Field();
            f.setIndex(i+1);
            switch(i) {
                case 0: f.setImg("img/field1");
                        f.setText("Feld 1: erstes Feld Spieler 1");
                        break;
                case 9: f.setImg("img/field");
                        f.setText("Feld 10: letztes Feld Spieler 2");
                        break;
                case 10: f.setImg("img/field2");
                        f.setText("Feld 11: erstes Feld Spieler 2");
                        break;
                case 19: f.setImg("img/field");
                        f.setText("Feld 20: letztes Feld Spieler 3");
                        break;
                case 20: f.setImg("img/field3");
                        f.setText("Feld 21: erstes Feld Spieler 3");
                        break;
                case 29: f.setImg("img/field");
                        f.setText("Feld 30: letztes Feld Spieler 4");
                        break;
                case 30: f.setImg("img/field4");
                        f.setText("Feld 31: erstes Feld Spieler 4");
                        break;
                case 39: f.setImg("img/field");
                        f.setText("Feld 40: letztes Feld Spieler 1");
                        break;
                default:
                    if(i < 40) {
                        f.setImg("img/field");
                        f.setText("Feld "+ (i+1));
                    } else if (i < 44) {
                        f.setImg("img/field1");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 1");
                    } else if (i < 48) {
                        f.setImg("img/field2");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 2");
                    } else if (i < 52) {
                        f.setImg("img/field3");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 3");
                    } else if (i < 56) {
                        f.setImg("img/field4");
                        f.setText("Feld "+(i+1)+": Startfeld Spieler 4");
                    } else if (i < 60) {
                        f.setImg("img/field1");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 1");
                    } else if (i < 64) {
                        f.setImg("img/field2");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 2");
                    } else if (i < 68) {
                        f.setImg("img/field3");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 3");
                    } else if (i < 72) {
                        f.setImg("img/field4");
                        f.setText("Feld "+(i+1)+": Zielfeld Spieler 4");
                    }
            }
            boardFields.add(f);
        }
        boardFields.get(routeplayer1.get(0) - 1).setOccupyingPlayer(1);
        boardFields.get(routeplayer2.get(0) - 1).setOccupyingPlayer(2);
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return
     * <code>true</code> if this game is over,
     * <code>false</code> otherwise
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public long getSpentTime() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return spenttime;
    }
    
    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     * 
     * TODO: move to controller bean
     */
    public String getSpentTimeFormat() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return (spenttime/(1000 * 60)) + " min " + ((spenttime/1000) % 60) + " sec";
    }

    /**
     * Returns the rounds already played in this game
     *
     * @return the rounds already played in this game
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Return an unmodifiable list of the players particiapting in this game
     *
     * @return an unmodifiable list of the players participating in this game
     */
    public List<Player> getPlayers() {
        //return Collections.unmodifiableList(this.players);
        return this.players;
    }
    
    /**
     * Return the amount of players
     *
     * @return integer with amount of players
     * 
     * TODO: move to controller
     */
    public Integer getPlayersCnt() {
        //return Collections.unmodifiableList(this.players);
        return this.players.size();
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public Player getLeader() {
        return this.leader;
    }
    
    public String rollDice() {
        rollthedice(currentplayer);
        return "";
    }

    /**
     * Rolls the dice for the given player and updates the position of the
     * player's counter according to the score
     *
     * @param player Player who rolls the dice
     * @return score
     */
    public int rollthedice(Player player) {
        if (gameOver) {
            throw new IllegalArgumentException(
                    "Game is over. Rolling the dice is not allowed.");
        }

        if (player != currentplayer) {
            throw new IllegalArgumentException(
                    "It is not this player's turn.");
        }

        int score = dice.roll();
        player.setDiceScore(score);

        List<Integer> route = player.getRoute();
        int position = player.getPosition();

        if (route.indexOf(position) + score >= route.size()) {
            /*
             * The counter of the player is standing in front of the HOME Base
             * but threw a too high score --> Do nothing
             */
        } else if (position == route.get(0) && score != 6) {
            /*
             * The counter of the player is standing in the Starting Square and
             * the player didn't threw a six. --> Do nothing
             */
        } else if (position == route.get(0) && score == 6) {
            /**
             * Player starts
             */
            player.setPosition(route.get(1));
            updateBoardFields(route.get(1), position, player);

            /**
             * Identify the leading player
             */
            identifyLeader();
        } else {
            /**
             * Move on field
             */
            int newposition = route.get(route.indexOf(position) + score);
            player.setPosition(newposition);
            updateBoardFields(newposition, position, player);

            /**
             * Test if the figure of the player reached the HOME Base and the
             * game is over
             */
            if (newposition == route.get(route.size() - 1)
                    || newposition == route.get(route.size() - 2)
                    || newposition == route.get(route.size() - 3)
                    || newposition == route.get(route.size() - 4)) {
                // Spieler ist im Ziel
                gameOver = true;
            }

            /**
             * Identify the leading player
             */
            identifyLeader();
        }
        if (currentplayer == players.get(0) && computeropponent == true) {
            // It is the turn of the real player 
            if (score != 6 && !gameOver) {
                // It is the turn of the computer-controlled opponent
                currentplayer = players.get(1);
                computeropponentscore = new ArrayList<Integer>();
                int scoreopponent = -1;
                do {
                    scoreopponent = rollthedice(currentplayer);
                    computeropponentscore.add(scoreopponent);
                } while (scoreopponent == 6 && !gameOver);
                currentplayer = players.get(0);
                ++this.round;
            } else {
                computeropponentscore = new ArrayList<Integer>();
            }
        } else {

            if (score != 6 && !gameOver) {
                currentplayer = players.get((players.indexOf(currentplayer) + 1) % players.size());
            }
        }

        return score;
    }

    /**
     * Updates the player's position and tests if another player is knocked off.
     *
     * @param player Player whose counter moved to a new position
     * @param newposition New Position of the player's counter
     */
    private void updateBoardFields(int posnew, int posold, Player player) {
        // a player is knocked off
        if (boardFields.get(posnew).getOccupyingPlayer() > -1) {
            Player otherplayer = players.get(boardFields.get(posnew).getOccupyingPlayer());
            otherplayer.setPosition(otherplayer.getRoute().get(0));
            updateBoardFields(otherplayer.getRoute().get(0), -1, otherplayer);
        }
        boardFields.get(posnew - 1).setOccupyingPlayer(players.indexOf(player) + 1);
        if (posold != -1) {
            boardFields.get(posold - 1).setOccupyingPlayer(-1);
        }
    }

    /**
     * Identify the leading player
     */
    private void identifyLeader() {
        List<Integer> positionen = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); ++i) {
            positionen.add(players.get(i).getRoute().indexOf(players.get(i).getPosition()));
        }
        List<Integer> positionensorted = new ArrayList<Integer>();
        positionensorted.addAll(positionen);
        Collections.sort(positionensorted);
        int weitesteposition = positionensorted.get(positionensorted.size() - 1);
        if (weitesteposition > positionensorted.get(positionensorted.size() - 2)) {
            this.leader = players.get(positionen.indexOf(weitesteposition));
        } else {
            this.leader = null;
        }
    }

    /**
     * Returns the current player
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return this.currentplayer;
    }

    /**
     * Returns the fields of the board
     *
     * @return fields of the board
     */
    public List<Field> getBoardFields() {
        return this.boardFields;
    }


    public int getBoardFieldsCnt() {
        return this.boardFields.size();
    }

    /**
     * Returns the score of the computer-controlled opponent
     *
     * @return the score of the computer-controlled opponent
     */
    public List<Integer> getOpponentScore() {
        return computeropponentscore;
    }
}