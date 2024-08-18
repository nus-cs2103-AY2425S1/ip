import java.util.ArrayList;

public class ChatBot {
    ArrayList<String> list;

    public ChatBot() {
        this.list = new ArrayList<>();
    }

    private String formatMessage(String msg) {
        return "___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n";
    }

    public String greet() {
        String greetMsg = " Hello! I'm Bob\n"
                + " What can I do for you?";
        return this.formatMessage(greetMsg);
    }

    public String exit() {
        String exitMsg = " Bye. Hope to see you again soon!";
        return this.formatMessage(exitMsg);
    }

    public String add(String item) {
        this.list.add(item);
        String addMsg = " added: " + item;
        return this.formatMessage(addMsg);
    }

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