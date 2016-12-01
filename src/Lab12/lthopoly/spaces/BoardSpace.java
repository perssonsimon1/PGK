package Lab12.lthopoly.spaces;

import Lab12.lthopoly.GameBoard;

public abstract class BoardSpace {

    /**
     * Returns a array of int describing possible game actions available while on this space
     */
    public abstract int[] getPossibleActions(GameBoard board);

    /**
     * Executes a game action available while on this space
     */
    public abstract void action(GameBoard board, int action);

    /**
     * Returns a string representation of this BoardSpace
     */
    public abstract String toString();
}
