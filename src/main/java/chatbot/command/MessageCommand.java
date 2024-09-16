package chatbot.command;

public class MessageCommand extends Command {
    private String message;

    public MessageCommand(String message) {
        this.message = message;
    }

    @Override
    public String run() {
        return this.message;
    }
}
