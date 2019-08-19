package Constant;

public class ParkingRequestCommand {

    public static final String CREATE_REQUEST = "CreateRequest";
    public static final String PARK_REQUEST = "ParkRequest";
    public static final String LEAVE_REQUEST = "LeaveRequest";
    public static final String STATUS_REQUEST = "StatusRequest";
    public static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER_REQUEST = "RequestSlotNumberForRegistrationNumber";
    public static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR_REQUEST = "RequestSlotNumberForCarsWithColour";
    public static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR_REQUEST = "RequestRegistrationNumberForCarsWithColour";
    public static final String ISSUE_TICKET = "IssueTicketRequest";
    public static final String CLOSE_TICKET = "CloseTicketRequest";

    private ParkingRequestCommand() {
    }
}
