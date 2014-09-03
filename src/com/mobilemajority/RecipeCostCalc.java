package com.mobilemajority;

import java.util.Set;

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

	public void calculateTotalPrice() {
		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			totalPrice = totalPrice
					+ (key.getPrice() * recipe.getIngredientList().get(key));
		}
		
		rcb.setTotalPrice(totalPrice);
	}

	

	public void calculateSalesTax() {

		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			if (key.getType() != 1) {
				salesTax = salesTax
						+ (key.getPrice() * recipe.getIngredientList().get(key));
			}
		}
		salesTax = salesTax * 0.086;
		double n2 = salesTax*100;
		int n1 = (int)(salesTax*100);
		
		double sub = n2 - (double)n1;
		int modulo = n1%7;
		int value=0;
		if(modulo == 0)
		{
			if(sub == 0)
			{
				value = n1;
			}
			else{
				value = n1 - modulo + 7;
			}
		}
		else{
			value = n1 - modulo + 7;
		}

		 salesTax = (double) value/100;

		rcb.setSalesTax(salesTax);
	}


	public void calculateWellnessDiscount() {
		Set<Ingredient> keys = recipe.getIngredientList().keySet();

		for (Ingredient key : keys) {
			if (key.isOrganic()) {
				wellnessDiscount = wellnessDiscount
						+ (key.getPrice() * recipe.getIngredientList().get(key) * 0.05);
			}
		}
		wellnessDiscount = (double) Math.round(wellnessDiscount * 100) / 100;
		rcb.setWellnessDiscount(wellnessDiscount);
	}

	

	public void calculateTotalCost() {
		totalCost = totalPrice + salesTax - wellnessDiscount;
		rcb.setTotalCost(totalCost);
	}

	
}
