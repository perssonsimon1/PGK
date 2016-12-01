package Lab12.lthopoly.spaces;

import java.util.Random;

import Lab12.lthopoly.GameBoard;
import Lab12.lthopoly.cards.MoveCard;

public class MoveSpace extends BoardSpace {
    private MoveCard[] cards;
    /**
     * Creates a new MoveSpace. When landing on this space a card from the card array will be drawn
     */
    public MoveSpace(MoveCard[] cards) {	// konstruktor, anropas när objektet skapas
        this.cards = cards;
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        return new int[]{GameBoard.DRAW_CARD, GameBoard.END_TURN};
    }

    /**
     * Performs a MoveSpace-related action.
     */
    @Override
    public void action(GameBoard board, int action) {
        if (action == GameBoard.DRAW_CARD) {
            int rnd = new Random().nextInt(this.cards.length);
            board.getCurrentPlayer().setPosition(this.cards[rnd].getPositionAdjustment());
        }
        else if (action == GameBoard.END_TURN) {
            board.doAction(GameBoard.END_TURN);
        }
    }

    /**
     * Returns a string representation of the MoveSpace
     */
    @Override
    public String toString() {
        return "FlyttaRuta";
    }
}
