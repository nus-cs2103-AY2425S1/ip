package ahmad.processor;

import java.util.List;

import ahmad.response.Response;

public class Echo {
    public static Response process(String prompt) {
        return new Response(List.of(prompt));
    }
}
