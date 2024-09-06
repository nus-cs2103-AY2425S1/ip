import java.util.Scanner;
import java.util.ArrayList;
public class Bing {
    public static void main(String[] args) {
        String message = "______________________________\n"
                + "Hi! My name is Bing\n"
                + "How can I help you?\n"
                + "______________________________\n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            input = scanner.nextLine().trim();
            if (input.equals("Bye") || input.equals("bye")) {
                System.out.println("______________________________\n"
                        + "Bye. Have a good day.\n"
                        + "______________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("______________________________\n"
                                    + "All tasks in your list :\n");
                for (int i=0 ; i<tasks.size() ; i++) {
                    System.out.println((i + 1)+". "+tasks.get(i).toString());
                }
                System.out.println("______________________________\n");
            } else if (input.startsWith("mark")) {
                try {
                    int x = Integer.parseInt(input.substring(5).trim());
                    if (x > 0 && x<=tasks.size()) {
                        tasks.get(x - 1).setStatus(TaskStatus.DONE);
                        System.out.println("______________________________\n");
                        System.out.println("This task is marked as done :");
                        System.out.println(tasks.get(x-1).toString());
                        System.out.println("______________________________\n");
                    } else {
                        System.out.println("Invalid Input.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("______________________________\n");
                    System.out.println("Invalid Format");
                    System.out.println("______________________________\n");
                }

            } else if (input.startsWith("unmark")) {
                try {
                    int x = Integer.parseInt(input.substring(7));
                    if (x>0 && x<=tasks.size()) {
                        tasks.get(x-1).setStatus(TaskStatus.UNDONE);
                        System.out.println("______________________________\n");
                        System.out.println("This task is marked as undone :");
                        System.out.println(tasks.get(x-1).toString());
                        System.out.println("______________________________\n");
                    } else {
                        System.out.println("Invalid Input.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("______________________________\n");
                    System.out.println("Invalid Format");
                    System.out.println("______________________________\n");
                }


            } else if (input.startsWith("todo")) {
                String temp = input.substring(4).trim();
                if (temp.isEmpty()) {
                    System.out.println("Invalid Input.\n");
                } else {
                    Task temp2 = new ToDo(temp);
                    tasks.add(temp2);
                    System.out.println("______________________________\n"
                            + "Added the task:\n"
                            + temp.toString() + "\n"
                            + "Total tasks - " + tasks.size() + "\n"
                            + "______________________________\n");
                }

            } else if (input.startsWith("deadline ")) {
                try {
                    String[] segments = input.substring(9).split(" /by ");
                    if (segments.length < 2 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty()) {
                        System.out.println("Invalid Input.\n");
                    } else {
                        Task temp = new Deadline(segments[0],segments[1]);
                        tasks.add(temp);
                        System.out.println("______________________________\n"
                                + "Added the task:\n"
                                + temp.toString() + "\n"
                                + "Total tasks - " + tasks.size() + "\n"
                                + "______________________________\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("______________________________\n");
                    System.out.println("Invalid Format");
                    System.out.println("______________________________\n");
                }


            } else if (input.startsWith("event ")) {
                try {
                    String[] segments = input.substring(6).split(" /from ");
                    if (segments.length<2) {
                        System.out.println("Invalid Input.\n");
                    } else {
                        String[] segments2 = segments[1].split(" /to ");
                        Task temp = new Event(segments[0],segments2[0],segments2[1]);
                        tasks.add(temp);
                        System.out.println("______________________________\n"
                                + "Added the task:\n"
                                + temp.toString() + "\n"
                                + "Total tasks - " + tasks.size() + "\n"
                                + "______________________________\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("______________________________\n");
                    System.out.println("Invalid Format");
                    System.out.println("______________________________\n");
                }

            } else if (input.startsWith("delete")) {
                try {
                    int x = Integer.parseInt(input.substring(7).trim());
                    if (x>0 && x<=tasks.size()) {
                        Task removedTask = tasks.remove(x - 1);
                        System.out.println("______________________________\n");
                        System.out.println("Task removed :");
                        System.out.println(removedTask.toString());
                        System.out.println("Total tasks - " + tasks.size() + "\n");
                        System.out.println("______________________________\n");
                    } else {
                        System.out.println("Invalid Input.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("______________________________\n");
                    System.out.println("Invalid Format");
                    System.out.println("______________________________\n");
                }
            } else {
                System.out.println("Invalid Input.\n");
            }
        }
    }
}