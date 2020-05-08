package perso.type;

public class TypeAdd2 implements TypeOfCards{

    protected int numberOfCardsToAdd;

    public TypeAdd2(){
        this.numberOfCardsToAdd = 2;
    }

    /**
     * @return the number
     */
    public int getNumberOfCardsToAdd() {
        return numberOfCardsToAdd;
    }
}