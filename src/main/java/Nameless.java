import java.lang.reflect.Type;
import java.net.PasswordAuthentication;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Nameless {
    private final DateTimeFormatter parse_format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private Storage storage;
    private TaskList tasks;
    private TypeOfException exception;
    private Ui ui;

    public Nameless(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        exception = new TypeOfException();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            exception.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetings();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            try {
                if(input.equals("bye")) {
                    bye();
                    break;
                } else if(input.equals("list")) {
                    ui.showList(tasks);
                } else if(input.matches("mark \\d+")) {
                    ui.showMarkTask(tasks, Parser.splitGetNum(input));
                } else if(input.matches("unmark \\d+")) {
                    ui.showUnmarkTask(tasks, Parser.splitGetNum(input));
                } else if(input.matches("todo(?: .+)?")) {
                    todo(input);
                } else if(input.matches("deadline(?: .+)?")) {
                    deadline(input);
                } else if(input.matches("event(?: .+)?")) {
                    event(input);
                } else if(input.matches("delete \\d+")) {
                    ui.showDeleteTask(tasks, Parser.splitGetNum(input));
                }
                else {
                    exception.noIdea();
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void bye() throws DukeException {
        storage.writeFile(tasks.getTasks());
        ui.goodbye();
    }

    private void todo(String input) throws DukeException {
        String words = Parser.splitGetWords(input);
        if (words.isEmpty()) {
            exception.todoFormatError();
        }
        tasks.addTask(new Todo(words));
        ui.showAddTask(tasks);
    }

    private void deadline(String input) throws DukeException {
        String[] words = Parser.splitGetWords(input).split(" /by ", 2);
        if(words.length != 2) {
            exception.deadlineFormatError();
        }
        try {
            LocalDateTime date = LocalDateTime.parse(words[1], parse_format);
            tasks.addTask(new Deadline(words[0], date));
            ui.showAddTask(tasks);
        } catch (DateTimeParseException e) {
            exception.timeFormatError();
        }
    }

    private void event(String input) throws DukeException {
        String[] words = Parser.splitGetWords(input).split(" /from | /to ", 3);
        if (words.length != 3) {
            exception.eventFormatError();
        }
        try {
            LocalDateTime from = LocalDateTime.parse(words[1], parse_format);
            LocalDateTime to = LocalDateTime.parse(words[2], parse_format);
            tasks.addTask(new Event(words[0], from, to));
            ui.showAddTask(tasks);
        } catch (DateTimeParseException e) {
            exception.timeFormatError();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Nameless("data/tasks.txt").run();
    }
}