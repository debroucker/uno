package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public class ReverseCard extends Card<TypeWithoutAttribute>{

    public ReverseCard(Colors color, TypeWithoutAttribute type){
        super(color, type);
    }

    public boolean isPossible(Card<?> otherCard){
       return otherCard.getColor().equals(this.getColor());
    }

    public void useCard(Board board, Player currentPlayer, Player nextPlayer){
        System.out.println("\n" + currentPlayer.getName() + " change order");
        board.changeOrderPlayers(currentPlayer);
        currentPlayer.dropCard(this);
    }

    public String toString(){
        return "Reverse Card : " + this.getColor().toString();
    }
    
}