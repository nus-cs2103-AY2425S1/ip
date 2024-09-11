package jarvis.logic;

public class Jarvis {
        TaskList tasklist;
        Parser parser;
        Storage storage;

        public Jarvis(){
            this.tasklist = new TaskList();
            this.parser = new Parser();
            this.storage = Storage.getInstance();
        }



    public static void main(String[] args) {
//        String logo =
//                "       _                  _     \n" +
//                        "      (_)___ _______   __(_)____\n" +
//                        "     / / __ `/ ___/ | / / / ___/\n" +
//                        "    / / /_/ / /   | |/ / (__  )\n" +
//                        " __/ /\\__,_/_/    |___/_/____/\n" +
//                        "/___/\n";
//        Scanner scanner = new Scanner(System.in);
//
//        TaskList tasklist = new TaskList();
//        Parser parser = new Parser();
//        Storage storage = Storage.getInstance();
//
//
//
//        System.out.println("Hello from\n" + logo);
//        System.out.println(" What can I do for you?\n\n");
//
//        String input = "";
//        storage.list();
//        storage.clearFile();
//
//        // Keep reading input until "bye" is entered
//        while (!input.equals("bye")) {
//
//            input = scanner.nextLine();
//            parser.parse(input, tasklist);
//
//        }
//        System.out.println("Bye. Hope to see you again soon!");
    }


    public String getResponse(String input) {
        return this.parser.parseDialog(input, tasklist);
    }


}




