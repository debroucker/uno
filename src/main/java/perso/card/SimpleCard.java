package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class SimpleCard extends Card<TypeSimple>{

    public SimpleCard(Colors color, TypeSimple type){
        super(color, type);
    }

    public boolean isPossible(Card<?> otherCard){
        if (otherCard instanceof SimpleCard) {
            SimpleCard otherSimpleCard = (SimpleCard)otherCard;
            return otherSimpleCard.getColor().equals(this.getColor()) || otherSimpleCard.getType().getNumber() == this.getType().getNumber();
        }
        else {
            return otherCard.getColor().equals(this.getColor());
        }
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        currentPlayer.dropCard(this);
    }

    public String toString(){
        return "Simple Card : " + this.getColor().toString() + ", " + this.getType().getNumber();
    }
    
}