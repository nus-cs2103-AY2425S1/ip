package poChat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PoChat poChat = new PoChat();
        ChatData chatData = new ChatData("src//main//chat_data.txt");
        poChat.load(chatData);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String textInput = scanner.nextLine();
            boolean isChatOver = poChat.parse(textInput);

            if (isChatOver) {
                break;
            }
            poChat.save(chatData);
        }
    }
}