package Handler;

import Constant.ParkingRequestCommand;
import Entity.Car;
import Entity.EmptyTicket;
import Entity.Ticket;
import Request.CloseTicketRequest;
import Request.IssueTicketRequest;
import Request.Request;
import Response.CloseTicketResponse;
import Response.IssueTicketResponse;
import Response.NullResponse;
import Response.Response;
import Storage.Context;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class TicketHandler implements MessageHandler {

    @Override
    public Response execute(Request request) {
        Response response;
        switch (request.getClass().getSimpleName()) {
            case ParkingRequestCommand.ISSUE_TICKET:
                response = issueTicket((IssueTicketRequest) request);
                break;
            case ParkingRequestCommand.CLOSE_TICKET:
                response = closeTicket((CloseTicketRequest) request);
                break;
            default:
                throw new IllegalStateException("Unexpected request: " + request.getClass().getSimpleName());
        }
        return response;
    }

    @Override
    public Collection<String> getSupportedRequest() {
        return new HashSet<>(Arrays.asList(IssueTicketRequest.class.getSimpleName(),
                CloseTicketRequest.class.getSimpleName()));
    }

    private Response issueTicket(IssueTicketRequest issueTicketRequest) {
        List<Ticket> tickets = Context.getStorage().getTickets();
        int slotOfTicket = IntStream.range(0, tickets.size()).filter(i -> tickets.get(i).getClass().getSimpleName()
                .equals("EmptyTicket"))
                .findFirst().orElse(tickets.size());
        Ticket ticket = new Ticket(slotOfTicket + 1, issueTicketRequest.getCar());
        Context.getStorage().getTickets().add(slotOfTicket, ticket);
        return new IssueTicketResponse(ticket);
    }


    private Response closeTicket(CloseTicketRequest closeTicketRequest) {
        List<Ticket> tickets = Context.getStorage().getTickets();
        if (tickets.isEmpty()) {
            return new NullResponse("Unable to close ticket: " + closeTicketRequest.getClass().getSimpleName());
        }
        int emptySlot = IntStream.range(0, tickets.size())
                .filter(i -> tickets.get(i).getSlot() == closeTicketRequest.getTicket().getSlot())
                .findFirst().orElse(0);
        Context.getStorage().getTickets().set(emptySlot, new EmptyTicket(0, new Car("", "")));
        return new CloseTicketResponse(closeTicketRequest.getTicket().getCar());
    }

}
