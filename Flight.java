package datastructures.dccc.edu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class Flight {

    enum FlightType {
        Arrival, Departure
    };

    enum OperationStatus {
        Scheduled, CancelDueCrash, CancelDuePassengerDisturbance, CancelDueDrunkPilot, CancelDueMaintenance, CancelNoPlane, Queued, NavigationError;

        public static Object getRandomStatus() {
            Random random = new Random();
            return Flight.OperationStatus.values()[random.nextInt(Flight.OperationStatus.values().length)];
        }
    }

    public void setOperationStatus(OperationStatus status) {
        operationStatus = status;
    }
    //  Scheduled, ArrivalCancelDueCrash, CancelDuePassengerDisturbance, CancelDueDrunkPilot, CancelDueMaintenance, CancelNoPlane, Queued;
    public void setOperationStatus(String type) {
        switch(type) {
            case "Scheduled":
                operationStatus = operationStatus.Scheduled;
                break;
            case "ArrivalCancelDueCrash":
                operationStatus = operationStatus.CancelDueCrash;
                break;
            case "ArrivalNavigationError":
                operationStatus = operationStatus.NavigationError;
                break;
        }
    }

    String flightNumber;
    String aircraftNumber;
    String destinationOrigin;
    Date schedule;
    FlightType flightType;
    //  Default is scheduled
    OperationStatus operationStatus = OperationStatus.Scheduled;

    // Constructor
    public Flight(String flightNumber, String aircraftNumber, String destinationOrigin, Date schedule, FlightType flightType) {
        this.flightNumber = flightNumber;
        this.aircraftNumber = aircraftNumber;
        this.destinationOrigin = destinationOrigin;
        this.schedule = schedule;
        this.flightType = flightType;
    }

    // Constructor
    public Flight() {
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return "FlightType:" + flightType +  " FlightNumber: " + flightNumber + " Aircraft Number: " + aircraftNumber + " Schedule: " + formatter.format(schedule)
                + " OpStatus: " + operationStatus;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAircraftNumber(String aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public void setDestinationOrigin(String destinationOrOrigin) {
        this.destinationOrigin = destinationOrOrigin;
    }

    public void setSchedule(String scheduleString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date schedule = null;
        try {
            schedule = formatter.parse(scheduleString);
        }
        catch (ParseException e)
        {
            System.out.println("Error parsing date: " + scheduleString);
        }
        this.schedule = schedule;
    }

    public void setFlightType(String type) {
        switch(type) {
            case "Arrival":
                flightType = FlightType.Arrival;
                break;
            case "Departure":
                flightType = FlightType.Departure;
                break;
        }
    }
}