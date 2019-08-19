package Handler;

import Entity.Car;
import Entity.Parking;
import Orchestrator.HandlerSelector;
import Request.ParkRequest;
import Request.Request;
import Request.RequestRegistrationNumberForCarsWithColour;
import Request.RequestSlotNumberForCarsWithColour;
import Request.RequestSlotNumberForRegistrationNumber;
import Response.ResponseRegistrationNumberForCarsWithColour;
import Response.ResponseSlotNumberForCarsWithColour;
import Response.ResponseSlotNumberForRegistrationNumber;
import Storage.Context;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReportHandlerTest {


    @Test
    public void executeRegistrationNumberForCarsWithColour() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");
        Request parkRequest = new ParkRequest(car);

        MessageHandler messageHandler = new ParkingHandler();
        messageHandler.execute(parkRequest);

        messageHandler = new ReportHandler();
        Request request = new RequestRegistrationNumberForCarsWithColour(car.getColor());

        ResponseRegistrationNumberForCarsWithColour actualResponse =
                (ResponseRegistrationNumberForCarsWithColour) messageHandler.execute(request);

        assertEquals(car.getPlate(), actualResponse.getCars().get(0).getPlate());

    }


    @Test
    public void executeSlotNumberForCarsWithColour() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");
        Request parkRequest = new ParkRequest(car);

        MessageHandler messageHandler = new ParkingHandler();
        messageHandler.execute(parkRequest);

        messageHandler = new ReportHandler();
        Request request = new RequestSlotNumberForCarsWithColour(car.getColor());
        ResponseSlotNumberForCarsWithColour actualResponse =
                (ResponseSlotNumberForCarsWithColour) messageHandler.execute(request);

        assertEquals(1, actualResponse.getNumbersOfTickets().get(0).intValue());

    }

    @Test
    public void executeSlotNumberForRegistrationNumber() {
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(6));
        Context.getStorage().setParking(parking);

        Car car = new Car("KA-01-HH-1234", "White");
        Request parkRequest = new ParkRequest(car);

        MessageHandler messageHandler = new ParkingHandler();
        messageHandler.execute(parkRequest);

        messageHandler = new ReportHandler();
        Request request = new RequestSlotNumberForRegistrationNumber(car.getPlate());
        ResponseSlotNumberForRegistrationNumber actualResponse =
                (ResponseSlotNumberForRegistrationNumber) messageHandler.execute(request);

        assertEquals(1, actualResponse.getSlot());


    }

    @Test
    public void getSupportedRequest() {

        Car car = new Car("KA-01-HH-1234", "White");

        Request requestSlotNumberForRegistrationNumber = new RequestSlotNumberForRegistrationNumber(car.getPlate());
        Request requestSlotNumberForCarsWithColour = new RequestSlotNumberForCarsWithColour(car.getColor());
        Request requestRegistrationNumberForCarsWithColour = new RequestRegistrationNumberForCarsWithColour(car.getColor());

        ReportHandler reportHandlerForRegNumber =
                (ReportHandler) HandlerSelector.selectHandler(requestSlotNumberForRegistrationNumber).orElse(null);

        ReportHandler reportHandlerForCarsWithColour =
                (ReportHandler) HandlerSelector.selectHandler(requestSlotNumberForCarsWithColour).orElse(null);

        ReportHandler reportHandlerRegNumForCarsWithColour =
                (ReportHandler) HandlerSelector.selectHandler(requestRegistrationNumberForCarsWithColour).orElse(null);


        assertNotNull(reportHandlerForRegNumber);
        assertNotNull(reportHandlerForCarsWithColour);
        assertNotNull(reportHandlerRegNumForCarsWithColour);


        assertEquals(ReportHandler.class, reportHandlerForRegNumber.getClass());
        assertEquals(ReportHandler.class, reportHandlerForCarsWithColour.getClass());
        assertEquals(ReportHandler.class, reportHandlerRegNumForCarsWithColour.getClass());

    }

    @After
    public void clear(){
        List<Parking> parking = new ArrayList<>();
        parking.add(new Parking(0));
        Context.getStorage().setParking(parking);
    }
}