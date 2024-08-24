import FileSaver.FileSaver;
import exceptions.CowExceptions;
import message.Message;
import TodoList.TodoList;
import commands.Command;
import exceptions.UnknownCommandException;
import Parser.Parser;

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

    public static void main(String[] args) throws IOException, UnknownCommandException {
//        Message.Message.printGreetings();
//
//        try {
//            fs = new FileSaver.FileSaver(todoList);
//            fs.loadData();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (UnknownCommandException | MissingParametersException e) {
//            Message.Message.print(e.getMessage());
//        }
//
//        while (true) {
//            try {
//                String command = scanner.nextLine();
//                new Command(command, todoList, fs).action();
//            } catch (UnknownCommandException | MissingParametersException | InvalidTaskException e) {
//                Message.Message.print(e.getMessage());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        new Cow("data/cow.txt").run();
    }
}
