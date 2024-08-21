import java.util.Scanner;
import java.util.ArrayList;

public class Maxine {

    static ArrayList<Task> list = new ArrayList<>();

    /**
     * This method prompts the user to ask for their answer
     * @return The user's input
     */
    public static Task answer() {
        Scanner scanner = new Scanner(System.in);
        Task answer;
        System.out.print("What can I do for you today? : ");
        answer = new Task(scanner.nextLine());
        return answer;
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! Nice to meet you :) I am Maxine");
        String answer;
        while (true) {
            System.out.print("What can I do for you today? : ");
            answer = scanner.nextLine().toLowerCase();
            String[] arr = answer.split(" ");
            if (answer.equals("bye")) {
                System.out.println("\nBye! I have been maxed out and am going to sleep. Hope to see you again soon!");
                break;
            } else if (answer.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
                int mark = Integer.parseInt(arr[1]) - 1;
                Task curr = list.get(mark);
                curr.changeStatus();
            } else if (arr[0].equals("todo")) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(arr[1]);
                    for (int i = 2; i < arr.length; i++) {
                        String word = " " + arr[i];
                        sb.append(word);
                    }
                    Todo task = new Todo(sb.toString());
                    list.add(task);
                } catch (Exception e) {
                    System.out.println("Please follow this format: todo [enter task]");
                }
            } else if (arr[0].equals("deadline")) {
                try {
                    StringBuilder desc = new StringBuilder();
                    StringBuilder ddl = new StringBuilder();
                    desc.append(arr[1]);
                    boolean check = false;
                    for (int i = 1; i < (arr.length - 1); i++) {
                        if (arr[i].equals("/by")) {
                            check = true;
                        }
                    }
                    if (!check || arr[1].equals("/by")) {
                        throw new MaxineException("Please follow this format: deadline [enter task] /by [enter deadline]");
                    }
                    boolean by = false;
                    for (int i = 2; i < arr.length; i++) {
                        if (arr[i].equals("/by")) {
                            by = true;
                        }
                        else if (by) {
                            String word = " " + arr[i];
                            ddl.append(word);
                        } else {
                            String word = " " + arr[i];
                            desc.append(word);
                        }
                    }
                    Deadline task = new Deadline(desc.toString(), ddl.toString());
                    list.add(task);
                } catch (Exception e) {
                    System.out.println("Please follow this format: deadline [enter task] /by [enter deadline]");
                }
            } else if (arr[0].equals("event")) {
                try {
                    StringBuilder desc = new StringBuilder();
                    StringBuilder start = new StringBuilder();
                    StringBuilder end = new StringBuilder();
                    desc.append(arr[1]);
                    boolean check1 = false;
                    boolean check2 = false;
                    for (int i = 2; i < (arr.length - 1); i++) {
                        if (arr[i].equals("/from")) {
                            check1 = true;
                        }
                        if (arr[i].equals("/to")) {
                            check2 = true;
                        }
                    }
                    
                    if (!check1 || !check2 || arr[1].equals("/from")) {
                        throw new MaxineException("Please follow this format: event [enter event] /from [start date] /to [end date]");
                    }
                    boolean from = false;
                    boolean to = false;
                    for (int i = 2; i < arr.length; i++) {
                        if (arr[i].equals("/from")) {
                            from = true;
                        }
                        else if (arr[i].equals("/to")) {
                            from = false;
                            to = true;
                        } else if (from) {
                            String word = " " + arr[i];
                            start.append(word);
                        } else if (to) {
                            String word = " " + arr[i];
                            end.append(word);
                        } else {
                            String word = " " + arr[i];
                            desc.append(word);
                        }
                    }
                    Event task = new Event(desc.toString(), start.toString(), end.toString());
                    list.add(task);
                } catch (Exception e) {
                    System.out.println("Please follow this format: event [enter event] /from [start date] /to [end date]");
                }
            } else if (arr[0].equals("delete")) {
                int mark = Integer.parseInt(arr[1]) - 1;
                Task curr = list.get(mark);
                list.remove(curr);
            } else {
                System.out.println("please type in a command starting with todo, deadline, event, mark, unmark or list");
            }

        }

        scanner.close();
    }
    
}
