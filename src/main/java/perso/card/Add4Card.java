package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class Add4Card extends Card<TypeAdd4>{

    public Add4Card(TypeAdd4 type){
        super(Colors.NONE, type);
    }

    public boolean isPossible(Card<?> otherCard){
       return true;
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        int choice;
        Colors newColor;
        Card<?> cardToAdd;
        System.out.println();
        for (int i = 0; i < this.getType().getNumberOfCardsToAdd(); i++){
            cardToAdd = board.takeFirstCardInDraw();
            board.removeFirstCardToDraw();
            nextPlayer.addCard(cardToAdd);
            System.out.println(nextPlayer.getName() + " draw " + cardToAdd.toString());
        }
        this.displayUseCard(currentPlayer);
        choice = board.intChooser(1, Colors.NONE.possibleColors().size());
        newColor = Colors.NONE.possibleColors().get(choice);
        this.changeColor(newColor);
        currentPlayer.dropCard(this);
    }

    public void displayUseCard(Player currentPlayer){
        System.out.println("\nYou can choose color : ");
        System.out.println(currentPlayer.getBoard().listToString(Colors.NONE.possibleColors()));
    }

    public String toString(){
        return "Add4 Card : " + this.getColor();
    }

    
}