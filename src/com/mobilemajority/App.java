package com.mobilemajority;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import com.mobilemajority.controller.RecipeCostCalc;
import com.mobilemajority.data.Ingredient;
import com.mobilemajority.data.Recipe;
import com.mobilemajority.data.RecipeCostBreakdown;

/**
 * This is a main class to add ingredient, add recipe, 
 * see cost breakdown, see ingredient list and see recipe list
 *
 */
/**
 * @author shahbits22
 *
 */
public class App {

	// Store Data in collections (In memory Data Storage)
	public static LinkedHashMap<Integer, Ingredient> ingredientList = new LinkedHashMap<Integer, Ingredient>();
	public static LinkedHashMap<Integer, Recipe> recipeList = new LinkedHashMap<Integer, Recipe>();
	public static LinkedHashMap<Integer, String> measureMap = new LinkedHashMap<Integer, String>();
	public static LinkedHashMap<Recipe, RecipeCostBreakdown> resultMap = new LinkedHashMap<Recipe, RecipeCostBreakdown>();
	public static int ingredientNumber = 1;
	public static int recipeNumber = 1;

	public static void main(String[] args) {

		// Measure

		System.out
				.println("-----------Welcome to Recipe Calculator!-----------");

		measureMap.put(1, "quantity");
		measureMap.put(2, "cup");
		measureMap.put(3, "clove");
		measureMap.put(4, "slice");
		measureMap.put(5, "ounce");
		measureMap.put(6, "teaspoon");

		int choice = 0;
		boolean flag = true;

		while (flag) {

			// Choice Menu
			System.out.println("");
			System.out.println("Enter your choice: ");
			System.out.println("Press 1 to add ingredient details");
			System.out.println("Press 2 to add recipe details");
			System.out.println("press 3 to see recipe cost breakdown");
			System.out.println("press 4 to see the ingredient list");
			System.out.println("press 5 to see the recipe list");
			System.out.println("Press 6 to exit the application");
			Scanner S = new Scanner(System.in);
			int reloop =1;
			while(reloop!=0)
			{
				try{
					reloop =0;
					choice = S.nextInt(); // Enter Choice
				}
				catch(InputMismatchException e)
				{
					reloop =1;
					System.out.println("Enter numeric value");
				}
				S.nextLine();
			}
			
			

			switch (choice) {
			case 1: {
				// Add ingredient

				addIngredient();
				break;

			}

			case 2: {
				// Add Recipe
				addRecipe();
				break;
			}

			case 3: {
				// show Recipe Cost Breakdown
				showRecipeCostBreak();
				break;
			}
			case 4: {
				// show Ingredient Detail
				showIngredientDetail();
				break;
			}
			case 5: {
				// show Recipe Detail
				showRecipeList();
				break;
			}
			case 6:{
				flag = false;
			}

			default: {
				// Exit from the menu
				System.out.println("Enter any number from the list");
			}
			}
		}

		System.out.println("Good Bye!!!");
	}

	// Function to add the ingredient into the system
	public static void addIngredient() {
		Ingredient ingredient = new Ingredient();
		int type = 0;
		int measure = 0;
		Scanner S = new Scanner(System.in);
		int reloop = 1;
		while (type < 1 || type > 3 || reloop != 0) {
			reloop = 0;
			System.out.println("Enter Ingredient Type:");
			System.out.println("Press 1 for Produce");
			System.out.println("Press 2 for Meat / Poultry");
			System.out.println("Press 3 for Pantry");
			try {
				type = S.nextInt();
			} catch (InputMismatchException e) {
				reloop = 1;
				System.out.println("Enter Proper Type number");
			}
			S.nextLine(); // clears the buffer

		}
		ingredient.setType(type); // Set ingredient type

		String name = null;

		System.out.println("Enter Ingredient Name: ");

		name = S.nextLine();

		ingredient.setIngredientName(name); // Set ingredient name
		reloop = 1;
		while (measure < 1 || measure > measureMap.size() || reloop != 0) {
			reloop = 0;
			System.out.println("Enter Measure: ");

			Set<Integer> keys = measureMap.keySet();
			for (Integer key : keys) {
				System.out.println("Press " + key + " for "
						+ measureMap.get(key));
			}
			try {
				measure = S.nextInt();
			} catch (InputMismatchException e) {
				reloop = 1;
				System.out.println("Enter Proper Measure number");
			}
			S.nextLine(); // clears the buffer

		}
		ingredient.setMeasure(measure); // Set ingredient measure

		reloop = 1;
		double price = 0.00;
		while (reloop != 0) {
			reloop = 0;
			System.out.println("Enter the price for ingredient: ");
			try {

				price = S.nextDouble();
			} catch (InputMismatchException e) {
				reloop = 1;
				System.out.println("Enter Proper price");
			}
			S.nextLine(); // clears the buffer
		}

		ingredient.setPrice(price); // Set ingredient price

		System.out.println("Is an ingredient organic? (y/n)");
		String isOrganic = S.next(); // Whether ingredient is organic or not
		if (isOrganic.equals("y") || isOrganic.equals("Y")) {
			ingredient.setOrganic(true);
		} else {
			ingredient.setOrganic(false);
		}

		ingredientList.put(ingredientNumber++, ingredient);
		System.out.println("Ingredient added into the system...");
		System.out.println("");
	}

	// Function to list ingredient names
	public static void showIngredients() {
		Set<Integer> keys = ingredientList.keySet();
		for (Integer key : keys) {
			System.out.println(key + " "
					+ ingredientList.get(key).getIngredientName());
		}
	}

