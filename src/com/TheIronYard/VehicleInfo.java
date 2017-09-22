package com.TheIronYard;

public class VehicleInfo{

    private int VIN;
    private double odometer;
    private double consumption;
    private double odometerAtLastService;
    private double engineDisplacement;

        public VehicleInfo() {}

        public int getVIN() {
            return VIN;
        }

        public void setVIN(int VIN) {
            this.VIN = VIN;
        }

        public double getOdometer() {
            return odometer;
        }

        public void setOdometer(double odometer) {
            this.odometer = odometer;
        }

        public double getConsumption() {
            return consumption;
        }

        public void setConsumption(double consumption) {
            this.consumption = consumption;
        }

        public double getOdometerAtLastService() {
            return odometerAtLastService;
        }

        public void setOdometerAtLastService(double odometerAtLastService) {
            this.odometerAtLastService = odometerAtLastService;
        }

        public double getEngineDisplacement() {
            return engineDisplacement;
        }

        public void setEngineDisplacement(double engineDisplacement) {
            this.engineDisplacement = engineDisplacement;
        }
}
