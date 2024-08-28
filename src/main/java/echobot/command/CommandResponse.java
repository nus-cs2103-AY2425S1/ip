package echobot.command;

public class CommandResponse {
    private boolean isExit = false;
    private String response;

    public CommandResponse(boolean isExitCommand) {
        this.isExit = isExitCommand;
    }

    public CommandResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

    public boolean isExitCommand() {
        return this.isExit;
    }
}
