package com.mobilemajority;


public class Ingredient {

	private String ingredientName;
	private int type;
	private int measure;
	private double price;
	private boolean isOrganic;
	/**
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}
	/**
	 * @param ingredientName the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the measure
	 */
	public int getMeasure() {
		return measure;
	}
	/**
	 * @param measure the measure to set
	 */
	public void setMeasure(int measure) {
		this.measure = measure;
	}
	
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
		
	}
	/**
	 * @return the isOrganic
	 */
	public boolean isOrganic() {
		return isOrganic;
	}
	/**
	 * @param isOrganic the isOrganic to set
	 */
	public void setOrganic(boolean isOrganic) {
		this.isOrganic = isOrganic;
	}
	
}
