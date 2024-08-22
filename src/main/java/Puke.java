public class Puke {
    private MessageBuilder messageBuilder;
    public Puke() {
        this.messageBuilder = new MessageBuilder();
    }
    public static void main(String[] args) {
        Puke chatBot = new Puke();
        chatBot.start();
    }

    private void start() {
        messageBuilder.sendGreetingMessage();
        terminate();
    }

    private void terminate() {
        messageBuilder.sendGoodbyeMessage();
    }
}
