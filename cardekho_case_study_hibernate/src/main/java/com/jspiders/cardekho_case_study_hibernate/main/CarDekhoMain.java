package com.jspiders.cardekho_case_study_hibernate.main;

import java.util.Scanner;

import com.jspiders.cardekho_case_study_hibernate.dao.CarOperationsDAO;

public class CarDekhoMain{
	static CarOperationsDAO operation = new CarOperationsDAO();
	static boolean loop = true;
	
	
	
	public static void main(String[] args) {
		while(loop) {
			CarDekhoMenu();
		}
	}
	
	public static void CarDekhoMenu() {
		System.out.println("----------MAIN-------------");
		System.out.println("1) Add / Remove Car Details\n"
				         + "2) Search Car Details\n"
				         + "3) Update Car Details\n"
				         + "4) Exit");
		Scanner sc = new Scanner(System.in);
		int choice1 = sc.nextInt();
		switch (choice1) {
//------------------------------------------------------------------------------------------------------------------------
		case 1:
		{
			System.out.println("-------Add/Remove-Car-Details-------");
			System.out.println("1) Add New Car Details\n"
					         + "2) Remove car Details\n"
					         + "3) Show all Car Details\n"
					         + "4) Go Back");
			int choice2 = sc.nextInt();
			switch (choice2) {
			case 1:
			{
				operation.addNewCar();
			}
				break;
			
			case 2:
			{
				operation.removeCar();
			}
				break;
			
			case 3:
			{
				operation.getCarDetails();
			}
				break;
			case 4:
			{
				CarDekhoMenu();
			}
				break;
				
			default:
			{
				System.out.println("Invalid input !!");
			}
				break;
			}
		}
			break;
//------------------------------------------------------------------------------------------------------------------------		
		case 2:
		{
			System.out.println("-------Search-CAR-BY---------");
			System.out.println("1) Search car by name\n"
					         + "2) Search car by Brand\n"
					         + "3) Search car by fuel type\n"
					         + "4) Go back to main menu");
			int choice3 = sc.nextInt();
			switch (choice3) {
			case 1:
			{
				System.out.print("Enter car name you are looking for:- ");
				operation.searchByName();
			}
				break;
			
			case 2:
			{
				System.out.print("Enter brand name of car you are looking for:- ");
				operation.searchByBrand();
			}
				break;
			
			case 3:
			{
				System.out.print("Enter fuel type of car you are looking for:- ");
				operation.searchByFuelType();
			}
				break;
				
			case 4:
			{
				CarDekhoMenu();
			}
				break;
				
			default:
			{
				System.out.println("Invalid input !!");
			}
				break;
			}
		}
			break;
//------------------------------------------------------------------------------------------------------------------------			
		case 3:
		{
			System.out.print("Enter Car Id:- ");
			operation.updateCar();
		}
			break;
//------------------------------------------------------------------------------------------------------------------------
		case 4:
		{
			System.out.println("ThankYou !!");
			loop = false;
		}
			break;
		default:
			{
				System.out.println("Invalid Input!!");
				CarDekhoMenu();
			}
			break;
		}
		}
}