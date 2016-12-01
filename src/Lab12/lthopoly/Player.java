package Lab12.lthopoly;

public class Player {
    private String name;
    private int money;
    private int pos;

    /**
     * Creates a new player
     */
    public Player(String name, int money, int pos) {
        this.name = name;
        this.money = money;
        this.pos = pos;

    }

    /**
     * Returns the players money
     */
    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    /**
     * Adjusts the players money
     */
    public void adjustMoney(int money) {
        this.money = this.money + money;
    }

    /**
     * Returns the players position
     */
    public int getPosition() {
        return this.pos;
    }

    /**
     * Returns a string representation of the player
     */
    @Override
    public String toString() {
        return pos + ", " + name + ", " + money + ":-";
    }

    /**
     * Sets the players position
     */
    public void setPosition(int pos) {
        this.pos = pos;
    }
}
