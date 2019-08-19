package Handler;

import Entity.Car;
import Entity.Parking;
import Entity.Ticket;
import Orchestrator.HandlerSelector;
import Request.*;
import Response.CloseTicketResponse;
import Response.IssueTicketResponse;
import Storage.Context;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketHandlerTest {


    @Test
    public void executeIssue() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");

        MessageHandler messageHandler = new TicketHandler();
        Request issueTicketRequest = new IssueTicketRequest(car);
        IssueTicketResponse issueTicketResponse = (IssueTicketResponse) messageHandler.execute(issueTicketRequest);

        assertEquals(1, issueTicketResponse.getTicket().getSlot());
        assertEquals(car.getPlate(), issueTicketResponse.getTicket().getCar().getPlate());
    }


    @Test
    public void executeClose() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = new Ticket(1, car);
        Request parkRequest = new ParkRequest(car);
        MessageHandler handler = new ParkingHandler();
        handler.execute(parkRequest);

        MessageHandler handler1 = new TicketHandler();
        Request leaveRequest = new LeaveRequest(1);
        handler.execute(leaveRequest);

        Request closeTicketRequest = new CloseTicketRequest(ticket);

        CloseTicketResponse closeTicketResponse = (CloseTicketResponse) handler1.execute(closeTicketRequest);

        assertEquals(car.getPlate(), closeTicketResponse.getCar().getPlate());

    }

    @Test
    public void getSupportedRequest() {
        Car car = new Car("KA-01-HH-1234", "White");
        Ticket ticket = new Ticket(1, car);

        Request issueTicketRequest = new IssueTicketRequest(car);
        Request closeTicketRequest = new CloseTicketRequest(ticket);

        TicketHandler issueTicketHandler = (TicketHandler) HandlerSelector.selectHandler(issueTicketRequest)
                .orElse(null);

        TicketHandler closeTicketHandler = (TicketHandler) HandlerSelector.selectHandler(closeTicketRequest)
                .orElse(null);

        assertNotNull(issueTicketHandler);
        assertNotNull(closeTicketHandler);

        assertEquals(TicketHandler.class, issueTicketHandler.getClass());
        assertEquals(TicketHandler.class, closeTicketHandler.getClass());

    }

    @After
    public void clear(){
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(0));
        Context.getStorage().setParking(parking);
    }
}