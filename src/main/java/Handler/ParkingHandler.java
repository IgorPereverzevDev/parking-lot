package Handler;

import Constant.*;
import Entity.Car;
import Entity.Parking;
import Entity.Ticket;
import Orchestrator.HandlerSelector;
import Request.*;
import Response.*;
import Storage.Context;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingHandler implements MessageHandler {

    @Override
    public Response execute(Request request) {
        Response response;
        switch (request.getClass().getSimpleName()) {
            case ParkingRequestCommand.CREATE_REQUEST:
                response = create((CreateRequest) request);
                break;
            case ParkingRequestCommand.LEAVE_REQUEST:
                response = leave((LeaveRequest) request);
                break;
            case ParkingRequestCommand.PARK_REQUEST:
                response = park((ParkRequest) request);
                break;
            case ParkingRequestCommand.STATUS_REQUEST:
                response = getStatus();
                break;
            default:
                throw new IllegalStateException("Unexpected request: " + request.getClass().getSimpleName());
        }
        return response;
    }

    @Override
    public Collection<String> getSupportedRequest() {
        return new HashSet<>(Arrays.asList(CreateRequest.class.getSimpleName(),
                LeaveRequest.class.getSimpleName(), ParkRequest.class.getSimpleName(),
                StatusRequest.class.getSimpleName()));
    }

    private Response create(CreateRequest createRequest) {
        List<Parking> parkingList = Context.getStorage().getParking();
        if (parkingList.isEmpty()) {
            Parking parking = new Parking(createRequest.getParkSlots());
            parkingList.add(parking);
            return new CreateResponse(createRequest.getParkSlots());
        }
        return new NullResponse("parking already exist");
    }


    private Response park(ParkRequest parkRequest) {
        if (!checkParkingBeforePark(parkRequest.getCar())) {
            return new NullResponse("Sorry, parking lot is full");
        }
        IssueTicketRequest ticketRequest = new IssueTicketRequest(parkRequest.getCar());
        MessageHandler ticketHandler = HandlerSelector.selectHandler(ticketRequest).orElseThrow(() ->
                new IllegalStateException("Unsupported request"));
        IssueTicketResponse issueTicketResponse = (IssueTicketResponse) ticketHandler.execute(ticketRequest);
        Context.getStorage().getCars().add(parkRequest.getCar());
        return new ParkResponse(issueTicketResponse.getTicket());
    }

    private Response leave(LeaveRequest leaveRequest) {
        if (checkParkingBeforeLeave(leaveRequest.getSlot())) {
            return new NullResponse("impossible to leave the Car");
        }
        List<Ticket> tickets = Context.getStorage().getTickets();
        Ticket ticket = tickets.stream().filter(t -> t.getSlot() == leaveRequest.getSlot()).findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported request"));
        CloseTicketRequest ticketRequest = new CloseTicketRequest(ticket);
        MessageHandler ticketHandler = HandlerSelector.selectHandler(ticketRequest).orElseThrow(() ->
                new IllegalStateException("Unsupported request"));
        CloseTicketResponse closeTicketResponse = (CloseTicketResponse) ticketHandler.execute(ticketRequest);
        Context.getStorage().getCars().remove(closeTicketResponse.getCar());
        return new LeaveResponse(ticketRequest.getTicket().getSlot());
    }

    private Response getStatus() {
        List<Ticket> leaveSlot = Context.getStorage().getTickets();
        return new StatusResponse(leaveSlot);
    }

    private boolean checkParkingBeforeLeave(Integer slot) {
        List<Ticket> tickets = Context.getStorage().getTickets();
        List<Ticket> availableSlot = tickets.stream().filter(ticket -> ticket.getSlot() == slot).collect(Collectors.toList());
        return availableSlot.isEmpty();
    }


    private boolean checkParkingBeforePark(Car car) {
        List<Parking> parking = Context.getStorage().getParking();
        List<Car> cars = Context.getStorage().getCars();
        int slots = !parking.isEmpty() ? parking.iterator().next().getNumberSlots() : 0;
        boolean carExist = cars.stream().anyMatch(c -> c.getPlate().equals(car.getPlate()));
        return !carExist && slots > cars.size();
    }
}
