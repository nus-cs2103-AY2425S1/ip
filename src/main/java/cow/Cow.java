package cow;

import cow.commands.Command;
import cow.filesaver.FileSaver;
import cow.message.Message;
import cow.parser.Parser;
import cow.exceptions.CowExceptions;
import cow.todoList.TodoList;

import java.io.IOException;
import java.util.Scanner;

public class Cow {
    // solution below inspired by https://www.w3schools.com/java/java_user_input.asp
    private static final Scanner scanner = new Scanner(System.in);
    private TodoList todoList;
    private FileSaver fs;

    public Cow(String filePath) {
        this.fs = new FileSaver(filePath);
        try {
            todoList = fs.loadData();
        } catch (IOException e) {
            Message.printLoadingError();
            todoList = new TodoList();
        }
    }

    public void run() {
        Message.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Message.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(todoList, fs);
                isExit = c.isExit();
            } catch (CowExceptions e) {
                Message.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Cow("data/cow.txt").run();
    }
}
