import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> toDoList = new ArrayList<Task>();
    private static int counter = 1;

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++){
            int serial = i+1;
            Task task = toDoList.get(i);
            System.out.println(serial + "." + task.toString());
        }
    }

    private static void mark(int index) throws InvalidIndexException {
        if (index-1 < 0 || index-1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void unmark(int index) throws InvalidIndexException {
        if (index-1 < 0 || index-1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index-1);
        task.unmarkAsUndone();
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    private static void delete(int index) throws InvalidIndexException {
        if (index-1 < 0 || index-1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index-1);
        toDoList.remove(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        counter -= 1;
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws DukeException {
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            String[] getInstr = command.split(" ", 2);
            String instr = getInstr[0];
            if (instr.equals("mark")) {
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    mark(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
            } else if (instr.equals("unmark")) {
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    unmark(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
            } else if (instr.equals("delete")) {
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    delete(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
            } else if (instr.equals("list")) {
                printList();
            } else if (instr.equals("bye")) {
                break;
            } else if (instr.equals("todo")) {
                try {
                    Task task;
                    if (getInstr.length <= 1) {
                        task = new Todo(null);
                    } else {
                        task = new Todo(getInstr[1]);
                    }
                    toDoList.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
            } else if (instr.equals("deadline")) {
                try {
                    Task task;
                    if (getInstr.length <= 1) {
                        task = new Deadline(null, null);
                    } else {
                        String[] getBy = getInstr[1].split(" /by ", 2);
                        if (getBy.length <= 1) {
                            task = new Deadline(getBy[0], null);
                        } else {
                            task = new Deadline(getBy[0], getBy[1]);
                        }
                    }
                    toDoList.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.toString());
                } catch (EmptyDeadlineException e) {
                    System.out.println(e.toString());
                }
            } else if (instr.equals("event")) {
                try {
                    Task task;
                    if (getInstr.length <= 1) {
                        task = new Event(null, null, null);
                    } else {
                        String[] getTime = getInstr[1].split(" /", 3);
                        if (getTime.length == 3) {
                            String[] from = getTime[1].split(" ", 2);
                            String[] to = getTime[2].split(" ", 2);
                            task = new Event(getTime[0], from[1], to[1]);
                        } else {
                            task = new Event(getTime[0], null, null);
                        }
                    }
                    toDoList.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.toString());
                } catch (EmptyTimeException e) {
                    System.out.println(e.toString());
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        sc.close();
        bye();
    }
}
