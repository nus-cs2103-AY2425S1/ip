package impl.chatbot;

import impl.storage.TaskList;
import impl.ui.Parser;
import impl.ui.Ui;

import java.util.Scanner;

public class Danny {
    private final TaskList list; // Array to keep all the Tasks
    Ui ui;
    Parser parser;

    public Danny() {
        list = new TaskList();
        parser = new Parser(list);
        ui = new Ui(parser);
    }

    public TaskList run() {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        while (!in.equalsIgnoreCase("bye")) {
            ui.running(in);
            in = input.nextLine();
        }
        ui.end();
        return list;
    }

    public String getResponse(String in) {
        if (in.equalsIgnoreCase("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        return ui.running(in);
    }

    public void end() {
        ui.end();
    }

    public void parseString(String in) {
        parser.handleInput(in);
    }

    public void setLastDone() {
        list.setLastDone();
    }

    public void setLastTag(String tag) {
        list.setTag(tag);
    }

    public String saveTasks() {
        return list.saveTasks();
    }
}
