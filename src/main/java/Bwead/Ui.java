package Bwead;

import java.util.Scanner;
import java.io.IOException;

public class Ui {

    private Scanner scanner;
    private TaskList taskList;
    private History history;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void set (History history, TaskList taskList) {
        this.history = history;
        this.taskList = taskList;
    }

    public void showLoadingError (String msg) {
        System.out.println("msg");
    }

    public void handleCommands() throws IOException {
        while (true) {

            String input = scanner.nextLine();

            Parser parser = new Parser(input);

            try {
                if (parser.isOneWord()) {
                    if (input.equals("todo")) {
                        throw new BweadException("OOPS!!! The description of a todo cannot be empty.");
                    } else if (input.equals("deadline")) {
                        throw new BweadException("OOPS!!! The description of a deadline cannot be empty.");
                    } else if (input.equals("event")) {
                        throw new BweadException("OOPS!!! The description of an event cannot be empty.");
                    } else if (!input.equals("bye") && !input.equals("list")) {
                        throw new BweadException("i don't know what that means :(");
                    }
                } else {
                    if (parser.isInvalidMultiWord()) {
                        throw new BweadException("i don't know what that means");
                    }
                }
            } catch (BweadException e) {
                System.out.println(e.getMessage());
            }

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskList.printlist();
            } else if (input.startsWith("mark")) {
                int toadd = parser.getTaskToMark();
                Task task = taskList.getCurrentList().get(toadd - 1);
                task.setDone(true);
                history.updateFile(taskList.getCurrentList());
                System.out.println("Nice! I've marked this task as done: " + task.text);
            } else if (input.startsWith("unmark")) {
                int toadd = parser.getTaskToMark();
                Task task = taskList.getCurrentList().get(toadd - 1);
                task.setDone(false);
                history.updateFile(taskList.getCurrentList());
                System.out.println("OK, I've marked this task as not done yet: " + task.text);
            } else if (input.startsWith("todo ")) {
                String todoName = parser.getTodoName();
                Todo task = new Todo(todoName);
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                Deadline task = new Deadline(parser.getDeadlineName(), parser.getDeadlineDate());
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                Event task = new Event(parser.getDeadlineName(), parser.getEventStart(), parser.getEventEnd());
                taskList.getCurrentList().add(task);
                history.updateFile(taskList.getCurrentList());
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + taskList.getCurrentList().size() + " tasks in the list.");
            } else if (input.startsWith("delete")) {
                Task toremove = taskList.getCurrentList().get(parser.getDeleteIndex() - 1);
                taskList.getCurrentList().remove(toremove);
                history.updateFile(taskList.getCurrentList());
                System.out.println("Noted. I've removed this task: " + toremove.toString());
                System.out.println("Now you have " + taskList.getCurrentList().size() + " tasks in the list.");
            }
        }
    }
}

