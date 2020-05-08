package perso.board;
import perso.player.*;
import perso.card.*;
import java.util.*;
import perso.color.*;
import perso.type.*;

@SuppressWarnings("ALL")
public class Board {

    private List<Card<?>> stack; //pile
    private List<Card<?>> draw; //picohe
    private List<Player> players;

    public Board(){
        this.stack = new ArrayList<Card<?>>();
        this.draw = new ArrayList<Card<?>>();
        this.players = new ArrayList<Player>();
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return the draw
     */
    public List<Card<?>> getDraw() {
        return draw;
    }

    /**
     * @return the stack
     */
    public List<Card<?>> getStack() {
        return stack;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addCardToStack(Card<?> cardToAdd){
        this.stack.add(0, cardToAdd);
    }

    public void addCardToDraw(Card<?> cardToAdd){
        this.draw.add(cardToAdd);
    }

    public void removeFirstCardToDraw(){
        this.draw.remove(0);
    }

    public void removeCardToStack(Card<?> cardToRemove){
        this.stack.remove(cardToRemove);
    }

    public Card<?> takeFirstCardInStack(){
        return this.stack.get(0);
    }

    public Card<?> takeFirstCardInDraw(){
        return this.draw.get(0);
    }

    public void addPlayer(Player playerToAdd){
        this.players.add(playerToAdd);
    }

    /**
     * A generic methods that allow us to represent list of any types.
     * @param l the list we want to represent.
     * @return the string representation of this list. 
     */
    public String listToString(List<?> l) {
    	int i = 1;
    	String res = "";
    	Iterator <?> iterator = l.iterator();
    	while(iterator.hasNext()) {
    		res += i + " : " + iterator.next().toString() + "\n";
    		i++;
        } 
        return res;
    }

    public void displayEgal(int num){
        String res = "";
        for(int i = 0; i < num; i++){
            res += "=";
        }
        System.out.println(res);
    }



    //chooser

    public int intChooser(int limiteA, int limiteB){
        System.out.println("Choose a number between " + limiteA + " and " + limiteB + " : ");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
    	while( ! (choice >= limiteA && choice <= limiteB) ){
    		System.out.println("You choose a wrong number, try again :");
    		choice = sc.nextInt();
    	}
        return choice-1;
    }

    public String stringChooser(){
        System.out.println("Choose a name : ");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        return name;
    }



    //players

    public void cratePlayers(int nbOfPlayers){
        System.out.println("\n===========================\n");
        for (int i = 0; i <= nbOfPlayers; i++){
            String name = this.stringChooser();
            Player playerCreate = createPlayer(name);
            this.addPlayer(playerCreate);
            System.out.println();
        }
    }

    public Player createPlayer(String name){
        return new Player(name, this);
    }

    public void distributeCardsToPlayers(){
        for( int i = 0; i < 7; i++){
            for ( Player player : players){
                Card<?> firstCard = this.takeFirstCardInDraw();
                player.addCard(firstCard);
                this.removeFirstCardToDraw();
            }
        }
    }

    public void displayCardsForAllPlayers(){
        System.out.println("===========================\n");
        for(Player player : players){
            player.displayCards();
        }
    }

    public void changeOrderPlayers(Player currentPlayer){
        int indexOfcurrentPlayer = this.getPlayers().indexOf(currentPlayer), indexOfLastPlayer = this.getPlayers().size() - 1, tmp = indexOfcurrentPlayer;
        List<Player> newPlayers = new ArrayList<Player>();
        while( 0 < indexOfcurrentPlayer) { //begin per the left player of the first player, ending per 0 (right to left)
            indexOfcurrentPlayer--; //decremente firstly to not take the forst player twice
            Player playerToAdd = this.getPlayers().get(indexOfcurrentPlayer); 
            newPlayers.add(playerToAdd);
        }
        while (tmp < indexOfLastPlayer){ //begin par the right player of the first player, ending par size (right to left)
            Player playerToAdd = this.getPlayers().get(indexOfLastPlayer); 
            newPlayers.add(playerToAdd); 
            indexOfLastPlayer--;
        }
        newPlayers.add(currentPlayer); //put at end cause he has already played
        this.setPlayers(newPlayers);
    }

    public Player takeNextPlayer(Player currentPlayer){
        int index = this.getPlayers().indexOf(currentPlayer), size = this.getPlayers().size();
        if (index == size - 1){
            return this.getPlayers().get(0);
        }
        else {
            return this.getPlayers().get(index + 1);
        }
    }

    public Player takePrevPlayer(Player currentPlayer){
        int index = this.getPlayers().indexOf(currentPlayer), size = this.getPlayers().size();
        if (index == 0){
            return this.getPlayers().get(size - 1);
        }
        else {
            return this.getPlayers().get(index - 1);
        }
    }

    public Player takeFirstPlayer(){
        int nbOfPlayers = this.players.size(), rand = randomInt(nbOfPlayers);
        Player firstPlayerToPlay = this.players.get(rand);
        System.out.println();
        this.displayEgal(firstPlayerToPlay.getName().length()+1 + " choose first card : ".length());
        System.out.println(" " + firstPlayerToPlay.getName() + " choose first card : ");
        this.displayEgal(firstPlayerToPlay.getName().length()+1 + " choose first card : ".length());
        System.out.println();
        return firstPlayerToPlay;
    }



    //init stack

    public void initStack(){
        Card<?> cardToAddInStack;
        int choice, nbOfPlayers = this.players.size();
        Player firstPlayerToPlay = this.players.get(nbOfPlayers-1);
        firstPlayerToPlay.displayCards();
        choice = this.intChooser(1, firstPlayerToPlay.getMain().size());
        cardToAddInStack = firstPlayerToPlay.getMain().get(choice);
        cardToAddInStack.useCard(this, firstPlayerToPlay, this.takeNextPlayer(firstPlayerToPlay));
    }

    /**
	 * Return a random int between 0 and de max-1
	 * @param max : the max of the random number
	 * @return a random int
	 */
	public int randomInt(int max){
		Random rand = new Random();
		return rand.nextInt(max);
	}



    //init draw

    public void initDraw(){
        this.initSimpleCards();
        this.initAdd2Cards();
        this.initAdd4Cards();
        this.initStopCards();
        this.initReverseCards();
        this.initJockerCards();
        Collections.shuffle(this.draw);
    }

    public void initSimpleCards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            this.addCardToDraw( new SimpleCard( color, new TypeSimple(0) ) ); //only one 0 per color
            for (int i = 0; i < 2; i++){
                for (int j = 1; j <= 9; j++){ //2 numbers per color for 1,2,...8,9
                    this.addCardToDraw( new SimpleCard( color, new TypeSimple(j) ) );
                }
            }
        }
    }

