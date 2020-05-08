package perso.type;

public class TypeSimple implements TypeOfCards{

    private int number;

    public TypeSimple(int number){
        this.number = number;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
}