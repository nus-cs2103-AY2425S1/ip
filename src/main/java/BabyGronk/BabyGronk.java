package BabyGronk;

import parser.EmptyInputException;
import parser.Instruction;
import parser.InvalidInputException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.Scanner;

public class BabyGronk {
    private final static String SEPARATOR =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    BabyGronk(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        taskList = new TaskList(storage.load());
    }

    private String   handleInput(String input) {
        Parser parser = new Parser();
        Instruction instruction;
        try {
            instruction = parser.parseInstruction(input);
        } catch (InvalidInputException e) {
            return (e.getMessage());
        }
        if (instruction.getInstruction().equals("bye")) {
            storage.saveData(taskList.getTasks());
            ui.logOff();
        }
        if (instruction.getInstruction().equals("list")) {
            return (taskList.toString());
        }
        if (instruction.getInstruction().equals("mark") || instruction.getInstruction().equals("m")) {
            return (taskList.markTask(instruction.getArgs(), true));
        }
        if (instruction.getInstruction().equals("unmark") || instruction.getInstruction().equals("um")) {
            return (taskList.markTask(instruction.getArgs(), false));
        }
        if (instruction.getInstruction().equals("delete")) {
            return (taskList.delete(instruction.getArgs()));
        }
        if (instruction.getInstruction().equals("todo") || instruction.getInstruction().equals("deadline") ||
                instruction.getInstruction().equals("event")) {
            return (addTask(instruction));
        }
        return (null);
    }

    private String addTask(Instruction instruction) {
        String task = instruction.getInstruction();
        String[] args = instruction.getArgs();
        switch (task) {
        case "todo":
            return (taskList.add(new ToDo(args[0])));
        case "deadline":
            return (taskList.add(new Deadline(args[0], args[1])));
        case "event":
            return (taskList.add(new Event(args[0], args[1], args[2])));
        }
        return null;
    }

    public static Task initTasks(String input) {
        String[] args = input.split("] ");
        if (args[0].charAt(1) == 'T') {
            ToDo todo = new ToDo(args[1]);
            if (args[0].charAt(4) == 'X') {
                todo.setDone(true);
            }
            return (todo);
        } else if (args[0].charAt(1) == 'D') {
            String[] args2 = args[1].split(" \\(");
            Deadline deadline = new Deadline(args2[0], args2[1].substring(4, args2[1].length() - 1));
            if (args[0].charAt(4) == 'X') {
                deadline.setDone(true);
            }
            return (deadline);
        } else if (args[0].charAt(1) == 'E') {
            String[] args2 = args[1].split(" \\(");
            String[] args3 = args2[1].split(" to: ");
            Event event = new Event(args2[0], args3[0].substring(6), args3[1].substring(0, args3[1].length() - 1));
            if (args[0].charAt(4) == 'X') {
                event.setDone(true);
            }
            return (event);
        } else {
            System.out.println("Line: " + input + " has invalid format type, data cannot be loaded");
            return (null);
        }
    }

    public void run() {
        ui.mascot();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    String input = Parser.parseInput(scanner.nextLine());
                    ui.printMessage(handleInput(input));
                } catch (EmptyInputException e) {
                    System.out.println(SEPARATOR + e.getMessage() + SEPARATOR);
                }
            } else {
                scanner.close();
                break;
            }
        }
    }

    public static void  main(String[] args) {
        new BabyGronk("./data/BabyGronk.txt").run();
    }
}
