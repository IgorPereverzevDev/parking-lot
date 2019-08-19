package Request;

import Entity.Ticket;

public class CloseTicketRequest extends Request {

    public CloseTicketRequest(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private Ticket ticket;
}
