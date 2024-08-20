package processor.text;

import response.Response;

public class List {
  public static Response process(String prompt) {
    return new Response(TextList.getStringList());
  }
}
