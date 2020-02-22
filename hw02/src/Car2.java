package edu.umb.cs.cs681.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Car2 {
	int price;
	int year;
	int mileage;
	String name;
	int dominationCount = 0;

	public Car2(int price, int year, int mileage, String name) {
		this.price = price;
		this.year = year;
		this.mileage = mileage;
		this.name = name;
		this.dominationCount = 0;
	}

	private static Integer minPrice(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer minPrice;
		return minPrice = cars.stream().map((Car2 car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice < result)
				return carPrice;
			else
				return result;
		});
	}

	private static Integer maxPrice(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer maxPrice;
		return maxPrice = cars.stream().map((Car2 car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice > result)
				return carPrice;
			else
				return result;
		});
	}

	private static Integer minYear(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer minYear;
		return minYear = cars.stream().map((Car2 car) -> car.getYear()).reduce(0, (result, carYear) -> {
			if (result == 0)
				return carYear;
			else if (carYear < result)
				return carYear;
			else
				return result;
		});
	}

	// ollections.sort(usedCars, Comparator.comparing((Car2 car)->car.getYear()));
	// or (Car2 item : usedCars) System.out.println(item.getName());

	private static Integer maxYear(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer maxYear;
		return maxYear = cars.stream().map((Car2 car) -> car.getYear()).reduce(0, (result, carYear) -> {
			if (result == 0)
				return carYear;
			else if (carYear > result)
				return carYear;
			else
				return result;
		});
	}

	private static Integer minMileage(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer minMileage;
		return minMileage = cars.stream().map((Car2 car) -> car.getMileage()).reduce(0, (result, carMileage) -> {
			if (result == 0)
				return carMileage;
			else if (carMileage < result)
				return carMileage;
			else
				return result;
		});
	}

	private static Integer maxMileage(ArrayList<Car2> cars) {// done
		// TODO Auto-generated method stub
		Integer maxMileage;
		return maxMileage = cars.stream().map((Car2 car) -> car.getMileage()).reduce(0, (result, carMileage) -> {
			if (result == 0)
				return carMileage;
			else if (carMileage < result)
				return carMileage;
			else
				return result;
		});
	}

	private static Integer minDomination(ArrayList<Car2> cars) { // TODO
		Integer minDom = null;
		return minDom = cars.stream().map((Car2 car) -> car.setDominationCount(cars)).reduce(0, (result, domCount) -> {
			if (result == 0)
				return domCount;
			else if (domCount < result)
				return domCount;
			else
				return result;
		});

	}

	private static Integer maxDomination(ArrayList<Car2> cars) { // TODO
		Integer maxDom = null;
		return maxDom = cars.stream().map((Car2 car) -> car.setDominationCount(cars)).reduce(0, (result, domCount) -> {
			if (result == 0)
				return domCount;
			else if (domCount > result)
				return domCount;
			else
				return result;
		});

	}
	// }

	// .reduce(0, (result, carCount) -> carCount.setDominationCount()
	// ); }

	/*
	 * private static Integer maxDomination(ArrayList<Car2> cars) { // TODO
	 * Auto-generated method stub Integer minPrice; return minPrice = cars.stream()
	 * .map((Car2 car)-> car.getPrice()) .reduce(0, (result, carPrice)-> {
	 * if(result==0) return carPrice; else if(carPrice < result) return carPrice;
	 * else return result;} ); }
	 */
	private static Long count(ArrayList<Car2> cars) {
		// TODO Auto-generated method stub
		Long count;
		return count = cars.stream().map((Car2 car) -> car.getName()).count();
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

	public int getMileage() {
		return mileage;
	}

	/*
	 * public int compare(Car2 o1, Car2 o2) { return 0; }
	 */
	public int getDominationCount() {
		return dominationCount;
	}

	public int setDominationCount(ArrayList<Car2> List) {
		this.dominationCount = 0;
		for (int j = 0; j < List.size(); j++) {
			Car2 car = List.get(j);

			if ((car.mileage > this.mileage && car.price >= this.price && car.year <= this.year)
					|| (car.mileage >= this.mileage && car.price > this.price && car.year <= this.year)
					|| (car.mileage >= this.mileage && car.price >= this.price && car.year < this.year))
				this.dominationCount = this.dominationCount + 1;
		}

		return this.dominationCount;
	}

	public static void main(String[] args) {

		ArrayList<Car2> usedCars = new ArrayList<Car2>();

		usedCars.add(new Car2(50000, 2005, 150000, "edsel"));
		usedCars.add(new Car2(40000, 2011, 130000, "pontiac"));
		usedCars.add(new Car2(30000, 2005, 165000, "buick"));

		
		System.out.println("Lowest price:" + minPrice(usedCars));
		System.out.println("Highest price:" + maxPrice(usedCars));
		System.out.println("Lowest year:" + minYear(usedCars));
		System.out.println("Highest year:" + maxYear(usedCars));
		System.out.println("Lowest mileage:" + minMileage(usedCars));
		System.out.println("Highest mileage:" + maxMileage(usedCars));
		System.out.println("Lowest domination:" + minDomination(usedCars));
		System.out.println("Highest domination:" + maxDomination(usedCars));
		System.out.println("Count:" + count(usedCars));
		/*
		 * System.out.println("Sorted by price:"); Collections.sort(usedCars,
		 * Comparator.comparing((Car2 car)->car.getPrice())); for (Car2 item : usedCars)
		 * System.out.println(item.getName());
		 * 
		 * System.out.println(); System.out.println("Sorted by year:");
		 * Collections.sort(usedCars, Comparator.comparing((Car2 car)->car.getYear()));
		 * for (Car2 item : usedCars) System.out.println(item.getName());
		 * 
		 * System.out.println(); System.out.println("Sorted by mileage:");
		 * Collections.sort(usedCars, Comparator.comparing((Car2
		 * car)->car.getMileage())); for (Car2 item : usedCars)
		 * System.out.println(item.getName());
		 * 
		 * System.out.println(); System.out.println("Sorted by Pareto comparison:");
		 * Collections.sort(usedCars, Comparator.comparing((Car2
		 * car)->car.getDominationCount())); for (Car2 item : usedCars)
		 * System.out.println(item.getName()); for (int k = 0; k < usedCars.size(); k++)
		 * usedCars.get(k).setDominationCount(usedCars);
		 * 
		 * Collections.sort(usedCars, Comparator.comparing((Car2
		 * car)->car.getDominationCount()));
		 * 
		 * for (Car2 item : usedCars) System.out.println(item.getName());
		 * 
		 */
	}

}
