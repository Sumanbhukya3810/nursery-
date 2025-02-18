package com.planter.in.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PlanterDto {
	
	private int planterId;
	private String planterName;
	private float price;
	private int noOfPlanters;
	private String description;
	
	
	public int getPlanterId() {
		return planterId;
	}
	public void setPlanterId(int planterId) {
		this.planterId = planterId;
	}
	public String getPlanterName() {
		return planterName;
	}
	public void setPlanterName(String planterName) {
		this.planterName = planterName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNoOfPlanters() {
		return noOfPlanters;
	}
	public void setNoOfPlanters(int noOfPlanters) {
		this.noOfPlanters = noOfPlanters;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "PlanterDto [planterName=" + planterName + ", price=" + price + ", noOfPlanters=" + noOfPlanters
				+ ", description=" + description + "]";
	}
}
