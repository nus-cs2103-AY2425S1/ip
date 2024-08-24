import java.util.Scanner;
import java.util.ArrayList;

public class TalkaBot {

    private Scanner sc;
    private ArrayList<Task> list;
    private int counter;

    public TalkaBot() {
        this.sc = new Scanner(System.in);
        this.list = new ArrayList<>();
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
                } else if (input.toLowerCase().startsWith("mark")) {
                    this.mark(input);
                } else if (input.toLowerCase().startsWith("unmark")) {
                    this.unmark(input);
                } else if (input.toLowerCase().startsWith("delete")) {
                    this.delete(input);
                } else if (input.toLowerCase().startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new InvalidScheduleException();
                    }
                    this.list.add(new ToDo(input.substring(5)));
                    Message.echo(this.list.get(this.counter), this.counter + 1);
                    this.counter++;
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new InvalidScheduleException();
                    }
                    if (input.indexOf("/by") == -1) {
                        throw new UnknownTimeException("should be done by");
                    }
                    this.list.add(new Deadline(input.substring(9)));
                    Message.echo(this.list.get(this.counter), this.counter + 1);
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
                    this.list.add(new Event(input.substring(6)));
                    Message.echo(this.list.get(this.counter), this.counter + 1);
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

    private boolean isValidNumber(String str, int len) {
        try {
            return str.length() > len
                    && Integer.parseInt(str.substring(len)) <= this.counter
                    && Integer.parseInt(str.substring(len)) >= 1;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void mark(String input) throws InvalidEditException {
        if (!isValidNumber(input, 5)) {
            throw new InvalidEditException("mark");
        }
        Task task = this.list.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        Message.mark(task);
    }

    private void unmark(String input) throws InvalidEditException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("unmark");
        }
        Task task = this.list.get(Integer.parseInt(input.substring(7)) - 1);
        task.markAsUndone();
        Message.unmark(task);
    }

    private void delete(String input) throws InvalidEditException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("delete");
        }
        Task task = this.list.remove(Integer.parseInt(input.substring(7)) - 1);
        this.counter--;
        Message.delete(task, this.counter);
    }

    public static void main(String[] args) {
        new TalkaBot().run();
    }
}
