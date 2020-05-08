package perso.player;
import perso.card.*;
import perso.board.*;
import java.util.*;
import perso.color.*;
import perso.type.*;

public class Player {

    private List<Card<?>> main;
    private String name;
    private Board board;
    private boolean canPlay;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
        this.main = new ArrayList<Card<?>>();
        this.canPlay = true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the main
     */
    public List<Card<?>> getMain() {
        return main;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    public boolean getCanPlay(){
        return canPlay;
    }

    public void addCard(Card<?> cardToAdd){
        this.main.add(cardToAdd);
    }

    public void removeCard(Card<?> cardremove){
        this.main.remove(cardremove);
    }

    public void cantPlay(){
        this.canPlay = false;
    }

    public void canPlay(){
        this.canPlay = true;
    }
    public boolean playerCantPlayCauseHasNoCard(){
        return this.possibleCards().size() == 0;
    }

    public boolean playerCantPlayCauseStopCard(){
        return ! this.getCanPlay();
    }

    public boolean won(){
        return this.getMain().size() == 0;
    }

    public void drawCard(){ //picoher
        Card<?> cardToDraw = this.board.takeFirstCardInDraw();
        this.addCard(cardToDraw);
        System.out.println("you draw " + cardToDraw.toString());
        this.getBoard().removeFirstCardToDraw();
    }

    public void drawCardWHileCardNoPossible(){
        Card<?> cardToDraw = this.board.takeFirstCardInDraw();
        System.out.println("You draw " + cardToDraw.toString());
        this.addCard(cardToDraw);
        this.getBoard().removeFirstCardToDraw();
        if ( ! cardToDraw.isPossible(this.getBoard().takeFirstCardInStack())){
            System.out.println("You don't have card to play, draw again");
            drawCardWHileCardNoPossible();
        }
    }

    public void dropCard(Card<?> cardToDrop){ //deposer
        this.removeCard(cardToDrop);
        this.getBoard().addCardToStack(cardToDrop);
        System.out.println("\nYou dorp " + cardToDrop.toString());
        this.alwaysCardsInTheDraw();
    }

    public void alwaysCardsInTheDraw(){
        if( this.getBoard().getStack().size() >= 2){
            Card<?> cardToGibeBackInDraw = this.getBoard().getStack().get(1); 
            this.getBoard().removeCardToStack(cardToGibeBackInDraw); //remove in the stack, cause the last card dosent serve
            this.getBoard().addCardToDraw(cardToGibeBackInDraw); //add at the end of the draw, to draw it later
        }
    }

    public List<Card<?>> possibleCards(){
        Card<?> firstCard = this.board.takeFirstCardInStack();
        List<Card<?>> possibleCards = new ArrayList<Card<?>>();
        for (Card<?> card : main){
            if (card.isPossible(firstCard)){
                possibleCards.add(card);
            }
        }
        return possibleCards; 
    }

    public void displayCards(){
        System.out.println(this.getName() + " has : ");
        System.out.print(this.board.listToString(this.getMain()));
        System.out.println();
    }

    public void displayPossibleCards(){
        System.out.println(this.getName() + " can use : ");
        System.out.print(this.board.listToString(this.possibleCards()));
        System.out.println();
    }

    public String toString(){
        return this.getName();
    }
}