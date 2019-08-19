package Orchestrator;


import Handler.MessageHandler;
import Request.Request;
import Response.Response;

import java.util.Optional;

public class Orchestrator {


    public static void run(Iterable<String> commands) {
        for (String command : commands) {
            if (command.equalsIgnoreCase("exit")) break;
            Request request = ParserUserCommand.parse(command);
            Optional<MessageHandler> messageHandler = HandlerSelector.selectHandler(request);
            Response response = messageHandler.orElseThrow(() -> new IllegalStateException("Unsupported request")).execute(request);
            ResponsePresenter.show(response);
        }
    }
}
