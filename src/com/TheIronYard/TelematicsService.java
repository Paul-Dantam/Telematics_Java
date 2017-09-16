package com.TheIronYard;

public class TelematicsService {





    void report(VehicleInfo vehicleInfo){


        try {
            File file = new File("greetings.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Hello World!\n"); //Very simple!
            fileWriter.write("File IO is cool and not scary!");
            fileWriter.close(); //close() cleans up and commits changes
        } //If Java doesn't find the file it will create it for us
        catch (IOException ex) { //A general exception that covers many errors
            ex.printStackTrace();
        }

    }
}
