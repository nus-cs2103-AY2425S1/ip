import java.util.ArrayList;

class PoChat {
    private final ArrayList<String> listTextsEntered = new ArrayList<>();

    public void sayHello() {
        String introMessage = "Hello! I'm PoChat, the chatbot in your pocket.\nWhat can I do for you?";
        System.out.println(introMessage);
    }

    public void sayGoodbye() {
        String introMessage = "Bye. Hope to see you again soon!";
        System.out.println(introMessage);
    }

    public void addToListAndReply(String textInput) {
        this.listTextsEntered.add(textInput);
        System.out.println("added: " + textInput);
    }

    public void replyWithListOfTextsEntered() {
        for (int i = 0; i < this.listTextsEntered.size(); i++) {
            System.out.println((i + 1) + ". " + this.listTextsEntered.get(i));
        }
    }
}