import java.time.LocalDate;

public class Reservation {
    private String guestName;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(String guestName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + ": Room " + room.getRoomNumber() +
                " (" + room.getCategory() + "), Check-in: " + checkInDate +
                ", Check-out: " + checkOutDate;
    }
}

