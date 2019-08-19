package Orchestrator;


import Handler.MessageHandler;
import Handler.ParkingHandler;
import Handler.ReportHandler;
import Handler.TicketHandler;
import Request.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class HandlerSelector {

    private static List<MessageHandler> handlers = new ArrayList<>(
            Arrays.asList(new ParkingHandler(), new TicketHandler(), new ReportHandler()));

    public static Optional<MessageHandler> selectHandler(Request request) {
        String nameClassHandler = request.getClass().getSimpleName();
        return handlers.stream().filter(handler -> handler.getSupportedRequest().contains(nameClassHandler)).findFirst();
    }
}
