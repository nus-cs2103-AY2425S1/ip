import java.util.Scanner;
import java.util.ArrayList;

public class TalkaBot {

    private Scanner sc;
    private Task[] list;
    private int counter;

    public TalkaBot() {
        this.sc = new Scanner(System.in);
        this.list = new Task[100];
        this.counter = 0;
    }

    private void run() {
        boolean end = false;
        Message.hello();
        while (!end) {
            String input = this.sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                end = true;
            } else if (input.equalsIgnoreCase("list")) {
                Message.displayList(this.list, counter);
            } else if (input.toLowerCase().startsWith("mark ")
                    && input.length() > 5
                    && this.isValidNumber(input.substring(5))) {
                this.mark(this.list[Integer.parseInt(input.substring(5)) - 1]);
            } else if (input.toLowerCase().startsWith("unmark ")
                    && input.length() > 7
                    && this.isValidNumber(input.substring(7))) {
                this.unmark(this.list[Integer.parseInt(input.substring(7)) - 1]);
            } else if (input.toLowerCase().startsWith("todo ")) {
                this.list[this.counter] = new ToDo(input.substring(5));
                Message.echo(this.list[this.counter], this.counter + 1);
                this.counter++;
            } else if (input.toLowerCase().startsWith("deadline ")) {
                this.list[this.counter] = new Deadline(input.substring(9));
                Message.echo(this.list[this.counter], this.counter + 1);
                this.counter++;
            } else if (input.toLowerCase().startsWith("event ")) {
                this.list[this.counter] = new Event(input.substring(6));
                Message.echo(this.list[this.counter], this.counter + 1);
                this.counter++;
            } /*else {
                this.list[this.counter] = new Task(input);
                Message.echo(input);
                this.counter++;
            }*/
        }
        Message.goodbye();
    }

    private boolean isValidNumber(String str) {
        try {
            return Integer.parseInt(str) <= this.counter
                    && Integer.parseInt(str) >= 1;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void mark(Task task) {
        task.markAsDone();
        Message.mark(task);
    }

    private void unmark(Task task) {
        task.markAsUndone();
        Message.unmark(task);
    }

    public static void main(String[] args) {
        new TalkaBot().run();
    }
}
