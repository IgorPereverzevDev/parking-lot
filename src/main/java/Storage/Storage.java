package Storage;

import Entity.Car;
import Entity.Parking;
import Entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<Ticket> tickets;
    private List<Parking> parking;
    private List<Car> cars;


    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setParking(List<Parking> parking) {
        this.parking = parking;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Storage() {
        parking = new ArrayList<>();
        cars = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Parking> getParking() {
        return parking;
    }

    public List<Car> getCars() {
        return cars;
    }


}
