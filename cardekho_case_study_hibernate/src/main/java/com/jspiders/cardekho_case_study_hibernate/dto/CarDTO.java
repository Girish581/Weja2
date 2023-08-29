package com.jspiders.cardekho_case_study_hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cars")

public class CarDTO {
	
	@Id
	private int Car_id ;
	private String Car_name;
	private String Car_Brand;
	private String Fuel_type;
	private double Car_Price;

}
