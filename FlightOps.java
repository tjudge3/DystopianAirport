package datastructures.dccc.edu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Stack;

public class FlightOps {
    LinkedList<Flight> flights = new LinkedList<>();
    private void printFlights()
    {
        for(Flight flights : flights)
        {
            System.out.println(flights.toString());
        }
    }

    private void removeCancelledFlights() {
        Stack removeStack = new Stack();
        for (Flight flights: flights)
        {
            if (flights.operationStatus == Flight.OperationStatus.CancelDueCrash || flights.operationStatus == Flight.OperationStatus.CancelDueDrunkPilot
|| flights.operationStatus == Flight.OperationStatus.CancelDueMaintenance || flights.operationStatus == Flight.OperationStatus.CancelDuePassengerDisturbance
|| flights.operationStatus == Flight.OperationStatus.NavigationError || flights.operationStatus == Flight.OperationStatus.CancelNoPlane) {
                removeStack.push(flt);
            }
        }
        while (!removeStack.isEmpty()) {
            flights.remove(removeStack.pop());
        }
    }
    
    private void changeStatuses() {
        for (Flight flights : flights) {
            Flight.OperationStatus status = (Flight.OperationStatus) Flight.OperationStatus.getRandomStatus();
            if (flt.flightType == Flight.FlightType.Arrival) {
                if (status == Flight.OperationStatus.CancelDueCrash || status == Flight.OperationStatus.NavigationError  || status == Flight.OperationStatus.Scheduled || status == Flight.OperationStatus.Queued) {
                    flights.setOperationStatus(status);
                }
            } else if ((flights.flightType == Flight.FlightType.Arrival && status == Flight.OperationStatus.CancelDueMaintenance)
                    || (flights.flightType == Flight.FlightType.Departure && status == Flight.OperationStatus.NavigationError)) {
                continue;
            } else {
                flights.setOperationStatus(status);
            }
        }
    }
    private void moveQueuedFlights() {
        Stack moveStack = new Stack();
        for (Flight flights : flightss)
        {
            if (flights.operationStatus == Flight.OperationStatus.Queued) {
                moveStack.push(flights);
            }
        }
        while (!moveStack.isEmpty()) {
            Flight flight = (flights) moveStack.peek();
            flights.remove(moveStack.pop());
            flights.addLast(flight);
        }
    }

    private void presidentAndCroniesJumpTheQueue() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
        try {
            Date date1 = sdf.parse("10/15/20 07:30");
            Date date2 = sdf.parse("10/15/20 07:30");
            Date date3 = sdf.parse("10/15/20 07:30");
            Flight vipFlight1 = new Flight("Vip001", "AF-01", "CDG", date1, Flight.FlightType.Departure);
            Flight vipFlight2 = new Flight("Vip002", "AF-01", "CDG", date2, Flight.FlightType.Departure);
            Flight vipFlight3 = new Flight("Vip003", "AF-01", "CDG", date3, Flight.FlightType.Arrival);

            flights.addFirst(vipFlight1);
            flights.addFirst(vipFlight2);
            flights.addFirst(vipFlight3);
        }
        catch (ParseException e)
        {
            System.out.println("Date Parse Exception");
        }
    }

    public void doSimuluation(String filePath) {

        flights = initializeFlightList(filePath);
        changeStatuses();
        System.out.println("Changed statuses");
        printFlights();
        System.out.println("Remove cancelled flights");
        removeCancelledFlights();
        printFlights();
        presidentAndCroniesJumpTheQueue();
        System.out.println("Cronies jump queue");
        printFlights();
        moveQueuedFlights();
        System.out.println("Moved queued flights");
        printFlights();
    }

    public static void main(String[] args) {
        FlightOps flightOPs = new FlightOps();
        String filePath = "./resources/Flights.csv";
        flightOPs.doSimuluation(filePath);
    }
    public LinkedList initializeFlightList(String filePath) {
        ReadCSVWithScanner csvReader = new ReadCSVWithScanner();
        LinkedList flightList = csvReader.getFlightListFromCSV(filePath);
        return flightList;
    }

}
