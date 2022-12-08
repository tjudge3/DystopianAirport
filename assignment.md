
You are an airport operations software engineer. Part of your job is to write a program that manages a computerized list of departures and arrivals to/from your small impoverished island's single busy airport.  

Because of the condition of many of the airplanes in your country's airline, over-worked and underpaid mechanics, and parts shortages,  airline crashes are very frequent, sometimes several per day. 

When an airplane crashes, naturally, you remove that aircraft from your flight schedule, which conveniently opens spots in your list for other aircraft, which are frequently standing by, waiting for a slot to open up.    Additionally, since the island is small,  arriving pilots often make navigation errors and land on the wrong island.   This also opens up slots in your list. 

Additionally, social cohesion is poor in your country, and flights are frequently canceled because of belligerent passengers and fistfights in the aisles of aircraft.   These aircraft are also pulled from the list at the last minute.  Mechanical problems,  drunk pilots,  missing, stolen or repossessed planes, strikes, and other operational challenges cause you and your software untold headaches and flight cancellations.  

If that weren't enough, the corrupt president of your country is very moody and travels to and from your airport for last-minute shopping junkets, trysts with his many mistresses,  and "diplomatic" excursions, which often include bags of money.  His flights are almost always unscheduled and because he’s the president, his plane jumps the line of scheduled departures and arrivals.   The president’s top cronies and generals have similar privileges and frequently also jump the queue on their junkets into and out of the country on private or military planes.

As a well-trained software engineer, however, you have had the foresight of using a linked list for your flight schedules.  Link lists, luckily for you, are very efficient for performing frequent delete and insert operations on a list, and adding items to the front or end of the list.   

If you had instead used a regular list like an ArrayList, (which would require complex shifting of items in your list when doing these types of operations), your country might be even more chaotic! 

You deserve a raise for your good thinking, but unfortunately, you have not been paid for several months because of corruption and mismanagement of the airport budget.  

 * * * * * * * 

Here is a class diagram showing the attributes and operations of your system.   Following the diagram, is a table describing the purpose of the operations listed.  You should understand the class diagram and operations descriptions before starting to code. 

LinkedListAircraftOps.JPG

 

Class/Enum	Method Name	Description
FlightOps	initializeFlightList	Reads in a sample flight list into a LinkedList<Flight> from a CSV file. 
ReadCSVWithScanner	
LinkedList<Flight>
getFlightListFromCSV(String filePath)
Reads in a sample flight list into a LinkedList<Flight> from a CSV file. 
FlightOps	void doSimuluation()

 	Driver method for airport operations simulation - initializes the LinkedList,  modifies statuses randomly, and then processes the status changes, modifying the LinkedList as necessary
FlightsOps	
void changeStatuses() 
Changes statuses from default Scheduled to random  values
FlightOps	
void removeCancelledFlights() 
Removes flights with statuses causes cancellation of flight
FlightOps	void printFlights()	Prints a list of flights in the LinkedList
FlightOps	
void moveQueuedFlights() {

Flights with status Queued should be moved to the end of the LinkedList 
FlightOps	
void presidentAndCroniesJumpTheQueue()
 

President and cronies jump to the front of the list on demand. 
 

Sample of Linked List Methods you will likely find useful. 
Use as many of these as you can:    
addLast();
addFirst();
add(Flight e)
indexOf(Object o)
getFirst()
getLast();
peek();
peekLast();
poll() or remove()
pollLast();
peekLast();
add(index, element)

 Preliminary Instructions:  
 Download and extract the provided stubbed out project zip file Download  Download and extract the provided stubbed out project zip fileinto your IdeaProjects home directory.  Open the project.   This project will compile but is incomplete.  Your job will be to correct it.     
Note that a provided Flights.csv (Comma-Delimited-Value) file is located in the resources directory under your src directory in IntelliJ.  Take a look at this file.  
  There is also a file called Employees.csv in this directory, which is provided so you can practice using an external file in a program.    ReadCSVFromScannerEmployee has a main method and you can run ReadCSVFromScannerEmployee from your project.    Just open this source file and select the second Run menu and you will be able to pick which Runnable to execute.  This will load and print out the data in the Employees.csv file.  See the Tutorial on CSV files in Unit 9 so that you understand how to use CSV files.  If this works correctly, you should not have trouble with the Flights.csv file.  
Examine the Flight class in the project.   
Modify the class ReadCSVWithScanner patterning your code from the ReadCSVFromScannerEmployee class.  Your modified class should be able to read and process the Flights.CSV file without errors.  Test it successfully before proceeding.  
 Modify the doSimulation() method in the provided FlightOps class file where indicated: 

Most of the code for this assignment is already provided for you.  Several methods from the FlightOps class called below are stubbed out in the project file provided and will require you to supply needed code where indicated.   You will need to complete the printFlights() method, and add the appropriate  LinkedList methods addFirst(), addLast(), add(), and remove(),  where appropriate. 

 You are welcome to add features or other operations to make this project your own.    A discussion of the doSimulation method follows below. 

public void doSimuluation(String filePath) {
    flts = initializeFlightList(filePath);
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

public LinkedList initializeFlightList(String filePath) {
    // Load flights from external file
    ReadCSVWithScanner csvReader = new ReadCSVWithScanner();
    LinkedList fltList = csvReader.getFlightListFromCSV(filePath);
    return fltList;
}

public LinkedList initializeFlightList(String filePath) {
// Load flights from external file
   ReadCSVWithScanner csvReader = new ReadCSVWithScanner();
   LinkedList fltList = csvReader.getFlightListFromCSV(filePath);
   return fltList;
}


public static void main(String[] args) {
   FlightOps fltOPs = new FlightOps();
   String filePath = "./resources/Flights.csv";
   fltOPs.doSimuluation(filePath);
}
What happens in DoSimulation()

The method initializeFlightList() builds a LinkedList of flights with a default status of Scheduled.
The method changeStatuses() is then called to set the statuses of the status to a random status to make our operations more interesting. Certain statuses make sense only for arrivals or departures.  Examine the setOperationStatus() and understand how it works.  
OperationStatuses that will result in a flight cancellation are identified and those flights are removed from the list.  The operation uses the remove() method.
 The president and his cronies can jump the queue, ThreeVIP flights are created and added to the list.  You are welcome to add more.  
Finally, flights with the status of Queued are moved to the bottom of the LinkedList using the addLast method.  Note that we cannot simply use for-next loops to remove or move items within the loop, since that would affect the the list that we are looping through and create a concurrency exception. 
We, therefore, use a Stack to keep track of items to be removed or moved while going through the for loop, then in a separate loop popping them off the stack to do the remove operation. 
 Attached is a sample output file of operational status changes from the program: DystopianAirportSampleOutput.pdf 
Your results will vary slightly because of randomization.  
