package com.mobilemajority;

import java.util.LinkedHashMap;

public class Recipe {

	private String recipeName;
	private LinkedHashMap<Ingredient, Double> ingredientList = new LinkedHashMap<Ingredient, Double>();
	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}
	/**
	 * @param recipeName the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	/**
	 * @return the ingredientList
	 */
	public LinkedHashMap<Ingredient, Double> getIngredientList() {
		return ingredientList;
	}
	/**
	 * @param ingredientList the ingredientList to set
	 */
	public void setIngredientList(LinkedHashMap<Ingredient, Double> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
}
