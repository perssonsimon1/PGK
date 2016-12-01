package Lab12.lthopoly.parser;

 import java.io.File;
 import java.util.Scanner;

 import Lab12.lthopoly.cards.MoneyCard;
 import Lab12.lthopoly.cards.MoveCard;
 import Lab12.lthopoly.spaces.BoardSpace;
 import Lab12.lthopoly.spaces.MoveSpace;
 import Lab12.lthopoly.spaces.HouseSpace;
 import Lab12.lthopoly.spaces.MoneySpace;

 import java.util.ArrayList;

public class DocumentParser {

    /**
     * Returns a ArrayList of Boardspaces loaded from a file
     */
    public static ArrayList<BoardSpace> getBoard(File boardFile, File moneyFile, File moveFile) {
        ArrayList<BoardSpace> board = new ArrayList<> ();
        MoneyCard[] money = getMoneyCards(moneyFile);
        MoveCard[] move = getMoveCards(moveFile);

        try {
            Scanner scan = new Scanner(boardFile);
            BoardSpace newMoney = new MoneySpace(money);
            BoardSpace newMove = new MoveSpace(move);

            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.startsWith("Money")) {
                    board.add(newMoney);
                } else if (line.startsWith("Move")) {
                    board.add(newMove);
                } else {
                    String[] split = line.split(";");
                    BoardSpace newHouse = new HouseSpace(Integer.parseInt(split[1]), split[2]);
                    board.add(newHouse);

                }
            }
            scan.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return board;

    }


    /**
     * Returns a array of MoneyCards loaded from file
     */
    private static MoneyCard[] getMoneyCards(File file) {

        ArrayList<MoneyCard> moneycards = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String [] split = line.split(";");
                MoneyCard newCard = new MoneyCard(split[0], Integer.parseInt(split[1]));
                moneycards.add(newCard);
            }
            scan.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return moneycards.toArray(new MoneyCard []{});
    }

    /**
     * Returns a array of MoveCards loaded from file
     */
    private static MoveCard[] getMoveCards(File file) {
        ArrayList<MoveCard> movecards = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String [] split = line.split(";");
                MoveCard newCard = new MoveCard(split[0], Integer.parseInt(split[1]));
                movecards.add(newCard);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return movecards.toArray(new MoveCard []{});

    }


}
