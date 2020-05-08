package perso.card;
import perso.player.*;
import perso.board.*;
import perso.color.*;
import perso.type.*;

public abstract class Card<T extends TypeOfCards> {

    protected Colors color;
    protected T type;

    public Card(Colors color, T type){
        this.color = color;
        this.type = type;
    }

    /**
     * @return the color
     */
    public Colors getColor() {
        return color;
    }

    /**
     * @return the type
     */
    public T getType() {
        return type;
    }

    public void changeColor(Colors newColor){
        this.color = newColor;
    }

    public abstract String toString();

    public abstract boolean isPossible(Card<?> otherCard);

    public abstract void useCard(Board board, Player currentPlayer, Player nextPlayer);
    
}