	// Function to add recipe into the system
	public static void addRecipe() {
		Recipe recipe = new Recipe();
		LinkedHashMap<Ingredient, Double> recipeIngredientList = new LinkedHashMap<Ingredient, Double>();
		String recipeName = "";
		int reloop = 1;
		Scanner S = new Scanner(System.in);

		System.out.println("Enter Recipe Name: ");

		recipeName = S.nextLine();

		recipe.setRecipeName(recipeName); // Set recipe name

		System.out.println("List of available ingredients");
		showIngredients();
		boolean flag = true;
		while (flag) {

			int number = 0;
			reloop = 1;
			double count = 0.00;
			while (reloop != 0) {
				reloop = 0;
				System.out.println("Enter ingredient number: ");

				try {
					number = S.nextInt();
				} catch (InputMismatchException e) {
					reloop = 1;
					System.out.println("Enter Proper number");
				}
				S.nextLine(); // clears the buffer
			}
			// --- check ingredient is available or not

			if (ingredientList.containsKey(number)) {
				if (ingredientList.get(number).getMeasure() == 1) {
					System.out.println("How many "
							+ ingredientList.get(number).getIngredientName()
							+ "?");
				} else {
					System.out.println("How many "
							+ measureMap.get(ingredientList.get(number)
									.getMeasure()) + " of "
							+ ingredientList.get(number).getIngredientName()
							+ "?");
				}

				reloop = 1;
				while (reloop != 0) {
					reloop = 0;

					try {
						count = S.nextDouble();
					} catch (InputMismatchException e) {
						reloop = 1;
						System.out.println("Enter Proper value");
					}
					S.nextLine(); // clears the buffer
				}
				recipeIngredientList.put(ingredientList.get(number), count); // Add
																				// ingredient
																				// to
																				// recipeIngredient
																				// List

			}

			else {
				System.out.println("Ingredient is not available in system");

			}

			System.out.println("Do you want to add more ingredients? (y/n)");
			String choice = S.next();
			if (choice.equals("n") || choice.equals("N")) {
				flag = false;
			}
		}
		if (!recipeIngredientList.isEmpty()) {
			recipe.setIngredientList(recipeIngredientList);
			recipeList.put(recipeNumber++, recipe);
			calculateRecipeCost(recipe);
			System.out.println("Recipe added into the system...");
			System.out.println("");
		}

	}

	// Function to calculate recipe cost
	public static void calculateRecipeCost(Recipe recipe) {
		RecipeCostBreakdown rcb = new RecipeCostBreakdown();
		RecipeCostCalc rc = new RecipeCostCalc(recipe, rcb);
		rc.calculateTotalPrice();
		rc.calculateSalesTax();
		rc.calculateWellnessDiscount();
		rc.calculateTotalCost();

		resultMap.put(recipe, rcb);

	}

	// Function to show recipe cost breakdown
	public static void showRecipeCostBreak() {
		if (recipeList.isEmpty()) {
			System.out.println("There is no recipe in the system!");
			System.out.println("");
			return;
		}
		Set<Integer> numbers = recipeList.keySet();
		for (Integer number : numbers) {
			System.out.println("Press " + number + " for "
					+ recipeList.get(number).getRecipeName());
		}
		boolean flag = true;
		while (flag) {
			Scanner S = new Scanner(System.in);
			int number = 0;
			int reloop = 1;
			while (reloop != 0) {
				reloop = 0;

				try {
					number = S.nextInt();
				} catch (InputMismatchException e) {
					reloop = 1;
					System.out.println("Enter proper recipe number: ");
				}
				S.nextLine(); // clears the buffer
			}

			if (recipeList.containsKey(number)) {
				System.out.println("Recipe Name: "
						+ recipeList.get(number).getRecipeName());
				System.out
						.println("Total Price: $"
								+ resultMap.get(recipeList.get(number))
										.getTotalPrice());
				System.out.println("Sales Tax: $"
						+ resultMap.get(recipeList.get(number)).getSalesTax());
				System.out.println("Wellness Discount: $"
						+ resultMap.get(recipeList.get(number))
								.getWellnessDiscount());
				System.out.println("Total Cost: $"
						+ resultMap.get(recipeList.get(number)).getTotalCost());
				System.out.println("");

			} else {
				System.out
						.println("Recipe that you are looking for is not available ");
			}
			System.out
					.println("Do you want to see other recipe's cost breakdown? (y/n)");
			String choice = S.next();
			if (choice.equals("n") || choice.equals("N")) {
				flag = false;
			} else {
				System.out.println("Enter Recipe Number: ");
			}
		}
	}

	// Function to list ingredient details
	public static void showIngredientDetail() {

		if (ingredientList.isEmpty()) {
			System.out.println("There is no ingredient in the system!");
			System.out.println("");
			return;
		}

		Set<Integer> numbers = ingredientList.keySet();
		for (Integer number : numbers) {
			System.out.println("1 "
					+ measureMap.get(ingredientList.get(number).getMeasure())
					+ " of " + ingredientList.get(number).getIngredientName()
					+ ": $" + ingredientList.get(number).getPrice());
		}
	}

	// Function to list recipe details
	public static void showRecipeList() {
		if (recipeList.isEmpty()) {
			System.out.println("There is no recipe in the system!");
			System.out.println("");
			return;
		}
		Set<Integer> numbers = recipeList.keySet();
		for (Integer number : numbers) {
			System.out.println("");
			System.out.println(recipeList.get(number).getRecipeName());
			System.out.println("--------------------------------");
			Set<Ingredient> ingredients = recipeList.get(number)
					.getIngredientList().keySet();
			for (Ingredient ingredient : ingredients) {
				System.out.println(recipeList.get(number).getIngredientList()
						.get(ingredient)
						+ " "
						+ measureMap.get(ingredient.getMeasure())
						+ " of " + ingredient.getIngredientName());
			}
		}
	}
}