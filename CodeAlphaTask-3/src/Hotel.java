import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Room> searchRooms(String category, LocalDate checkInDate, LocalDate checkOutDate) {
        return rooms.stream()
                .filter(room -> room.getCategory().equalsIgnoreCase(category) && room.isAvailable())
                .collect(Collectors.toList());
    }

    public void makeReservation(String guestName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        room.setAvailable(false);
        Reservation reservation = new Reservation(guestName, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        System.out.println("Reservation made successfully!");
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
