public class Talky {
    public static void printSeperator(String content) {
        String seperator = "---------------------------------------";
        System.out.println(seperator);
        System.out.println(content);
        System.out.println(seperator);
    }
    public static void main(String[] args) {
        String seperator = "---------------------------------------";
        printSeperator("Hello I'm Talky\n" + "How may I help you?");
        printSeperator("Bye. Do let me know if there's anything else!");
    }
}
