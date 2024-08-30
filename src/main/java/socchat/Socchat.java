package socchat;

import java.util.Scanner;

public class Socchat {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Scanner scanner = new Scanner(System.in);
    enum Command{
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE
    }
    public Socchat(String filePath) {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (SocchatException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        greet();

        chatLoop:
        while (true) {
            String[] strToken;
            try{
                String input = scanner.next();
                Command command = getCommand(input);
                System.out.print("> ");
                switch (command) {
                    case BYE:
                        exit();
                        break chatLoop;
                    case LIST:
                        taskList.list();
                        break;
                    case MARK:
                        String taskIndexString = scanner.nextLine().trim();
                        taskList.setMark(taskIndexString,true);
                        break;
                    case UNMARK:
                        taskIndexString = scanner.nextLine().trim();
                        taskList.setMark(taskIndexString, false);
                        break;
                    case TODO:
                        String str = command + scanner.nextLine();
                        strToken = parser.tokenizeAdd(str);
                        taskList.addTodo(strToken);
                        break;
                    case DEADLINE:
                        str = command + scanner.nextLine();
                        strToken = parser.tokenizeAdd(str);
                        taskList.addDeadline(strToken);
                        break;
                    case EVENT:
                        str = command + scanner.nextLine();
                        strToken = parser.tokenizeAdd(str);
                        taskList.addEvent(strToken);
                        break;
                    case DELETE:
                        taskIndexString = scanner.nextLine().trim();
                        taskList.delete(taskIndexString);
                        break;
                }
            } catch (SocchatException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public static void main(String[] args) {

        new Socchat("tasks.txt").run();
    }
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }

    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }



}

