package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class JockerCard extends Card<TypeWithoutAttribute>{

    public JockerCard(TypeWithoutAttribute type){
        super(Colors.NONE, type);
    }

    public boolean isPossible(Card<?> otherCard){
       return true;
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        int choice;
        Colors newColor;
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
        return "Jocker Card : " + this.getColor();
    }
    
}