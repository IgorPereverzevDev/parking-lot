package Handler;

import Entity.Car;
import Entity.Parking;
import Entity.Ticket;
import Orchestrator.HandlerSelector;
import Request.*;
import Response.CreateResponse;
import Response.LeaveResponse;
import Response.ParkResponse;
import Response.StatusResponse;
import Storage.Context;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingHandlerTest {

    @Test
    public void executeCreate() {
        List<Parking> parking = new ArrayList<>();
        Context.getStorage().setParking(parking);
        Request request = new CreateRequest(6);
        MessageHandler messageHandler = new ParkingHandler();
        CreateResponse actualResponse = (CreateResponse) messageHandler.execute(request);
        assertEquals(6, actualResponse.getSlots());
    }

    @Test
    public void executePark() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");
        Request parkRequest = new ParkRequest(car);
        Ticket ticket = new Ticket(1, car);

        MessageHandler messageHandler = new ParkingHandler();
        ParkResponse actualResponse = (ParkResponse) messageHandler.execute(parkRequest);

        assertEquals(ticket.getSlot(), actualResponse.getTicket().getSlot());
    }

    @Test
    public void executeLeave() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);
        Car car = new Car("KA-01-HH-1234", "White");

        Request request = new ParkRequest(car);
        MessageHandler messageHandler = new ParkingHandler();
        messageHandler.execute(request);

        Request leaveRequest = new LeaveRequest(1);
        LeaveResponse expectResponse = new LeaveResponse(1);

        LeaveResponse actualResponse = (LeaveResponse) messageHandler.execute(leaveRequest);
        assertEquals(expectResponse.getSlot(), actualResponse.getSlot());
    }


    @Test
    public void executeStatus() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);
        Car car = new Car("KA-01-HH-1234", "White");

        Request request = new ParkRequest(car);
        Ticket ticket = new Ticket(1, car);

        MessageHandler messageHandler = new ParkingHandler();
        messageHandler.execute(request);

        Request statusRequest = new StatusRequest();

        StatusResponse actualResponse = (StatusResponse) messageHandler.execute(statusRequest);

        assertEquals(actualResponse.getTickets().iterator().next().getSlot(), ticket.getSlot());
        assertEquals(actualResponse.getTickets().iterator().next().getCar().getColor(), ticket.getCar().getColor());

    }

    @Test
    public void getSupportedRequest() {
        Car car = new Car("KA-01-HH-1234", "White");

        Request createRequest = new CreateRequest(6);
        Request parkRequest = new ParkRequest(car);
        Request statusRequest = new StatusRequest();
        Request leaveRequest = new LeaveRequest(1);

        ParkingHandler parkingHandlerCreate = (ParkingHandler) HandlerSelector.selectHandler(createRequest)
                .orElse(null);

        ParkingHandler parkingHandlerPark = (ParkingHandler) HandlerSelector.selectHandler(parkRequest)
                .orElse(null);

        ParkingHandler parkingHandlerStatus = (ParkingHandler) HandlerSelector.selectHandler(statusRequest)
                .orElse(null);

        ParkingHandler parkingHandlerLeave = (ParkingHandler) HandlerSelector.selectHandler(leaveRequest)
                .orElse(null);


        assertNotNull(parkingHandlerCreate);
        assertNotNull(parkingHandlerPark);
        assertNotNull(parkingHandlerStatus);
        assertNotNull(parkingHandlerLeave);


        assertEquals(ParkingHandler.class, parkingHandlerCreate.getClass());
        assertEquals(ParkingHandler.class, parkingHandlerPark.getClass());
        assertEquals(ParkingHandler.class, parkingHandlerStatus.getClass());
        assertEquals(ParkingHandler.class, parkingHandlerLeave.getClass());


    }

    @After
    public void clear(){
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(0));
        Context.getStorage().setParking(parking);
    }

}
