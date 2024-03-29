package datastructures.dccc.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadCSVWithScanner
{

    public LinkedList getFlightListFromCSV(String filePath)
    {
        LinkedList<Flight> fltList = new LinkedList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            Scanner scanner;
            int index = 0;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null)
            {
                Flight flt = new Flight();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                if (! firstLine)

                    while (scanner.hasNext())
                    {
                        String data = scanner.next();
                        switch(index)
                        {
                            case 0 : flt.setFlightNumber(data); break;
                            case 1 : flt.setAircraftNumber(data); break;
                            case 2 : flt.setDestinationOrigin(data); break;
                            case 3 : flt.setFlightType(data); break;
                            case 4 : flt.setSchedule(data); break;
                            case 5 : flt.setOperationStatus(data); break;
                            default: System.out.println("invalid data::" + data);
                        }
                        index++;
                    }

                index = 0;
                if (!firstLine)
                {
                    fltList.add(flt);
                }

                firstLine = false;
            }
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("File not found");
        }
        return fltList;
    }

}
