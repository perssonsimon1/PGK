package Lab12.lthopoly.spaces;

import Lab12.lthopoly.GameBoard;
import java.util.Random;		// size: om ngt kan ändra storlek, är en funkiton, length om något inte kan ändra storlek & attribut som ändras när det blir tilldelat

import Lab12.lthopoly.Player;
import Lab12.lthopoly.TextUI;
import Lab12.lthopoly.cards.MoneyCard;

public class MoneySpace extends BoardSpace {
    private MoneyCard[] cards;
    /**
     * Creates a new MoneySpace. When landing on this space a card from the card
     * array will be drawn
     */
    public MoneySpace(MoneyCard[] cards) {	// konstruktor
        this.cards = cards;		//this.cards refererar till klassens attribut cards på rad 11
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        return new int[]{GameBoard.END_TURN, GameBoard.DRAW_CARD};
    }

    /**
     * Performs a MoneySpace-related action.
     */
    public void action(GameBoard board, int action) {
        if (action == GameBoard.DRAW_CARD) {
            int rnd = new Random().nextInt(this.cards.length);
            MoneyCard card = this.cards[rnd];
            Player player = board.getCurrentPlayer();

            player.adjustMoney(card.getMoney());
            TextUI.addToLog(player.getName() + " drog ett kort: " + card.getDescription());
            board.doAction(GameBoard.END_TURN);
        }
        else if (action == GameBoard.END_TURN) {
            board.doAction(GameBoard.END_TURN);
        }

    }

    /**
     * Returns a string representation of the MoneySpace
     */
    @Override
    public String toString() {
        return "Pengaruta";
    }
}