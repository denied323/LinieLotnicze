package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleClass{

	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	public VehicleClass( String name){
		this.name = name;
	}
	
	public VehicleClass(int id, String name){
		this.id = id;
		this.name = name;
	}

}
