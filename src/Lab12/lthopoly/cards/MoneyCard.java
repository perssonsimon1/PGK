package Lab12.lthopoly.cards;

public class MoneyCard {

    /**
     * Creates a new MoneyCard
     */

    private String description;
    private int money;

    public MoneyCard(String description, int money) {
        this.description = description;
        this.money = money;


    }

    /**
     * Returns the cards money adjustment value
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Returns the description of why the money is adjusted
     */
    public String getDescription() {
        return this.description;
    }
}
