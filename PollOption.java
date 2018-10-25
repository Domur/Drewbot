public class PollOption {

    private String name = "Unknown";
    private int votes = 0;

    public PollOption(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public void addVote(){
        votes++;
    }

    public int getVotes(){
        return votes;
    }
}
