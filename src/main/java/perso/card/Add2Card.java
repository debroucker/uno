package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class Add2Card extends Card<TypeAdd2>{

    public Add2Card(Colors color, TypeAdd2 type){
        super(color, type);
    }

    public boolean isPossible(Card<?> otherCard){
       return otherCard.getColor().equals(this.getColor());
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        Card<?> cardToAdd;
        System.out.println();
        for (int i = 0; i < this.getType().getNumberOfCardsToAdd(); i++){
            cardToAdd = board.takeFirstCardInDraw();
            board.removeFirstCardToDraw();
            nextPlayer.addCard(cardToAdd);
            System.out.println(nextPlayer.getName() + " draw " + cardToAdd.toString());
        }
        currentPlayer.dropCard(this);
    }

    public String toString(){
        return "Add2 Card : " + this.getColor().toString();
    }
    
}