import exception.MaxineException;
import task.Deadline;
import task.Task;
import task.Event;
import task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Maxine {

    static ArrayList<Task> list = new ArrayList<>();
    static String[] arr;
    static Scanner scanner = new Scanner(System.in);
    
    
    public Maxine() {
        // nothing
    }

    /**
     * This method displays the list of tasks
     */
    public static void showList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    /**
     * 
     * @return
     */
    public static String ask() {
        System.out.print("What can I do for you today? : ");
        String answer = scanner.nextLine().toLowerCase();
        arr = answer.split(" ");
        return answer;
    }

    /**
     * This method helps to mark or unmark the task
     */
    public static void changeMark() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = list.get(mark);
        curr.changeStatus();
    }

    /**
     * This method adds a todo task to the list
     */
    public static void todo() {
        Todo todo = new Todo();
        todo.addTodo(arr, list);
    }

    /**
     * This method adds a deadline task to the list
     * @throws MaxineException
     */
    public static void deadline() throws MaxineException {
        Deadline deadline = new Deadline();
        deadline.addToDeadline(arr, list);
    }
    
    public static void event() throws MaxineException {
        Event event = new Event();
        event.addToEvent(arr, list);
    }
    
    public static void delete() {
        int mark = Integer.parseInt(arr[1]) - 1;
        Task curr = list.get(mark);
        curr.delete();
        list.remove(curr);
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        FileReading.rememberFileContents("data/maxine.txt", list);

        while (true) {
            String answer = ask();
            if (answer.equals("bye")) {
                System.out.println("\nBye! I have been maxed out and am going to sleep. Hope to see you again soon!");
                break;
            } else if (answer.equals("list")) {
                showList();
            } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
                changeMark();
                FileWriting.refreshFile(list);
            } else if (arr[0].equals("todo")) {
                try {
                    todo();
                    FileWriting.refreshFile(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: todo [enter task]");
                }
            } else if (arr[0].equals("deadline")) {
                try {
                    deadline();
                    FileWriting.refreshFile(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: deadline [enter task] /by [enter deadline]");
                }
            } else if (arr[0].equals("event")) {
                try {
                    event();
                    FileWriting.refreshFile(list);
                } catch (Exception e) {
                    System.out.println("Please follow this format: event [enter event] /from [start date] /to [end date]");
                }
            } else if (arr[0].equals("delete")) {
                delete();
                FileWriting.refreshFile(list);
            } else {
                System.out.println("please type in a command starting with todo, deadline, event, mark, unmark or list");
            }
        }

        scanner.close();
    }
    
}
