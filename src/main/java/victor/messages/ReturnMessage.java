package victor.messages;

public class ReturnMessage {
    private String[] message;
    private boolean isEmpty;

    public ReturnMessage(String... messages) {
        this.message = messages;
        if (messages.length != 0) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
    }

    public String[] getMessages() {
        return this.message;
    }

    public boolean checkIsEmpty() {
        return this.isEmpty;
    }
}
