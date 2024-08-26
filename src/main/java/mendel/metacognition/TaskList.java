package mendel.metacognition;

import mendel.datetime.DateTimeManager;
import mendel.discretetask.Task;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends Command{
    private final List<Task> messages;
    int counter;

    public TaskList() {
        this.messages = new ArrayList<>();
        this.counter = 0;
    }

    public Task getTask(int serial) {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        return messages.get(serial);
    }

    public boolean hasTask(int serial) {
        return serial >= 0 && serial < this.counter;
    }

    public boolean isFirstTask() {
        return this.counter == 1;
    }

    public String marker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsDone();
        return String.format("Nice! I've marked this task as done:\n  %s",
                task);
    }

    public String unMarker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsUnDone();
        return String.format("OK, I've marked this task as not done yet:\n  %s",
                task);
    }

    public String delete(int serial) throws MendelException{
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.remove(serial);
        this.counter--;
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                this.counter);
    }

    public String add(Task element) {
        this.messages.add(element);
        this.counter++;
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                element, this.counter);
    }

    public void silencedAdd(Task element) {
        this.messages.add(element);
        this.counter++;
    }

    public String find(String rawDate) {
        String formattedDate = new DateTimeManager(rawDate).toString();
        String finalMessage = String.format("Here are the tasks with deadlines on: %s", formattedDate);
        int increment = 0;
        for (int i = 0; i < counter; i++) {
            if (this.messages.get(i).isTargetDueDate(formattedDate)) {
                increment++;
                finalMessage += String.format("\n%d.%s", increment,
                        this.messages.get(i).toString());
            }
        }
        return finalMessage;
    }

    public String speak() {
        return String.format("Here are the tasks in your list:\n%s", this);
    }

    @Override
    public String toString() {
        String finalMessage = "";
        if (counter > 0) {
            finalMessage = String.format("1.%s",
                    this.messages.get(0).toString());
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += String.format("\n%d.%s", increment,
                    this.messages.get(i).toString());

        }
        return finalMessage;
    }
}
