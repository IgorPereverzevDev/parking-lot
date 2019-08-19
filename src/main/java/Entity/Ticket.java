package Entity;

public class Ticket {
    private int slot;
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



    public Ticket(int slot, Car car) {
        this.slot = slot;
        this.car = car;
    }

    public int getSlot() {
        return slot;
    }

}
