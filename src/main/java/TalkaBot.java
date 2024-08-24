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
            try {
                String input = this.sc.nextLine();
                if (input == "") {
                    throw new NoInputException();
                } else if (input.equalsIgnoreCase("bye")) {
                    end = true;
                } else if (input.equalsIgnoreCase("list")) {
                    Message.displayList(this.list, counter);
                } else if (input.toLowerCase().startsWith("mark")
                        /*&& input.length() > 5
                        && this.isValidNumber(input.substring(5))*/) {
                    this.mark(input);
                    //this.mark(this.list[Integer.parseInt(input.substring(5)) - 1]);
                } else if (input.toLowerCase().startsWith("unmark")
                        /*&& input.length() > 7
                        && this.isValidNumber(input.substring(7))*/) {
                    this.unmark(input);
                    //this.unmark(this.list[Integer.parseInt(input.substring(7)) - 1]);
                } else if (input.toLowerCase().startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new InvalidScheduleException();
                    }
                    this.list[this.counter] = new ToDo(input.substring(5));
                    Message.echo(this.list[this.counter], this.counter + 1);
                    this.counter++;
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new InvalidScheduleException();
                    }
                    if (input.indexOf("/by") == -1) {
                        throw new UnknownTimeException("should be done by");
                    }
                    this.list[this.counter] = new Deadline(input.substring(9));
                    Message.echo(this.list[this.counter], this.counter + 1);
                    this.counter++;
                } else if (input.toLowerCase().startsWith("event")) {
                    if (input.length() < 7) {
                        throw new InvalidScheduleException();
                    }
                    if (input.indexOf("/from") == -1) {
                        throw new UnknownTimeException("starts");
                    }
                    if (input.indexOf("/to") == -1) {
                        throw new UnknownTimeException("ends");
                    }
                    this.list[this.counter] = new Event(input.substring(6));
                    Message.echo(this.list[this.counter], this.counter + 1);
                    this.counter++;
                } else {
                    if (input == "") {
                    } else {
                        throw new UnknownInputException(input);
                    }
                }
            } catch (TalkaBotException e) {
                Message.error(e);
            }
        }
        Message.goodbye();
    }

    private boolean isValidNumber(String str, boolean marking) {
        try {
            if (marking) {
                return str.length() > 5
                        && Integer.parseInt(str.substring(5)) <= this.counter
                        && Integer.parseInt(str.substring(5)) >= 1;
            } else {
                return str.length() > 7
                        && Integer.parseInt(str.substring(7)) <= this.counter
                        && Integer.parseInt(str.substring(7)) >= 1;
            }
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void mark(String input) throws InvalidMarkingException {
        if (!isValidNumber(input, true)) {
            throw new InvalidMarkingException("mark");
        }
        Task task = this.list[Integer.parseInt(input.substring(5)) - 1];
        task.markAsDone();
        Message.mark(task);
    }

    private void unmark(String input) throws InvalidMarkingException {
        if (!isValidNumber(input, false)) {
            throw new InvalidMarkingException("unmark");
        }
        Task task = this.list[Integer.parseInt(input.substring(7)) - 1];
        task.markAsUndone();
        Message.unmark(task);
    }

    public static void main(String[] args) {
        new TalkaBot().run();
    }
}
