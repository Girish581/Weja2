package com.jspiders.cardekho_case_study_hibernate.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.cardekho_case_study_hibernate.dto.CarDTO;



public class CarOperationsDAO {
	
	private static EntityManagerFactory enitityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	Scanner scanner = new Scanner(System.in);
	
	public static void openConnection() {
		
		enitityManagerFactory = Persistence.createEntityManagerFactory("cardekho");
		entityManager = enitityManagerFactory .createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	
	public static void closeConnection() {
		
		if(enitityManagerFactory  != null) {
			enitityManagerFactory.close();
		}
		if(entityManager != null) {
			entityManager.close();
		}
		if(entityManager != null) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}

		
	}
	
	public void addNewCar() {
		openConnection();
		entityTransaction.begin();
		
		System.out.println("How Many Cars Do You Want To Add");
		int choice = scanner.nextInt();
		
		for(int i = 0 ; i <choice; i++) {
			
			CarDTO car = new CarDTO();
			
			System.out.print("Enter car id:- ");
			car.setCar_id(scanner.nextInt());
			System.out.print("Enter car name:- ");
			car.setCar_name(scanner.next());
			System.out.print("Enter car brand:- ");
			car.setCar_Brand(scanner.next());
			System.out.print("Enter car fuel type:- ");
			car.setFuel_type(scanner.next());
			System.out.print("Enter car price:- ");
			car.setCar_Price(scanner.nextDouble());
			
			entityManager.persist(car);
			
		}
		
		entityTransaction.commit();
		closeConnection();
	}
	
	public void  removeCar() {
		
		openConnection();
		entityTransaction.begin();
		
		System.out.println("Enter the id of the car you want ot remove");
		int carid = scanner.nextInt();
		
		CarDTO car = entityManager.find(CarDTO.class, carid);
		entityManager.remove(car);
		
		entityTransaction.commit();
		closeConnection();
	}

	public void getCarDetails() {
	
		openConnection();
		entityTransaction.begin();
		
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car");
        List<CarDTO> cars = query.getResultList();
        
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Car ID\t"+"Car Name\t"+"Brand Name\t"+"Fuel Type\t"+"Price\t");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        
        for (CarDTO car : cars) {
            System.out.println(car.getCar_id() + "\t" + car.getCar_name() + "\t\t" + car.getCar_Brand() + "\t\t" +
                               car.getFuel_type() + "\t\t" + car.getCar_Price());
        }
        
        System.out.println("---------------------------------------------------------------------------------------------------------");
		
		entityTransaction.commit();
		closeConnection();
	}
	
	public void searchByName() {
		
		openConnection();
		entityTransaction.begin();
		
		System.out.print("Enter car name you are looking for: ");
        String carName = scanner.next();
        
        Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE car.Car_name = :carName");
        query.setParameter("carName", carName);
        
        List<CarDTO> cars = query.getResultList();
        
        if (cars.isEmpty()) {
            System.out.println("No cars found with the provided name.");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Car ID\t"+"Car Name\t"+"Brand Name\t"+"Fuel Type\t"+"Price\t");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            
            for (CarDTO car : cars) {
                System.out.println(car.getCar_id() + "\t" + car.getCar_name() + "\t\t" + car.getCar_Brand() + "\t\t" +
                                   car.getFuel_type() + "\t\t" + car.getCar_Price());
            }
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
		
		entityTransaction.commit();
		closeConnection();
	}
	
	public void searchByBrand() {
		
		openConnection();
	    entityTransaction.begin();
	    
	    System.out.print("Enter brand name of car you are looking for: ");
        String brandName = scanner.next();
        
        Query query = entityManager.createQuery(
            "SELECT car FROM CarDTO car WHERE car.Car_Brand = :brandName");
        query.setParameter("brandName", brandName);
        
        List<CarDTO> cars = query.getResultList();
        
        if (cars.isEmpty()) {
            System.out.println("No cars found with the provided brand name.");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Car ID\t"+"Car Name\t"+"Brand Name\t"+"Fuel Type\t"+"Price\t");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            
            for (CarDTO car : cars) {
                System.out.println(car.getCar_id() + "\t" + car.getCar_name() + "\t\t" + car.getCar_Brand() + "\t\t" +
                                   car.getFuel_type() + "\t\t" + car.getCar_Price());
            }
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
	    
	    entityTransaction.commit();
		closeConnection();
	}
	
	public void searchByFuelType() {
		
		openConnection();
		entityTransaction.begin();
		
		System.out.print("Enter fuel type of car you are looking for: ");
        String fuelType = scanner.next();
        
        Query query = entityManager.createQuery(
            "SELECT car FROM CarDTO car WHERE car.Fuel_type = :fuelType");
        query.setParameter("fuelType", fuelType);
        
        List<CarDTO> cars = query.getResultList();
        
        if (cars.isEmpty()) {
            System.out.println("No cars found with the provided fuel type.");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("Car ID\t"+"Car Name\t"+"Brand Name\t"+"Fuel Type\t"+"Price\t");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            
            for (CarDTO car : cars) {
                System.out.println(car.getCar_id() + "\t" + car.getCar_name() + "\t\t" + car.getCar_Brand() + "\t\t" +
                                   car.getFuel_type() + "\t\t" + car.getCar_Price());
            }
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
        
        entityTransaction.commit();
		closeConnection();
	}
	
	public void updateCar() {
		openConnection();
		entityTransaction.begin();
		
        int carId = scanner.nextInt();
        
        CarDTO car = entityManager.find(CarDTO.class, carId);
        
        if (car == null) {
            System.out.println("Car with ID " + carId + " not found.");
        } else {
            System.out.print("Enter new Car Name: ");
            String newCarName = scanner.next();
            car.setCar_name(newCarName);
            
            System.out.print("Enter new Brand Name: ");
            String newBrandName = scanner.next();
            car.setCar_Brand(newBrandName);
            
            System.out.print("Enter new Fuel Type: ");
            String newFuelType = scanner.next();
            car.setFuel_type(newFuelType);
            
            System.out.print("Enter new Price: ");
            double newPrice = scanner.nextDouble();
            car.setCar_Price(newPrice);
            
            entityManager.merge(car);
            entityTransaction.commit();
            System.out.println("Car with ID " + carId + " has been updated.");
            closeConnection();
        }
	}
}
