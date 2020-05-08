package perso.color;
import java.util.ArrayList;
import java.util.List;

public enum Colors { 
    
    RED, YELLOW, GREEN, BLUE, NONE;

    public List<Colors> possibleColors(){
        List<Colors> possibleColors = new ArrayList<Colors>();
        possibleColors.add(Colors.RED);
        possibleColors.add(Colors.YELLOW);
        possibleColors.add(Colors.GREEN);
        possibleColors.add(Colors.BLUE);
        return possibleColors;
    }

}