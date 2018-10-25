public class Player {

    private String playerName = "";
    private int playerScore = -1;

    public Player(){
        playerName = "undefined";
        playerScore = -1;
    }

    public Player(String name, int score){
        playerName = name;
        playerScore = score;
    }

    public String getName(){
        return playerName;
    }

    public int getScore(){
        return playerScore;
    }

    public void increaseScore(){
        playerScore++;
    }
}
