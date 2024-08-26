import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Initialize some rooms
        hotel.addRoom(new Room(101, "Single", 100.00));
        hotel.addRoom(new Room(102, "Double", 150.00));
        hotel.addRoom(new Room(103, "Suite", 300.00));

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single, Double, Suite): ");
                    String category = scanner.next();
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    LocalDate checkInDate = LocalDate.parse(scanner.next());
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    LocalDate checkOutDate = LocalDate.parse(scanner.next());
                    List<Room> availableRooms = hotel.searchRooms(category, checkInDate, checkOutDate);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms found.");
                    } else {
                        System.out.println("Available rooms:");
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    checkInDate = LocalDate.parse(scanner.next());
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    checkOutDate = LocalDate.parse(scanner.next());
                    Room roomToReserve = null;
                    for (Room room : hotel.getRooms()) {
                        if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                            roomToReserve = room;
                            break;
                        }
                    }
                    if (roomToReserve != null) {
                        double totalCost = roomToReserve.getPricePerNight() * (checkOutDate.toEpochDay() - checkInDate.toEpochDay());
                        if (paymentProcessor.processPayment(totalCost)) {
                            hotel.makeReservation(guestName, roomToReserve, checkInDate, checkOutDate);
                        } else {
                            System.out.println("Payment failed. Reservation not completed.");
                        }
                    } else {
                        System.out.println("Room not available or invalid room number.");
                    }
                    break;
                case 3:
                    List<Reservation> reservations = hotel.getReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        System.out.println("Reservations:");
                        for (Reservation reservation : reservations) {
                            System.out.println(reservation);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
