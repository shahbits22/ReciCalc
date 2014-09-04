package com.mobilemajority.controller;

import java.util.Set;

import com.mobilemajority.data.Ingredient;
import com.mobilemajority.data.Recipe;
import com.mobilemajority.data.RecipeCostBreakdown;

/*This class is to calculate recipe cost (Total Price, Sales Tax, 
 * Wellness Discount & Total Cost of Recipe) */
public class RecipeCostCalc {

	private Recipe recipe;
	private RecipeCostBreakdown rcb;
	private double totalPrice = 0;
	private double salesTax = 0;
	private double wellnessDiscount = 0;
	private double totalCost = 0;

	public RecipeCostCalc(Recipe recipe, RecipeCostBreakdown rcb) {
		this.recipe = recipe;
		this.rcb = rcb;
	}

	// function to calculate total price
	public void calculateTotalPrice() {
		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			totalPrice = totalPrice
					+ (key.getPrice() * recipe.getIngredientList().get(key));
		}
		
		rcb.setTotalPrice((double) Math.round(totalPrice * 100.0) / 100.0);
	}

	// function to calculate sales tax
	public void calculateSalesTax() {

		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			if (key.getType() != 1) {
				salesTax = salesTax
						+ (key.getPrice() * recipe.getIngredientList().get(key));
			}
		}
		salesTax = salesTax * 0.086;

		// logic to round up to nearest 7 cent
		double n2 = salesTax * 100;
		int n1 = (int) (salesTax * 100);

		double sub = n2 - (double) n1;
		int modulo = n1 % 7;
		int value = 0;
		if (modulo == 0) {
			if (sub == 0) {
				value = n1;
			} else {
				value = n1 - modulo + 7;
			}
		} else {
			value = n1 - modulo + 7;
		}

		salesTax = (double) value / 100;

		rcb.setSalesTax(salesTax);
	}

	// function to calculate wellness discount based on organic ingredients
	public void calculateWellnessDiscount() {
		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			if (key.isOrganic()) {
				wellnessDiscount = wellnessDiscount
						+ (key.getPrice() * recipe.getIngredientList().get(key) * 0.05);
			}
		}

		// round up to nearest cent
		wellnessDiscount = (double) Math.ceil(wellnessDiscount * 100) / 100; //always rounded up to nearest cent
		rcb.setWellnessDiscount(wellnessDiscount);
	}

	// function to calculate total cost of recipe

	public void calculateTotalCost() {
		totalCost = rcb.getTotalPrice() + rcb.getSalesTax() - rcb.getWellnessDiscount();
		rcb.setTotalCost((double) Math.round(totalCost * 100) / 100);
	}

}
