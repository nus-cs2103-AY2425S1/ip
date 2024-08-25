package mendel.metacognition;

import mendel.discretetask.Task;
import mendel.preetyprint.FormatText;
import mendel.mendelexception.MendelException;
import mendel.mendelexception.ConditionalExceptionHandler;


import java.util.ArrayList;
import java.util.List;


public class TaskStorage extends MendelAction{
    private final List<Task> messages;
    int counter;

    public TaskStorage() {
        this.messages = new ArrayList<>();
        this.counter = 0;
    }

    public void marker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsDone();
        String outputMessage = String.format("Nice! I've marked this task as done:\n  %s",
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());

    }

    public void unMarker(int serial) throws MendelException {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.get(serial);
        task.markAsUnDone();
        String outputMessage = String.format("OK, I've marked this task as not done yet:\n  %s",
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void delete(int serial) throws MendelException{
        ConditionalExceptionHandler.of()
                .conditionTriggerException(serial >= this.counter, "OOPS! serial is too big.\nDecrease serial.")
                .conditionTriggerException(serial < 0, "OOPS! Serial is too small.\nIncrease serial.");
        Task task = this.messages.remove(serial);
        this.counter--;
        String outputMessage = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                this.counter);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void add(Task element) {
        this.messages.add(element);
        this.counter++;
        String outputMessage = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                element, this.counter);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void speak() {
        String outputMessage = String.format("Here are the tasks in your list:\n%s",
                this);
        System.out.println(new FormatText(outputMessage).wrapLines());
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

