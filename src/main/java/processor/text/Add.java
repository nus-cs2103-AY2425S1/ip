package processor.text;

import response.Response;

public class Add {
  public static Response process(String prompt) {
    TextList.addString(prompt);
    return new Response(java.util.List.of("added: " + prompt));
  }
}
