package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class StopCard extends Card<TypeWithoutAttribute>{

    public StopCard(Colors color, TypeWithoutAttribute type){
        super(color, type);
    }

    public boolean isPossible(Card<?> otherCard){
       return otherCard.getColor().equals(this.getColor());
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        nextPlayer.cantPlay();
        currentPlayer.dropCard(this);
    }

    public String toString(){
        return "Stop Card : " + this.getColor().toString();
    }
    
}