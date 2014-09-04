package com.mobilemajority.data;


/*This is a POJO class for recipe breakdown*/
public class RecipeCostBreakdown {
	
	private double totalPrice;
	private double salesTax;
	private double wellnessDiscount;
	private double totalCost;
	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the salesTax
	 */
	public double getSalesTax() {
		return salesTax;
	}
	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	/**
	 * @return the wellnessDiscount
	 */
	public double getWellnessDiscount() {
		return wellnessDiscount;
	}
	/**
	 * @param wellnessDiscount the wellnessDiscount to set
	 */
	public void setWellnessDiscount(double wellnessDiscount) {
		this.wellnessDiscount = wellnessDiscount;
	}
	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	

}
