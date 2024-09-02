package wenjigglybot;

public class WenJigglyBotException extends Exception {
    private String s;

    public WenJigglyBotException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}