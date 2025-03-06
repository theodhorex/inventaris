package org.carvehicle;

public class Main {

	public static void main(String[] args) {
		Engine dieselEngine = new Engine("Solar", 3500);
		Transmision manualTransmision = new Transmision("Manual", 6);
		Tire trailTire = new Tire("Trail", 309, 70, 20);
		Wheel truckWheel = new Wheel(8, 18);
		Truck truckHino = new Truck(dieselEngine, manualTransmision, trailTire, truckWheel, 4500);
		truckHino.setVehicleName("HINO DUTRO CARGO 110 SD");

		System.out.println("Nama kendaraan : "+ truckHino.getVehicleName());
		System.out.println("CC mesin: "+ truckHino.getEngine().getCapacity());
		System.out.println("Tipe transmisi: "+ truckHino.getTransmision().getTransType());
		System.out.println("Tipe Ban : "+ truckHino.getTire().getTireType());
		System.out.println("Diameter Roda : R"+ truckHino.getWheel().getDiameter());
	}
}
