import java.util.ArrayList;

public class ChatBot {
    /**
     * The list of items added.
     */
    ArrayList<String> list;

    public ChatBot() {
        this.list = new ArrayList<>();
    }

    /**
     * Convert a message to the standard format.
     * @param msg the message to convert
     * @return the formatted message
     */
    private String formatMessage(String msg) {
        return "___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n";
    }

    /**
     * Get the greeting message.
     * @return the greeting message
     */
    public String greet() {
        String greetMsg = " Hello! I'm Bob\n"
                + " What can I do for you?";
        return this.formatMessage(greetMsg);
    }

    /**
     * Get the exit message.
     * @return the exit message.
     */
    public String exit() {
        String exitMsg = " Bye. Hope to see you again soon!";
        return this.formatMessage(exitMsg);
    }

    /**
     * Add an item to the list and get the response message.
     * @param item the item to add
     * @return the response message
     */
    public String add(String item) {
        this.list.add(item);
        String addMsg = " added: " + item;
        return this.formatMessage(addMsg);
    }

    /**
     * Get the list of items added.
     * @return the list message
     */
    public String list() {
        StringBuilder listMsg = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            listMsg
                    .append("\n ")
                    .append(i + 1)
                    .append(". ")
                    .append(list.get(i));
        }
        return this.formatMessage(listMsg.toString());
    }
}