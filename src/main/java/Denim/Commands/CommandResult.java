package denim.commands;

public class CommandResult {
    private final String reply;

    public CommandResult(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return this.reply;
    }

}
