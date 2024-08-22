package main.java;

import main.java.Exceptions.EmptyDescriptionError;
import main.java.Exceptions.InvalidCommandError;

import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
    private Scanner scanner = new Scanner(System.in);

    private ArrayList<Task> lst = new ArrayList<>();

    private String name;
    private String exitKeyword = "bye";
    private String listKeyword = "list";

    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addToList(Task task){
        try {
            lst.add(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (int i = 0; i < lst.size(); ++i) {
            System.out.println(num + ". " + lst.get(i).getDesc());
            num +=1;
        }
    }

    public void run() throws InvalidCommandError, EmptyDescriptionError {
        greet();
        while (true){
            try {

                String input = scanner.nextLine();

                String [] parts = input.split(" ", 2);

                if (input.equals(exitKeyword)) {
                    goodbye();
                    return;
                } else if (input.equals(listKeyword)) {
                    showList();
                } else if (parts.length == 2 && parts[0].equals("mark")) {
                    Task task = lst.get(Integer.parseInt(parts[1])-1);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.getDesc());
                } else if (parts.length == 2 && parts[0].equals("unmark")) {
                    Task task = lst.get(Integer.parseInt(parts[1])-1);
                    task.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.getDesc());
                } else {
                    String command = parts[0];

                    if (parts.length < 2) {
                        throw new Exception("Invalid input.");
                    }
                    String description = parts[1];

                    String by = null;
                    String from = null;
                    String to = null;

                    if (description.contains(" /")) {
                        String [] details = description.split(" /");
                        description = details[0].trim();
                        for (int i = 1; i < details.length; i++) {
                            String detail = details[i].trim();
                            if (detail.startsWith("by ")) {
                                by = detail.substring(3).trim();
                            } else if (detail.startsWith("from ")) {
                                from = detail.substring(5);
                            } else if (detail.startsWith("to ")) {
                                to = detail.substring(3).trim();
                            }
                        }
                    }

                    Task task = null;
                    if (command.equals("todo")) {
                        if (description.equals("")) {
                            throw new EmptyDescriptionError();
                        }
                        task = new Todo (description);
                        addToList(task);
                    } else if (command.equals("deadline")) {
                        task = new Deadline(by, description);
                        addToList(task);
                    } else if (command.equals("event")) {
                        task = new Event(from, to, description);
                        addToList(task);
                    } else {
                        throw new InvalidCommandError();
                    }
                    System.out.println("Got it. I've added this task: " + "\n" + task.getDesc());
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        }

}
