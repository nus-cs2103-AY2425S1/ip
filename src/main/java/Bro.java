import bro.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bro {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            s.append(String.format("%d.%s\n", i + 1, tasks.get(i).toLoad()));
        }
        return s.toString();
    }

    public void run() {
        ui = new Ui();
        parser = new Parser(ui);
        tasks = new TaskList(ui, parser);
        storage = new Storage("./src/main/java/data.txt", tasks);
        try {
            storage.loadIn();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nothing to load");
        } catch (BroException be) {
            System.out.println(be.getMessage());
        }
        ui.printWelcome();
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            if (word.equalsIgnoreCase("list")) {
                ui.printList(tasks);
            } else {
                String action = word.split(" ", 2)[0];
                String info;
                if (word.split(" ", 2).length == 1) {
                    info = "";
                } else {
                    info = word.split(" ", 2)[1];
                }
                try {
                    switch (action.toLowerCase()) {
                        case "mark":
                            tasks.markTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "unmark":
                            tasks.unmarkTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "delete":
                            tasks.deleteTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "todo":
                            tasks.addTodo(info);
                            storage.saveToFile();
                            break;
                        case "deadline":
                            tasks.addDeadline(info);
                            storage.saveToFile();
                            break;
                        case "event":
                            tasks.addEvent(info);
                            storage.saveToFile();
                            break;
                        default:
                            ui.printDefault();
                    }
                } catch (BroException be) {
                    System.out.println(be.getMessage());
                } catch (NumberFormatException nfe) {
                    ui.printErrorFormat();
                } catch (IndexOutOfBoundsException e) {
                    ui.printErrorSize(tasks.size());
                }
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        Bro bro = new Bro();
        bro.run();
    }
}