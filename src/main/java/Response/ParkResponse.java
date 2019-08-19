package Response;

import Entity.Ticket;

public final class ParkResponse extends Response {

    public ParkResponse(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private Ticket ticket;

}
