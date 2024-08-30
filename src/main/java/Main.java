import BobChatBot.Bob;

public class Main {
    public static void main(String[] args) {

        Bob myBot = new Bob("./userdata.txt");
        myBot.startChatBot();

    }
}
