package com.TheIronYard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelematicsService {

    public static String tableContents;
    public static ArrayList<String> rowCollection;

    public static ArrayList<String> getRowCollection() {
        if (rowCollection == null) {
            return new ArrayList<>();
        }
        return rowCollection;
    }

    void report(VehicleInfo vehicleInfo){
        tableContents = "";
        if (rowCollection == null) {
            rowCollection = new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(vehicleInfo);
            try {
                File outputFile = new File(Integer.toString(vehicleInfo.getVIN()) + ".json");
                FileWriter fileWriter = new FileWriter(outputFile);
                fileWriter.write(json);
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

        List<VehicleInfo> myGarage = new ArrayList<>();

        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                try{ Scanner fileScanner = new Scanner(f);
                     String json = fileScanner.nextLine();
                    try{
                        ObjectMapper readMapper = new ObjectMapper();
                        VehicleInfo vi = readMapper.readValue(json, VehicleInfo.class);
                        myGarage.add(vi);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        int odometerTotal = 0;
        int consumptionTotal = 0;
        int lastServiceTotal = 0;
        int engineDisplacementTotal = 0;

        for (VehicleInfo vehicle : myGarage) {

            odometerTotal += vehicle.getOdometer();
            consumptionTotal += vehicle.getConsumption();
            lastServiceTotal += vehicle.getOdometerAtLastService();
            engineDisplacementTotal += vehicle.getEngineDisplacement();

            String rowItem;
            rowItem = String.format("<tr>\n" +
                    "        <td align=\"center\">%s</td><td align=\"center\">%s</td><td align=\"center\">%s</td><td align=\"center\">%s</td><td align=\"center\">%s</td>\n" +
                    "    </tr>", vehicle.getVIN(), vehicle.getOdometer(), vehicle.getConsumption(), vehicle.getOdometerAtLastService(), vehicle.getEngineDisplacement());
            rowCollection.add(rowItem);
        }

        for(String row: rowCollection){
            tableContents += row;
        }

        String dashboardhtml = "<html>\n" +
                "<title>Vehicle Telematics Dashboard</title>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for "+ rowCollection.size() +" vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">"+ (odometerTotal/rowCollection.size()) +"</td><td align=\"center\">"+ (consumptionTotal/rowCollection.size()) +"</td><td align=\"center\">"+ (lastServiceTotal/rowCollection.size()) +"</td><td align=\"center\">"+ (engineDisplacementTotal/rowCollection.size()) +"</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<h1 align=\"center\">History</h1>\n" +
                "<table align=\"center\" border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" + tableContents +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">45435</td><td align=\"center\">123</td><td align=\"center\">234</td><td align=\"center\">345</td><td align=\"center\">4.5</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";

        File dashboard = new File("dashboard.html");
        try( FileWriter dashboardWriter = new FileWriter(dashboard)) {
            dashboardWriter.write(dashboardhtml);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
