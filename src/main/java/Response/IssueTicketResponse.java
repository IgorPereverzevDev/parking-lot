package Response;

import Entity.Ticket;

public final class IssueTicketResponse extends Response {

    public Ticket getTicket() {
        return ticket;
    }

    private Ticket ticket;

    public IssueTicketResponse(Ticket openTicket) {
       this.ticket = openTicket;
    }
}
