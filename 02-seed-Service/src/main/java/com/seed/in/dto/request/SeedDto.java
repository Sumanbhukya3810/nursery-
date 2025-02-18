package com.seed.in.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeedDto {
	
	private String seedName;
	private float price;
	private int noOfSeedsPerPacket;
	private String description;
	
	
	public String getSeedName() {
		return seedName;
	}
	public void setSeedName(String seedName) {
		this.seedName = seedName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNoOfSeedsPerPacket() {
		return noOfSeedsPerPacket;
	}
	public void setNoOfSeedsPerPacket(int noOfSeedsPerPacket) {
		this.noOfSeedsPerPacket = noOfSeedsPerPacket;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "SeedDto [seedName=" + seedName + ", price=" + price + ", noOfSeedsPerPacket=" + noOfSeedsPerPacket
				+ ", description=" + description + "]";
	}
	

}
