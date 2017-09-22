package com.TheIronYard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        VehicleInfo newVehicle = new VehicleInfo();

        System.out.println("Enter your Vehicles VIN");
        newVehicle.setVIN(sc.nextInt());

        System.out.println("Enter your odometer reading");
        newVehicle.setOdometer(sc.nextDouble());

        System.out.println("Enter your fuel consumption");
        newVehicle.setConsumption(sc.nextDouble());

        System.out.println("Enter your odometer reading at time of last oil change");
        newVehicle.setOdometerAtLastService(sc.nextDouble());

        System.out.println("Enter your engine displacement");
        newVehicle.setEngineDisplacement(sc.nextDouble());

        TelematicsService telematicsService = new TelematicsService();

        telematicsService.report(newVehicle);
    }
}