    public void initAdd2Cards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            for (int i = 0; i < 2; i++){
                this.addCardToDraw( new Add2Card( color, new TypeAdd2() ) );
            }
        }
    }

    public void initReverseCards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            for (int i = 0; i < 2; i++){
                this.addCardToDraw( new ReverseCard( color, new TypeWithoutAttribute() ) );
            }
        }
    }

    public void initStopCards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            for (int i = 0; i < 2; i++){
                this.addCardToDraw( new StopCard( color, new TypeWithoutAttribute() ) );
            }
        }
    }

    public void initJockerCards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            this.addCardToDraw( new JockerCard( new TypeWithoutAttribute() ) );
        }
    }

    public void initAdd4Cards(){
        List<Colors> possibleColors = Colors.NONE.possibleColors();
        for (Colors color : possibleColors){
            this.addCardToDraw( new Add4Card( new TypeAdd4() ) );
        }
    }



    //uno

    public void initUno(){
        int nbOfPlayers;
        Player firstPlayerToPlay;
        System.out.println();
        this.displayEgal("| WELCOME TO THE UNO GAME |".length());
        System.out.println("| WELCOME TO THE UNO GAME |");
        this.displayEgal("| WELCOME TO THE UNO GAME |".length());
        //init players
        System.out.println();
        System.out.println("How many players?");
        nbOfPlayers = this.intChooser(2,4);
        this.cratePlayers(nbOfPlayers);
        //init draw
        this.initDraw();
        //distribute cards to players
        this.distributeCardsToPlayers();
        this.displayCardsForAllPlayers();
        //change players' order
        Player firstPlayer = this.takeFirstPlayer();
        this.changeOrderPlayers(firstPlayer); 
        //init stack
        this.initStack();
    }

    public void uno(){
        this.initUno();
        boolean victory = false;
        Player currentPlayer = this.getPlayers().get(0);
        while (! victory) {
            // nextPlayer = this.takeNextPlayer(currentPlayer);
            System.out.println("\n");
            this.displayEgal(currentPlayer.getName().length()+1 + " play : ".length());
            System.out.println(" " + currentPlayer.getName() + " play : ");
            this.displayEgal(currentPlayer.getName().length()+1 + " play : ".length());
            System.out.println("\nStack : " + this.takeFirstCardInStack().toString() + "\n");
            currentPlayer.displayCards();
            if (currentPlayer.playerCantPlayCauseStopCard()){
                System.out.println("You can't play (card stop droped by " + this.takePrevPlayer(currentPlayer).getName() + ")");
            }
            else if (currentPlayer.playerCantPlayCauseHasNoCard()) {
                System.out.println("You don't have card to play, draw");
                currentPlayer.drawCardWHileCardNoPossible();
                System.out.println();
                currentPlayer.displayPossibleCards();
                int choice = this.intChooser(1, currentPlayer.possibleCards().size());
                Card<?> cardToPlay = currentPlayer.possibleCards().get(choice);
                cardToPlay.useCard(this, currentPlayer, this.takeNextPlayer(currentPlayer));
            }
            else {
                currentPlayer.displayPossibleCards();
                int choice = this.intChooser(1, currentPlayer.possibleCards().size());
                Card<?> cardToPlay = currentPlayer.possibleCards().get(choice);
                cardToPlay.useCard(this, currentPlayer, this.takeNextPlayer(currentPlayer));
            }
            if ( currentPlayer.won()){
                victory = true;
            }
            else {
                currentPlayer.canPlay(); //remove action of the stop card
                currentPlayer = this.takeNextPlayer(currentPlayer); 
            }
        }
        System.out.println(currentPlayer.getName() + "won");
    }



    public static void main(String[] args) {
        Board board = new Board();
        board.uno();
    }


}