import java.util.Scanner;

public class Yapper {
    public static int itemsInList = 0;
    public static String[] list = new String[100];
    public static void main(String[] args) {
        // greeting message
        System.out.println("Hello! I'm Yapper \n" +
                "What can I do for you? \n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            read(text);
        }
    }

    // reads commands
    public static void read(String command)
    {
        if (command.equals("bye"))
        {
            exit();
            System.exit(0);
        }
        else if (command.equals("list"))
        {
            returnList();
        }
        else
        {
            addToList(command);
        }
    }

    // return list
    public static void returnList()
    {
        for (int i = 1; i <= itemsInList; i++)
        {
            System.out.println(i + ". " + list[i]);
        }
    }

    // add to List
    public static void addToList(String text)
    {
        itemsInList++;
        list[itemsInList] = text;
        System.out.println("Added: " + text);
    }

    // exits when the user types the command "bye"
    public static void exit()
    {
        System.out.println("Yapper shall yap next time!");
    }
}
