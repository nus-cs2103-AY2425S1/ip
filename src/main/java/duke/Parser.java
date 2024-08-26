package duke;

public class Parser {
    public static int splitGetNum(String input){
        String[] words = input.split(" ");
        return Integer.parseInt(words[1]) - 1;
    }

    public static String splitGetWords(String input){
        String[] words = input.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
}
