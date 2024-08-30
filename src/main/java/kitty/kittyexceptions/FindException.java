package kitty.kittyexceptions;

public class FindException extends KittyException {
    @Override
    public String toString() {
        return "Invalid input. Search for task by inputting:\n" +
                "    find <keyword>\n" + "Only one keyword at a time is allowed.";
    }
}
