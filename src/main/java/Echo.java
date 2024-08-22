import java.util.ArrayList;
import java.util.List;

public class Echo {
    private String word;
    private List<String> list;

    public Echo() {
        this.list = new ArrayList<>();
    }

    public void setWord(String word) {
        this.word = word;
    }
    public void echoOut() {
        switch (word.toLowerCase()) {
            case "list":
                if (list.isEmpty()) {
                    System.out.println("No items in the list yet!\n");
                }
                for (int i = 0; i < list.size(); i++) {
                    String response = String.format("%d. %s", i + 1, list.get(i));
                    System.out.println(response);
                }
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n"
                                +  "____________________________________________________________\n");
                break;
            default:
                list.add(word);
                System.out.println("\n____________________________________________________________\n"
                        + "added: " + word + "\n____________________________________________________________\n");
                break;
        }
    }
}
