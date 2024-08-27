package parser;

import java.util.Scanner;

import exception.GuyException;
import tasks.*;
public class Parser {
    private final Scanner sc;
    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public boolean cmd() {
        TaskManager tm = TaskManager.getInstance();
        String cmd, args;
        while (sc.hasNext()) {
            String[] input = splitCmd(sc.nextLine());
            cmd = input[0];
            args = input[1];

            try {
                switch (cmd) {
                    case "bye":
                        sc.close();
                        return false;
                    case "list":
                        tm.list();
                        break;
                    case "mark":
                        tm.markTask(args);
                        break;
                    case "unmark":
                        tm.unmarkTask(args);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        tm.addTask(cmd, args);
                        break;
                    case "delete":
                        tm.deleteTask(args);
                        break;
                    default:
                        throw new GuyException("Maybe put in an actual command next time, shitass.");
                }
            } catch (GuyException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    private String[] splitCmd(String input) {
        String[] raw = input.split(" ", 2);
        String[] output = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            output[i] = raw[i].trim();
        }
        output[0] = output[0].toLowerCase();
        return output;
    }
}
