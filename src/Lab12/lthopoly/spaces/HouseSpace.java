package Lab12.lthopoly.spaces;

import Lab12.lthopoly.GameBoard;

import Lab12.lthopoly.Player;
import Lab12.lthopoly.TextUI;

public class HouseSpace extends BoardSpace {
    private Player owner;        // OBS importera player uppe, blir null från början eftersom ingen tilldelning i konstruktorn
    private int rent;    // int lila = primitiv typ, bara ett tal. Integer kan man göra grejer med (.toString, .compare, etc massor!!!)
    private String description;

    /**
     * Creates a new housespace with rent and a description
     */
    public HouseSpace(int rent, String description) {
        this.rent = rent;
        this.description = description;

    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        if (owner == null) {
            return new int[]{GameBoard.END_TURN, GameBoard.BUY_PROPERTY};
        } else if (owner == board.getCurrentPlayer()) {
            return new int[]{GameBoard.END_TURN};
        } else {
            return new int[]{GameBoard.PAY_RENT};
        }
    }

    /**
     * Performs a HouseSpace-related action.
     */
    @Override
    public void action(GameBoard board, int action) {
        if (owner == null) {	/* ingen äger huset*/
            // antingen: köp fastigheten, bli av med pengar. "this" för att det är klassens attribut, övertydlig
            if (action == GameBoard.BUY_PROPERTY) {
                board.getCurrentPlayer().adjustMoney(-this.rent);
                owner = board.getCurrentPlayer();
                TextUI.addToLog(owner.getName() + " Köpte: " + description);
                board.doAction(GameBoard.END_TURN);
            }
            else if (action == GameBoard.END_TURN) {
                board.doAction(GameBoard.END_TURN);
            }
        } else if (owner == board.getCurrentPlayer()) {	/* du själv äger huset*/
            board.doAction(GameBoard.END_TURN);
        } else if (owner != board.getCurrentPlayer()) { /* en annan spelare äger huset*/
            // betala hyra, bli av med pengar
            board.getCurrentPlayer().adjustMoney(-this.rent);
            owner.adjustMoney(Math.abs(rent));
            board.doAction(GameBoard.END_TURN);
        }
    }

    /**
     * Returns a string representation of the HouseSpace with the format
     * "HouseName [Owner] (Rent)"
     */
    @Override
    public String toString() {
        return description + " [" + owner + "] (" + rent + ")";
    }
}
