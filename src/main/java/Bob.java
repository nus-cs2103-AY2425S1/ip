import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String name = "Bob";
        System.out.println(line);
        System.out.printf("Hello! I'm %s!\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);
        Task[] tasks = new Task[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();

        while (!phrase.equals("bye")) {
            System.out.println(line);
            if (phrase.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.printf("%d.%s\n", i + 1, tasks[i]);
                }
            } else {
                String[] tmp = phrase.split(" ");
                if (tmp[0].equals("mark") || tmp[0].equals("unmark")) {
                    if (tmp[0].equals("mark")) {
                        int num = Integer.parseInt(tmp[1]) - 1;
                        System.out.println(tasks[num].mark());
                    } else {
                        int num = Integer.parseInt(tmp[1]) - 1;
                        System.out.println(tasks[num].unmark());
                    }
                } else {
                    if (tmp[0].equals("todo")) {
                        String string = phrase.substring(5);
                        tasks[index] = new ToDo(string);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  %s\n", tasks[index]);
                    } else if (tmp[0].equals("deadline")) {
                        StringBuilder description = new StringBuilder();
                        StringBuilder by = new StringBuilder();
                        boolean flag = false;
                        for (int i = 0; i < tmp.length; i++) {
                            if (tmp[i].equals("/by")) {
                                flag = true;
                                continue;
                            }
                            if (flag) {
                                by.append(tmp[i]);
                                if (i != tmp.length - 1) {
                                    by.append(" ");
                                }
                            } else if (i > 0) {
                                if (i != 1) {
                                    description.append(" ");
                                }
                                description.append(tmp[i]);
                            }
                        }
                        tasks[index] = new Deadline(description.toString(), by.toString());
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  %s\n", tasks[index]);
                    } else if (tmp[0].equals("event")) {
                        StringBuilder description = new StringBuilder();
                        StringBuilder from = new StringBuilder();
                        StringBuilder to = new StringBuilder();
                        int fromIndex = 0;
                        int toIndex = 0;
                        for (int i = 0; i < tmp.length; i++) {
                            if (tmp[i].equals("/from")) {
                                fromIndex = i;
                            } else if (tmp[i].equals("/to")) {
                                toIndex = i;
                            }
                        }
                        for (int i = 1; i < fromIndex; i++) {
                            if (i != 1) {
                                description.append(" ");
                            }
                            description.append(tmp[i]);
                        }
                        for (int i = fromIndex + 1; i < toIndex; i++) {
                            from.append(tmp[i]);
                            if (i != toIndex - 1) {
                                from.append(" ");
                            }
                        }
                        for (int i = toIndex + 1; i < tmp.length; i++) {
                            to.append(tmp[i]);
                            if (i != tmp.length - 1) {
                                to.append(" ");
                            }
                        }
                        tasks[index] = new Event(description.toString(), from.toString(), to.toString());
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  %s\n", tasks[index]);
                    } else {
                        tasks[index] = new Task(phrase);
                        System.out.printf("added: %s\n", phrase);
                    }
                    index++;
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                }
            }

            System.out.println(line);
            phrase = scanner.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
