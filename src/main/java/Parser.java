public class Parser {
    static Task parseTask(String s) {
        String command = s.split(" ")[0];
        String desc = s.substring(s.indexOf(" "));

        desc = desc.stripLeading();

        Task t = null;

        if (command.equals("todo")) {
            try {
                t = new ToDoTask(desc);
            } catch (Exception e) {
                Ui.printAnything(e.getMessage());
            }
        } else if (command.equals("deadline")) {
            try {
                String[] arr = desc.split("/by");
                try {
                    t = new Deadline(arr[0].strip(), arr[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Ui.printAnything("missing /by");
                }
            } catch (EmptyDescException e) {
                Ui.printAnything(e.getMessage());
            }
        } else if (command.equals("event")) {
            String[] arr = desc.split("/from");
            String[] arr2;
            try {
                arr2 = arr[1].split("/to");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("missing /from");
            }
            Task t = null;
            try {
                try {
                    t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("missing /to");
                }
            } catch (Exception e) {
                Ui.printAnything(e.getMessage());
            }
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                Ui.printAnything(e.getMessage());
            }
            //System.out.println("Unknown command: " + command);
        }

        if (t == null) {
            System.out.println("null tasked returned back to Storage.load()");
        }

        return t;
    }
}
