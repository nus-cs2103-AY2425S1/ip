package bro.ui;

import bro.task.Task;

// This class encapsulates user interactions with bro.Bro
public class UI {
    final static String GREETING_MESSAGE = """
                 Hello! I'm bro.Bro
                 What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";

    // Prints an adds to list on standard output
    public static void reply(String content) {
        String replyStr = String.format("""
                ____________________________________________________________
                %s
                bro.Bro
                ____________________________________________________________
                """, content);
        System.out.print(replyStr);
    }

    // Prints an add task reply
    public static void addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                bro.Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
    }

}
