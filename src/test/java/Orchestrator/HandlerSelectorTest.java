package Orchestrator;

import Entity.Car;
import Handler.ParkingHandler;
import Handler.ReportHandler;
import Handler.TicketHandler;
import Request.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandlerSelectorTest {
    @Test
    public void selectParkingHandler() {
        Car car = new Car("AAA","White");
        Request request = new ParkRequest(car);

        ParkingHandler parkingHandler = new ParkingHandler();

        ParkingHandler handler = (ParkingHandler)HandlerSelector.selectHandler(request).orElseThrow(
                ()-> new IllegalStateException(""));

        assertEquals(parkingHandler.getClass().getSimpleName(), handler.getClass().getSimpleName());
    }

    @Test
    public void selectTicketHandler() {
        Car car = new Car("AAA","White");
        IssueTicketRequest request = new IssueTicketRequest(car);

        TicketHandler parkingHandler = new TicketHandler();

        TicketHandler handler = (TicketHandler)HandlerSelector.selectHandler(request).orElseThrow(
                ()-> new IllegalStateException(""));

        assertEquals(parkingHandler.getClass().getSimpleName(), handler.getClass().getSimpleName());
    }


    @Test
    public void selectReportHandler() {
        String colour = "White";
        RequestRegistrationNumberForCarsWithColour request = new RequestRegistrationNumberForCarsWithColour(colour);

        ReportHandler parkingHandler = new ReportHandler();

        ReportHandler handler = (ReportHandler)HandlerSelector.selectHandler(request).orElseThrow(
                ()-> new IllegalStateException(""));

        assertEquals(parkingHandler.getClass().getSimpleName(), handler.getClass().getSimpleName());
    }
}