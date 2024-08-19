public class TaskStorage extends MendelAction{
    private Task[] messages;
    int counter;

    public TaskStorage() {
        this.messages = new Task[100];
        this.counter = 0;
    }

    public void add(String message) {
        Task element = new Task(message);
        this.messages[this.counter] = element;
        this.counter++;
        String outputMessage = "added: " + element.toString();
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void speak() {

        System.out.println(new FormatText(this.toString()).wrapLines());
    }

    @Override
    public String toString() {
        String finalMessage = "";
        if (counter > 0) {
            finalMessage = "1. " + this.messages[0].toString();
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += "\n" + increment + ". " + this.messages[i].toString();

        }
        return finalMessage;
    }
}
