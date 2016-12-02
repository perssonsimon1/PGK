package Lab12.lthopoly

import scala.collection.{JavaConversions, JavaConverters}
import scala.collection.JavaConverters._

object Main {
  def main(args: Array[String]): Unit = {

    def initGameBoard(): GameBoard = {
      println("Ange antal spelare:")
      val playerNum = scala.io.StdIn.readInt
      val players = for(i <- 1 to playerNum) yield{
        println(s"Ange spelare $i:s namn:")
        val name = scala.io.StdIn.readLine
        val money = 1000
        val pos = 0
        new Player( name, money, pos )
      }
      new GameBoard( players.asJava )
    }

    def Play(gameBoard: GameBoard): Unit = {
      while( !gameBoard.isGameOver() ){
        for( currPlayer <- gameBoard.getPlayers().asScala ){
        val chosenAction = getAction( gameBoard )

          gameBoard.doAction( chosenAction )
        }
      }
    }

    val gameBoard = initGameBoard()

    Play( gameBoard )

    val list = JavaConversions.asScalaBuffer(gameBoard.getStatistics)

    print(TextUI.plotStatistics(list.map(x => x.toInt)))
  }




  /**
    * Retrieves all possible actions from GameBoard and joins them with
    * a corresponding description String into tuples.
    * The tuples are then sent to the promptForInput method in TextUI.
    *
    * @return the user's choice as given by promptForInput.
    */
  def getAction(board: GameBoard): Int = {
    val possibleActions = board.getPossibleActions().sorted
    val orderedPA = if(possibleActions.head == 0) possibleActions.tail :+ possibleActions.head else possibleActions
    val actionDescriptions = Vector( //fråga om detta
      "Kasta tärning"   	//    THROW_DICE = 0;
      ,"Dra kort"       	//    DRAW_CARD = 1;
      ,"Köp fastigheten"	//    BUY_PROPERTY = 2;
      ,"Betala hyra"		//    PAY_RENT = 3;
      ,"Avsluta din runda"//    END_TURN = 4;
      ,"Default view"		//    DEFAULT_VIEW = 5;
      ,"Visa brädet"		//    SHOW_BOARD = 6;
      ,"Avsluta spelet"	//    EXIT_GAME = 7;
    )
    val actionTuples = for( action <- possibleActions ) yield{
      (action, actionDescriptions( action ) )
    }
    TextUI.updateConsole(board)
    val chosenAction = TextUI.promptForInput( actionTuples )
    return chosenAction


  }

}
