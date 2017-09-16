package com.TheIronYard;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;


public class TelematicsService {





    void report(VehicleInfo vehicleInfo){

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(vehicleInfo);


            try {
                File file = new File(Integer.toString(vehicleInfo.getVIN()) + ".json");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("Hello World!\n"); //Very simple!
                fileWriter.write("File IO is cool and not scary!");
                fileWriter.close(); //close() cleans up and commits changes
            } //If Java doesn't find the file it will create it for us
            catch (IOException ex) { //A general exception that covers many errors
                ex.printStackTrace();
            }
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

    }
}
