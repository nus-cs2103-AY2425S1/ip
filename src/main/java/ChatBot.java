package main.java;

import main.java.Exceptions.EmptyDescriptionError;
import main.java.Exceptions.InvalidCommandError;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
        loadTasksFromFile();
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Save tasks to file. Creates tasks.ser file if not present.
     */
    private void saveTasksToFile() {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream("tasks.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(lst);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads tasks from saved file, if it exists.
     */
    private void loadTasksFromFile() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream("tasks.ser");
            in = new ObjectInputStream(fileIn);
            lst = (ArrayList<Task>) in.readObject();
        } catch (Exception e) {
            // Handle the case where the file is not found or class is not found
            lst = new ArrayList<>();
            saveTasksToFile();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (fileIn != null) {
                    fileIn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addToList(Task task){
        try {
            lst.add(task);
            saveTasksToFile();
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

    public Task delete(int i) {
        Task task = lst.remove(i-1);
        saveTasksToFile();
        return task;
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
                } else if (parts.length == 2 && parts[0].equals("delete")) {
                    int numToDelete = Integer.parseInt(parts[1]);
                    Task removed = delete(numToDelete);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removed.getDesc());
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
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
                    System.out.println("Got it. I've added this task:" + "\n" + task.getDesc());
                    if (lst.size() == 1) {
                        System.out.println("Now you have " + lst.size() + " task in the list.");
                    } else {
                        System.out.println("Now you have " + lst.size() + " tasks in the list.");
                    }

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        }

}
