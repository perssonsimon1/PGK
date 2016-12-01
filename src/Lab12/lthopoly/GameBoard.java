package Lab12.lthopoly;

import Lab12.lthopoly.parser.DocumentParser;
import Lab12.lthopoly.spaces.BoardSpace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    /**
     * Menu constants
     */
    public static final int THROW_DICE = 0;
    public static final int DRAW_CARD = 1;
    public static final int BUY_PROPERTY = 2;
    public static final int PAY_RENT = 3;
    public static final int END_TURN = 4;
    public static final int DEFAULT_VIEW = 5;
    public static final int SHOW_BOARD = 6;
    public static final int EXIT_GAME = 7;

    private enum gameState {THROWN, NOT_THROWN}


    /**
     * Attributes
     */
    private ArrayList<BoardSpace> spaces;
    private List<Player> players;
    private int currentPlayer = 0;
    private gameState currentState = gameState.NOT_THROWN;
    private BoardSpace currentSpace;


    private ArrayList<Integer> totalMoney;



    /**
     * Creates a new board ready to play
     */
    public GameBoard(List<Player> players) {
        this.players = players;
        this.spaces = DocumentParser.getBoard(new File("/util/board.txt"), new File("/util/moneyfile.txt"), new File("/util/movefile.txt"));
    }


    private void nextPlayer() {
        if(currentPlayer < players.size()-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
            updateTotalMoney();
        }
        TextUI.addToLog("Nästa spelare: " + players.get(currentPlayer).getName());
        currentState = gameState.NOT_THROWN;
    }

    /**
     * Returns an int array containing possible game actions.
     * A game action can be any of the static constants in
     * GameBoard
     */
    public int[] getPossibleActions() {
        int[] defaultActions = new int[]{EXIT_GAME, SHOW_BOARD, DEFAULT_VIEW};
        int[] out;
        if(currentState == gameState.THROWN){
            out = merge(defaultActions, currentSpace.getPossibleActions(this));
        } else {
            out = merge(defaultActions, new int[]{THROW_DICE});
        }

        return out;
    }

    private int[] merge(int[] a, int[] b){
        int count = 0;
        int[] out = new int[a.length + b.length];

        for(int i: a)
        {
            out[i]=a[i];
            count++;
        }
        for(int j: b)
        {
            out[count++]=j;
        }
        return out;
    }

    /**
     * Checks whether the game is over or not
     */
    public boolean isGameOver() {
        boolean isOver = false;
        for (Player player: players) {
            if(player.getMoney() <= 0){
                isOver = true;
            }
        }
        return isOver;
    }

    /**
     * Returns the player with the most money
     */
    public Player getRichestPlayer() {
        int max = 0;
        int richest = 0;
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getMoney() > max) {
                richest = i;
            }
        }
        return players.get(richest);
    }

    /**
     * Returns a list of all players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns a list of all BoardSpaces
     */
    public List<BoardSpace> getBoardSpaces() {
        return spaces;
    }

    /**
     * Performs an action for the current player
     */
    public void doAction(int action) {
        switch (action){
            case EXIT_GAME:
                System.exit(4);
                break;
            case DEFAULT_VIEW:
                TextUI.printStatus(this);
                break;
            case SHOW_BOARD:
                TextUI.printBoard(this);
                break;
            case END_TURN:
                TextUI.addToLog(players.get(currentPlayer).getName() + " har avslutat sin runda");
                nextPlayer();
                break;
            case THROW_DICE:
                diceRoll();
                break;
            default:
                currentSpace.action(this, action);
                break;
        }

    }

    private void diceRoll(){
        int dice = (int)(Math.random()*6) + 1;
        moveCurrentPlayer(dice);
        TextUI.addToLog(players.get(currentPlayer).getName() + " slog en " + dice + ":a");
        currentState = gameState.THROWN;
    }

    /**
     * Returns the currently active player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Returns the boardspace corresponding to the position of the current player.
     */
    public BoardSpace getCurrentBoardSpace() {
        return currentSpace;
    }

    /**
     * Moves the currently active player adjustments spaces forward. Negative adjustment moves the player backwards
     */
    private void moveCurrentPlayer(int adjustment) {
        int current = players.get(currentPlayer).getPosition();
        int adjusted = (current + adjustment) % spaces.size();
        currentSpace = spaces.get(adjusted);
        players.get(currentPlayer).setPosition(adjusted);
    }

    /**
     * Returns an ArrayList<Integer> where each element contains the total sum of all players' money
     * at the end of a round.
     * E.g. list.get(0) is the total amount of money in the game after the first round.
     */
    public ArrayList<Integer> getStatistics() {
        return totalMoney;
    }

    private void updateTotalMoney() { //måste användas varje gång en hel runda är slut (och antagligen när spelet tar slut, även om rundan inte är slut)
        int temp = 0;
        for (Player i: players) {
            temp += i.getMoney();
        }
        totalMoney.add(temp);
    }

    /**
     * String Representation of the GameBoard
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Rutans Namn [Ägare] (Pris/Hyra) (Spelare, Pengar)*\n");
        out.append("--------------------------------------------------\n");
        for (int i = 0; i < spaces.size(); i++) {

            out.append(spaces.get(i).toString() + " ");

            for (int j = 0; j < players.size(); j++)
                if (players.get(j).getPosition() == i)
                    out.append("(" + players.get(j).toString() + "," + players.get(j).getMoney() + ")");//add name to row

            out.append("\n");
        }
        return out.toString();
    }
}
