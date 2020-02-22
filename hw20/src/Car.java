package edu.umb.cs.cs681.hw20;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

public class Car {
	int price;
	int year;
	int mileage;
	private String model;
	String name;
	int dominationCount = 0;

	public Car(int price, int year, int mileage, String name, String model) {
		this.price = price;
		this.year = year;
		this.mileage = mileage;
		this.name = name;
		this.dominationCount = 0;
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getYear() {
		return year;
	}
	 public String getModel() {
		return model;} 
	
	public float getMileage() {
		return mileage;
	}

	public int compare(Car o1, Car o2) {
		return 0;
	}

	public int setDominationCount(List<Car> usedCars) {
		this.dominationCount = 0;
		for (int j = 0; j < usedCars.size(); j++) {
			Car car = usedCars.get(j);

			if ((car.mileage < this.mileage && car.price <= this.price && car.year >= this.year)
					|| (car.mileage <= this.mileage && car.price < this.price && car.year >= this.year)
					|| (car.mileage <= this.mileage && car.price <= this.price && car.year > this.year))
				this.dominationCount = this.dominationCount + 1;
		}

		return this.dominationCount;
	}

	public int getDominationCount() {
		return dominationCount;
	}

	public static void main(String[] args) {

		List<Car> usedCars = new ArrayList<Car>();
		List<Car> streamList;
		usedCars.add(new Car(50000, 2005, 150000, "edsel", "Corsair"));
		usedCars.add(new Car(40000, 2011, 130000, "pontiac", "Firebird"));
		usedCars.add(new Car(50000, 2005, 165000, "buick", "Grand National"));
		
		Integer Count = usedCars.stream()
				.map( (Car car)-> car.getModel() )
				. reduce(0, 
				    (result, carModel)-> ++ result,
				         (count, intresult) -> count);
		System.out.println("Model Count is: " + Count);
		
		System.out.println("---Sorting using Comparator by price---");

		streamList = 
				usedCars.stream()
				.parallel()
				.sorted(Comparator.comparing(Car::getPrice))
				.collect(Collectors.toList());
		streamList.forEach(e -> System.out.println("name: " + e.getName() + ", price: " + e.getPrice()));

		System.out.println("---Sorting using Comparator by year---");

		streamList = usedCars.stream()
				.parallel()
				.sorted(Comparator.comparing(Car::getYear))
				.collect(Collectors.toList());
		streamList.forEach(e -> System.out.println("name: " + e.getName() + ", price: " + e.getPrice()));

		System.out.println("---Sorting using Comparator by mileage---");

		streamList = usedCars.stream()
				.parallel()
				.sorted(Comparator.comparing(Car::getMileage))
				.collect(Collectors.toList());
		streamList.forEach(e -> System.out.println("name: " + e.getName() + ", price: " + e.getPrice()));
		
		for (int k = 0; k < usedCars.size(); k++)
			usedCars.get(k).setDominationCount(usedCars);
		
		System.out.println("---Sorting using Comparator by domination count---");
	    streamList = usedCars.stream()
	    		.parallel()
	    		.sorted(Comparator.comparing(Car::getDominationCount))
	    		.collect(Collectors.toList());
		streamList.forEach(e -> System.out.println("name: " + e.getName() + ", domination count: " + e.getDominationCount()));
		


	}

}
