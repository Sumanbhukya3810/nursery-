package com.seed.in.entity;

import org.antlr.v4.runtime.misc.NotNull;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seed {
	 
	@Id
	@GeneratedValue
	private int seedId;
	
	@NotBlank (message = "Seed name is mandatory")
	@Size (max = 255, message = "Seed name must be less than or equal to 255 characters")
	private String seedName;
	
	@NonNull
	@DecimalMin(value = "0.01", message = "Price must be greater than zero")
	private float price;
	
	@Min(value = 1, message = "Number of seeds per packet must be at least 1")
	private int noOfSeedsPerPacket;
	
	@Size(max = 500, message = "Description must be less than or equal to 500 characters")
	private String description;
	
}
