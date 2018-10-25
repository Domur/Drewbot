import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreKeeper implements Serializable {

    public void addScore(MessageReceivedEvent e) throws IOException{
        int playerCount = 0;
        ArrayList<Player> scores = new ArrayList<Player>();
        Scanner scan = new Scanner(new File("scores.txt"));
        while(scan.hasNext()){
            playerCount++;
            String playerName = scan.next();
            int playerScore = Integer.parseInt(scan.next());
            Player player = new Player(playerName, playerScore);
            scores.add(player);
        }
        if(playerCount == 0) {
            String playerName = e.getAuthor().getName();
            int playerScore = 1;
            Player player = new Player(playerName, playerScore);
            scores.add(player);
        }
        else{
            boolean found = false;
            int i = 0;
            String discordName = e.getAuthor().getName();
            while(i < scores.size() && !found){
                if(scores.get(i).getName().equals(discordName)){
                    found = true;
                    scores.get(i).increaseScore();
                    if(i != 0 && scores.get(i).getScore() > scores.get(i-1).getScore()){
                        Player temp = scores.get(i-1);
                        scores.remove(i-1);
                        scores.add(i, temp);
                    }
                }
                i++;
            }
            if(!found){
                int playerScore = 1;
                Player player = new Player(discordName, playerScore);
                scores.add(player);
            }
        }


        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scores.txt")));
        for(int i = 0; i < scores.size(); i++){
            out.println(scores.get(i).getName() + " " + scores.get(i).getScore());
        }
        out.close();
    }
    
    public void printLeaderboard(MessageReceivedEvent e) throws IOException{
        ArrayList<Player> scores = new ArrayList<Player>();
        Scanner scan = new Scanner(new File("scores.txt"));
        while(scan.hasNext()){
            String playerName = scan.next();
            int playerScore = Integer.parseInt(scan.next());
            Player player = new Player(playerName, playerScore);
            scores.add(player);
        }
        EmbedBuilder eb = new EmbedBuilder();
        eb.setThumbnail(e.getGuild().getIconUrl());
        eb.setColor(Color.RED);
        eb.setDescription("The Flying Fortress Leaderboard:");

        if(scores.size() <= 10){
            for(int i = 0; i < scores.size(); i++){
                MessageEmbed.Field f = new MessageEmbed.Field((i + 1) + ". " + scores.get(i).getName(), Integer.toString(scores.get(i).getScore()), true);
                eb.addField(f);
            }
        }
        else{
            for(int i = 0; i < 10; i++){
                MessageEmbed.Field f = new MessageEmbed.Field((i + 1) + ". " + scores.get(i).getName(), Integer.toString(scores.get(i).getScore()), true);
                eb.addField(f);
            }
        }
        MessageEmbed eMessage = eb.build();
        e.getChannel().sendMessage(eMessage).queue();
    }
}
