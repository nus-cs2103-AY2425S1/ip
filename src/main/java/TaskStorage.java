public class TaskStorage extends MendelAction{
    private String[] messages;
    int counter;

    public TaskStorage() {
        this.messages = new String[100];
        this.counter = 0;
    }

    public void add(String message) {
        this.messages[this.counter] = message;
        this.counter++;
        String outputMessage = "added: " + message;
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void speak() {

        System.out.println(new FormatText(this.toString()).wrapLines());
    }

    @Override
    public String toString() {
        String finalMessage = "";
        if (counter > 0) {
            finalMessage = "1. " + this.messages[0];
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += "\n" + increment + ". " + this.messages[i];

        }
        return finalMessage;
    }
}
