import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;

public class TalkaBot {

    private Scanner sc;
    private ArrayList<Task> list;
    private Storage storage;

    public TalkaBot(String filePath) {
        storage = new Storage(filePath);
        this.sc = new Scanner(System.in);
        try {
            this.list = storage.load();
        } catch (IOException e) {
            Message.error(e.getMessage());
        }
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
                    Message.displayList(this.list);
                } else if (input.toLowerCase().startsWith("mark")) {
                    this.mark(input);
                } else if (input.toLowerCase().startsWith("unmark")) {
                    this.unmark(input);
                } else if (input.toLowerCase().startsWith("delete")) {
                    this.delete(input);
                } else if (input.toLowerCase().startsWith("get day")) {
                    this.getDay(input);
                } else if (input.toLowerCase().startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new InvalidScheduleException();
                    }
                    Task curr = new ToDo(input.substring(5));
                    this.list.add(curr);
                    Message.echo(curr, this.list.size());
                    storage.save(this.list);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new InvalidScheduleException();
                    }
                    if (input.indexOf("/by") == -1) {
                        throw new UnknownTimeException("should be done by");
                    }
                    Task curr = new Deadline(input.substring(9));
                    this.list.add(curr);
                    Message.echo(curr, this.list.size());
                    storage.save(this.list);
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
                    Task curr = new Event(input.substring(6));
                    this.list.add(curr);
                    Message.echo(curr, this.list.size());
                    storage.save(this.list);
                } else {
                    if (input == "") {
                    } else {
                        throw new UnknownInputException(input);
                    }
                }
            } catch (TalkaBotException e) {
                Message.error(e.getMessage());
            } catch (DateTimeException e) {
                Message.error("Sorry, I need a valid date format! For example: yyyy-mm-dd");
            }
        }
        Message.goodbye();
    }

    private boolean isValidNumber(String str, int len) {
        try {
            return str.length() > len
                    && Integer.parseInt(str.substring(len)) <= this.list.size()
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
        storage.save(this.list);
    }

    private void unmark(String input) throws InvalidEditException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("unmark");
        }
        Task task = this.list.get(Integer.parseInt(input.substring(7)) - 1);
        task.markAsUndone();
        Message.unmark(task);
        storage.save(this.list);
    }

    private void delete(String input) throws InvalidEditException {
        if (!isValidNumber(input, 7)) {
            throw new InvalidEditException("delete");
        }
        Task task = this.list.remove(Integer.parseInt(input.substring(7)) - 1);
        Message.delete(task, this.list.size());
        storage.save(this.list);
    }

    private void getDay(String input) throws InvalidEditException {
        if (!isValidNumber(input, 8)) {
            throw new InvalidEditException("get the day of");
        }
        Task task = this.list.get(Integer.parseInt(input.substring(8)) - 1);
        Message.getDay(task);
    }

    public static void main(String[] args) {
        new TalkaBot("data/tasks.txt").run();
    }
}
