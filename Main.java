import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class Main extends ListenerAdapter {

    String[] links = { "https://www.youtube.com/watch?v=U13xOvDa19U&list=PL0C6F06A475045244&index=1", "https://www.youtube.com/watch?v=yO3MwSbsju8&list=PL0C6F06A475045244&index=2", "https://www.youtube.com/watch?v=4nVJ4WhYVWA&list=PL0C6F06A475045244&index=3", "https://www.youtube.com/watch?v=__3McxusO7Y&list=PL0C6F06A475045244&index=4", "https://www.youtube.com/watch?v=4bMM7tGV9MI&list=PL0C6F06A475045244&index=5", "https://www.youtube.com/watch?v=CdxIuzxA7Do&list=PL0C6F06A475045244&index=6", "https://www.youtube.com/watch?v=2k7wq77djvo&list=PL0C6F06A475045244&index=7", "https://www.youtube.com/watch?v=Ka3GznTXur8&list=PL0C6F06A475045244&index=8", "https://www.youtube.com/watch?v=FtsxfquYHf0&list=PL0C6F06A475045244&index=9", "https://www.youtube.com/watch?v=yZYC1v6QIYI&list=PL0C6F06A475045244&index=10", "https://www.youtube.com/watch?v=XL0YWTPDMUI&list=PL0C6F06A475045244&index=11", "https://www.youtube.com/watch?v=Z6PntCiFnmg&list=PL0C6F06A475045244&index=12", "https://www.youtube.com/watch?v=8nf53yhp1TQ&list=PL0C6F06A475045244&index=13", "https://www.youtube.com/watch?v=t96C7TqDU-c&list=PL0C6F06A475045244&index=14", "https://www.youtube.com/watch?v=MkWrrY2fEEY&list=PL0C6F06A475045244&index=15", "https://www.youtube.com/watch?v=pJx54IOAbEY&list=PL0C6F06A475045244&index=16", "https://www.youtube.com/watch?v=tRvjGkItck0&list=PL0C6F06A475045244&index=17", "https://www.youtube.com/watch?v=ybXSET6TP-A&list=PL0C6F06A475045244&index=18", "https://www.youtube.com/watch?v=LzKMCVTw53E&list=PL0C6F06A475045244&index=19", "https://www.youtube.com/watch?v=DouZ5VZQVuw&list=PL0C6F06A475045244&index=20",
            "https://www.youtube.com/watch?v=yotSxqa7WAA&list=PL0C6F06A475045244&index=21", "https://www.youtube.com/watch?v=DksjpsAe3vk&list=PL0C6F06A475045244&index=22", "https://www.youtube.com/watch?v=NV3CVuhJmOY&list=PL0C6F06A475045244&index=23", "https://www.youtube.com/watch?v=wdqV9Im-65E&list=PL0C6F06A475045244&index=24", "https://www.youtube.com/watch?v=YtnzZV5w9Ts&list=PL0C6F06A475045244&index=25", "https://www.youtube.com/watch?v=8vljGu__oDU&list=PL0C6F06A475045244&index=26", "https://www.youtube.com/watch?v=ZAJnWGkLdJo&list=PL0C6F06A475045244&index=27", "https://www.youtube.com/watch?v=9jumopYHTTw&list=PL0C6F06A475045244&index=28", "https://www.youtube.com/watch?v=JvYRm1mTIIY&list=PL0C6F06A475045244&index=29", "https://www.youtube.com/watch?v=F248tAKwO8w&list=PL0C6F06A475045244&index=30", "https://www.youtube.com/watch?v=EpsJb9ULotU&list=PL0C6F06A475045244&index=31", "https://www.youtube.com/watch?v=sAlDqw7eOUw&list=PL0C6F06A475045244&index=32", "https://www.youtube.com/watch?v=gKIqRQsxWMg&list=PL0C6F06A475045244&index=33", "https://www.youtube.com/watch?v=J0GCOLv3ZNI&list=PL0C6F06A475045244&index=34", "https://www.youtube.com/watch?v=ucdEIMKTD7U&list=PL0C6F06A475045244&index=35", "https://www.youtube.com/watch?v=iGFr06O3hwc&list=PL0C6F06A475045244&index=36", "https://www.youtube.com/watch?v=3yJh5XNfN2k&list=PL0C6F06A475045244&index=37", "https://www.youtube.com/watch?v=QJ2HVr7xlfY&list=PL0C6F06A475045244&index=38",
            "https://www.youtube.com/watch?v=Et2MpcJ5LpA&list=PL0C6F06A475045244&index=39", "https://www.youtube.com/watch?v=PDS8cR6ncDQ&list=PL0C6F06A475045244&index=40", "https://www.youtube.com/watch?v=bKwawLY42sI&list=PL0C6F06A475045244&index=41", "https://www.youtube.com/watch?v=4aa-d3_E86U&list=PL0C6F06A475045244&index=42", "https://www.youtube.com/watch?v=wNraXglgUvw&list=PL0C6F06A475045244&index=43", "https://www.youtube.com/watch?v=Au16TTI7nQc&list=PL0C6F06A475045244&index=44", "https://www.youtube.com/watch?v=ev4ESucF53U&list=PL0C6F06A475045244&index=45", "https://www.youtube.com/watch?v=8tVghm88r2Y&list=PL0C6F06A475045244&index=46", "https://www.youtube.com/watch?v=9P3Gu-ksON0&list=PL0C6F06A475045244&index=47", "https://www.youtube.com/watch?v=nHA_NArF9-M&list=PL0C6F06A475045244&index=48", "https://www.youtube.com/watch?v=ThU9BOWcmjM&list=PL0C6F06A475045244&index=49", "https://www.youtube.com/watch?v=7QFtgSwTveI&list=PL0C6F06A475045244&index=50", "https://www.youtube.com/watch?v=DUBn7bkKcuI&list=PL0C6F06A475045244&index=51", "https://www.youtube.com/watch?v=Ac1X16K5XlU&list=PL0C6F06A475045244&index=52", "https://www.youtube.com/watch?v=yu4fOdK-KDs&list=PL0C6F06A475045244&index=53", "https://www.youtube.com/watch?v=lhs7rVhOg3o&list=PL0C6F06A475045244&index=54", "https://www.youtube.com/watch?v=Nnq98a0UJPs&list=PL0C6F06A475045244&index=55", "https://www.youtube.com/watch?v=dbbAj3EDJM8&list=PL0C6F06A475045244&index=56",
            "https://www.youtube.com/watch?v=u3SWNRDFrt0&list=PL0C6F06A475045244&index=57", "https://www.youtube.com/watch?v=tWJ9YaTmoyg&list=PL0C6F06A475045244&index=58", "https://www.youtube.com/watch?v=XXdfKUbS-0c&list=PL0C6F06A475045244&index=59", "https://www.youtube.com/watch?v=Z6jtLAnqiiA&list=PL0C6F06A475045244&index=60", "https://www.youtube.com/watch?v=rdVG2jYDlL8&list=PL0C6F06A475045244&index=61", "https://www.youtube.com/watch?v=9wW9f3k7LTw&list=PL0C6F06A475045244&index=62", "https://www.youtube.com/watch?v=N8sNMJKlZo8&list=PL0C6F06A475045244&index=63", "https://www.youtube.com/watch?v=CfihF5BD1Rs&list=PL0C6F06A475045244&index=64", "https://www.youtube.com/watch?v=TifOGP97chU&list=PL0C6F06A475045244&index=65", "https://www.youtube.com/watch?v=Fc6gdAZdtRY&list=PL0C6F06A475045244&index=66", "https://www.youtube.com/watch?v=BKQPbdcMcYs&list=PL0C6F06A475045244&index=67", "https://www.youtube.com/watch?v=KZLRDfaJvNM&list=PL0C6F06A475045244&index=68", "https://www.youtube.com/watch?v=jf6xipNG2Bc&list=PL0C6F06A475045244&index=69", "https://www.youtube.com/watch?v=kspPE9E1yGM&list=PL0C6F06A475045244&index=70", "https://www.youtube.com/watch?v=xzqyZFHOcE0&list=PL0C6F06A475045244&index=71", "https://www.youtube.com/watch?v=kVJCyOYAiSo&list=PL0C6F06A475045244&index=72", "https://www.youtube.com/watch?v=ITBsuHp0HOo&list=PL0C6F06A475045244&index=73", "https://www.youtube.com/watch?v=3CE3ii0N2HM&list=PL0C6F06A475045244&index=74"
    };

    Scanner scan = null;
    PrintStream output = null;
    Message mostRecentSelfMsg = null;
    Message lastMsg = null;
    boolean pollActive = false;
    ArrayList<PollOption> pollOptions = new ArrayList<PollOption>();
    int pollTime = 30;
    String pollQuestion = "";

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "<token>";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        lastMsg = event.getMessage();
        if (event.getAuthor().isBot()) {
            if(event.getAuthor().getName().equalsIgnoreCase("drewbot")) {
                if(!event.getMessage().getEmbeds().isEmpty() && event.getMessage().getEmbeds().get(0).getTitle().equals("Poll")) {
                    pollTime--;
                    if(pollTime < 0){
                        return;
                    }
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Poll");
                    eb.setDescription(pollQuestion);
                    if(pollTime != 0){
                        eb.addField("Time left:", String.valueOf(pollTime), true);
                    }
                    else{
                        eb.addField("Time left:", "Poll has ended", true);
                        pollActive = false;

                    }
                    for (int i = 0; i < pollOptions.size(); i++) {
                        MessageEmbed.Field f = new MessageEmbed.Field(pollOptions.get(i).getName(), String.valueOf(pollOptions.get(i).getVotes()), true);
                        eb.addField(f);
                    }

                    MessageEmbed msgE = eb.build();
                    event.getChannel().editMessageById(event.getMessageIdLong(), msgE).queueAfter(1, TimeUnit.SECONDS);
                    return;
                }
            }
            return;
        }
    
        if(pollActive) {
            for (int i = 0; i < pollOptions.size(); i++) {
                if (pollOptions.get(i).getName().equals(event.getMessage().getContentRaw())) {
                    pollOptions.get(i).addVote();
                    return;
                }
            }
        }

        String message = event.getMessage().getContentRaw().toLowerCase();
        MessageChannel channel = event.getChannel();

        if (event.getMessage().getContentRaw().length() >= 5 && event.getMessage().getContentRaw().substring(0, 5).equalsIgnoreCase("$roll")) {
            String str = event.getMessage().getContentRaw();
            Random r = new Random();
            if(str.length() == 5){
                channel.sendMessage(event.getAuthor().getName() + ": " + String.valueOf(r.nextInt(20) + 1)).queue();
            }
            else{
                if(validateNumber(str.substring(6, str.length())) && str.substring(6, str.length()).length() <= 6){
                    int rollAmount = Integer.parseInt(str.substring(6, str.length()));
                    if(rollAmount < 1 || rollAmount > 100000){
                        channel.sendMessage("Roll amount must be between 1 and 100000").queue();
                    }
                    else {
                        channel.sendMessage(event.getAuthor().getName() + ": " + String.valueOf(r.nextInt(rollAmount) + 1)).queue();
                    }
                }
                else{
                        channel.sendMessage("Roll amount must be between 1 and 100000").queue();
                }
            }
        }

        else if (event.getMessage().getContentRaw().equalsIgnoreCase("$join")) {
            if (!event.getGuild().getSelfMember().hasPermission(Permission.VOICE_CONNECT)) {
                // The bot does not have permission to join any voice channel. Don't forget the .queue()!
                channel.sendMessage("I do not have permissions to join a voice channel!").queue();
                return;
            }
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            if (connectedChannel == null) {
                channel.sendMessage("You are not connected to a voice channel!").queue();
                return;
            }
            AudioManager audioManager = event.getGuild().getAudioManager();
            // When somebody really needs to chill.
            if (audioManager.isAttemptingToConnect()) {
                channel.sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
                return;
            }
            audioManager.openAudioConnection(connectedChannel);
        }
        else if (message.equals("$leave")) { // Checks if the command is !leave.
            VoiceChannel connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();
            if (connectedChannel == null) {
                // Get slightly fed up at the user.
                channel.sendMessage("I am not connected to a voice channel!").queue();
                return;
            }
            event.getGuild().getAudioManager().closeAudioConnection();
        }

        else if (event.getMessage().getContentRaw().equalsIgnoreCase("$mashup")) {
            Random r = new Random();
            channel.sendMessage(links[r.nextInt((links.length))]).queue();
            return;
        }

        else if (event.getMessage().getContentRaw().equalsIgnoreCase("$leaderboard")) {
            try {
                ScoreKeeper scores = new ScoreKeeper();
                scores.printLeaderboard(event);
            }
            catch(IOException e){
                System.out.println("Scores failed");
            }
            return;
        }

        else if (event.getMessage().getContentRaw().equalsIgnoreCase("$userinfo")) {
            EmbedBuilder eb = new EmbedBuilder();

                    eb.setThumbnail(event.getAuthor().getAvatarUrl());
                    MessageEmbed.Field accountDate = new MessageEmbed.Field("Account creation date: ",
                            event.getAuthor().getCreationTime().getMonth().toString() + " "
                                    + event.getAuthor().getCreationTime().getDayOfMonth() + ", "
                                    + event.getAuthor().getCreationTime().getYear(), false);
                    MessageEmbed.Field serverDate = new MessageEmbed.Field("Server join date: ",
                            event.getMember().getJoinDate().getMonth().toString() + " "
                                    + event.getMember().getJoinDate().getDayOfMonth() + ", "
                                    + event.getMember().getJoinDate().getYear(), false);
                    eb.setColor(Color.MAGENTA);
                    eb.addField(accountDate);
                    eb.addField(serverDate);
                    eb.setDescription(event.getAuthor().getName() + "'s user info:");

            MessageEmbed eMessage = eb.build();
            event.getChannel().sendMessage(eMessage).queue();
            return;
        }

        else if (event.getMessage().getContentRaw().length() >= 5 && event.getMessage().getContentRaw().substring(0, 5).equalsIgnoreCase("$poll")) {

            if(!pollActive){
                String pollArgs = event.getMessage().getContentRaw().substring(5, event.getMessage().getContentRaw().length());
                StringTokenizer st = new StringTokenizer(pollArgs);
                String token;
                boolean error = false;
                int numOptions = 0;
                EmbedBuilder eb = new EmbedBuilder();


                while(st.hasMoreTokens()) {
                    token = st.nextToken();
                    if(error){
                        channel.sendMessage("Error in syntax. Command usage: =question here -response 1 -response 2 +time (in seconds)").queue();
                        error = false;
                        return;
                    }
                    else if(token.substring(0,1).equals("=")){
                        if(token.length() > 1){
                            pollQuestion = token.substring(1, token.length());
                        }
                        else{
                            error = true;
                        }
                    }
                    else if(token.substring(0,1).equals("-")){
                        if(token.length() > 1){
                            PollOption po = new PollOption(token.substring(1, token.length()));
                            pollOptions.add(po);
                            numOptions++;
                        }
                        else{
                            error = true;
                        }
                    }
                    else if(token.length() > 1 && token.substring(0,1).equals("+")){
                        if(validateNumber(token.substring(1, token.length()))){
                            if(pollTime == 30){
                                pollTime = Integer.parseInt(token.substring(1, token.length()));
                            }
                            else{
                                error = true;
                            }

                        }
                        else{
                            error = true;
                        }
                    }
                    else{
                        if(numOptions > 0) {
                            pollOptions.get(numOptions - 1).setName(pollOptions.get(numOptions - 1).getName() + " " + token);
                        }
                        else if(pollQuestion != "") {
                            pollQuestion += " " + token;
                        }
                        else{
                            error = true;
                        }
                    }

                }
                if(error){
                    channel.sendMessage("Error in syntax. Command usage: =question here -response 1 -response 2 +time (in seconds)").queue();
                    error = false;
                    return;
                }
                pollActive = true;
                eb.setTitle("Poll");
                eb.setDescription(pollQuestion);
                eb.addField("Time left:", String.valueOf(pollTime), true);
                for(int i = 0; i < numOptions; i++){
                    MessageEmbed.Field f = new MessageEmbed.Field(pollOptions.get(i).getName(), String.valueOf(pollOptions.get(i).getVotes()), true);
                    eb.addField(f);
                }

                MessageEmbed msgE = eb.build();
                channel.sendMessage(msgE).queue();
            }


            return;

        }

        else if (event.getMessage().getContentRaw().equalsIgnoreCase("$help")) {
            String helpMsg = "dongbot command list: ```css\n" +
                    "$help  [I wonder what this does :thinking:]\n" +
                    "$userinfo  [Displays user's account creation and server join date]\n" +
                    "$leaderboard  [Displays chat statistics]\n" +
                    "$mashup  [Generates a random music mashup]\n" +
                    "$join  [Joins the voice channel]\n" +
                    "$leave  [Leaves the voice channel]\n" +
                    "$poll =[Question here] -[Response 1] -[Response 2 or more] +[Time limit]\n" +
                    "$roll [Rolls a 20 sided die. Can use $roll [number] for specific amount]" +
                    "```";
            channel.sendMessage(helpMsg).queue();
            return;
        }

    }

    public void onMessageUpdate(MessageUpdateEvent event) {

        if (event.getAuthor().isBot()) {
            if(event.getAuthor().getName().equalsIgnoreCase("dongbot")) {
                if(event.getMessage().getEmbeds().get(0).getTitle().equals("Poll")) {
                    pollTime--;
                    if(pollTime < 0){
                        return;
                    }
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Poll");
                    eb.setDescription(pollQuestion);

                    if(pollTime != 0){
                        eb.addField("Time left:", String.valueOf(pollTime), true);
                    }
                    else{
                        eb.addField("Time left:", "Poll has ended", true);
                        pollActive = false;
                    }

                    for (int i = 0; i < pollOptions.size(); i++) {
                        MessageEmbed.Field f = new MessageEmbed.Field(pollOptions.get(i).getName(), String.valueOf(pollOptions.get(i).getVotes()), true);
                        eb.addField(f);
                    }


                    MessageEmbed msgE = eb.build();
                    event.getChannel().editMessageById(event.getMessageIdLong(), msgE).queueAfter(1, TimeUnit.SECONDS);

                    return;
                }
            }
            return;
        }
    }

    public void onMessageDelete(MessageDeleteEvent event) {
        if(event.getTextChannel().getName().equalsIgnoreCase("bot-niggas")) {
            event.getTextChannel().sendMessage("<:ResidentSleeper:270325580476579842>").queue();
        }
    }

    /*
    public void updatePoll(MessageReceivedEvent event, long id){

        for(int x = 1; x < pollTime + 1; x++) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Poll");
            eb.setDescription(pollQuestion);
            eb.addField("Time left:", String.valueOf(pollTime - x), true);
            for (int i = 0; i < pollOptions.size(); i++) {
                MessageEmbed.Field f = new MessageEmbed.Field(pollOptions.get(i).getName(), String.valueOf(pollOptions.get(i).getVotes()), true);
                eb.addField(f);
            }


            MessageEmbed msgE = eb.build();
            event.getChannel().editMessageById(id, msgE).queueAfter(x, TimeUnit.SECONDS);
        }
        event.getChannel().editMessageById(id, "Finishing in 4 seconds").queueAfter(1, TimeUnit.SECONDS);;
        event.getChannel().editMessageById(id, "Finishing in 3 seconds").queueAfter(2, TimeUnit.SECONDS);;
        event.getChannel().editMessageById(id, "Finishing in 2 seconds").queueAfter(3, TimeUnit.SECONDS);;
        event.getChannel().editMessageById(id, "Finishing in 1 seconds").queueAfter(4, TimeUnit.SECONDS);;
        event.getChannel().editMessageById(id, "Finished").queueAfter(5, TimeUnit.SECONDS);;

        pollActive = false;
    }
    */

    public boolean validateNumber(String str){
        for(char c : str.toCharArray()){
            if(c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }
}
