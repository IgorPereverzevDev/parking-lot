package Response;


import Entity.Ticket;

import java.util.Collection;

public class StatusResponse extends Response {

    public StatusResponse(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    private Collection<Ticket> tickets;
}
