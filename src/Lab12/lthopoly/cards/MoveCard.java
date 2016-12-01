package Lab12.lthopoly.cards;

import java.io.FileNotFoundException;

public class MoveCard {

    private String description;
    private int positionAdjustment;
    /**
     * Creates a new MoveCard
     */
    public MoveCard(String description, int positionAdjustment) {
        this.description = description;
        this.positionAdjustment = positionAdjustment;
    }


    /**
     * Returns the position adjustment
     */
    public int getPositionAdjustment(){
        return positionAdjustment;
    }

    /**
     * Returns the description of why the position is adjusted
     */
    public String getDescription() {
        return description;
    }


}
