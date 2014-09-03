package com.mobilemajority;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {

	public static LinkedHashMap<Integer, Ingredient> ingredientList = new LinkedHashMap<Integer, Ingredient>();
	public static LinkedHashMap<Integer, Recipe> recipeList = new LinkedHashMap<Integer, Recipe>();
	public static LinkedHashMap<Integer, String> measureMap = new LinkedHashMap<Integer, String>();
	public static LinkedHashMap<Recipe, RecipeCostBreakdown> resultMap = new LinkedHashMap<Recipe, RecipeCostBreakdown>();
	public static int ingredientNumber = 1;
	public static int recipeNumber = 1;

	public static void main(String[] args) {
		// System.out.println( "Hello World!" );

		// Measure

		measureMap.put(1, "quantity");
		measureMap.put(2, "cup");
		measureMap.put(3, "clove");
		measureMap.put(4, "slice");
		measureMap.put(5, "ounce");
		measureMap.put(6, "teaspoon");

		int choice = 0;
		boolean flag = true;

		while (flag) {
			System.out.println("Enter your choice: ");
			System.out.println("Press 1 to add ingredient details");
			System.out.println("Press 2 to add recipe details");
			System.out.println("press 3 to see recipe cost breakdown");
			System.out.println("press 4 to see the ingredient list");
			System.out.println("press 5 to see the recipe list");
			System.out.println("Press 6 to exit the application");
			Scanner S = new Scanner(System.in);
			choice = S.nextInt();
			// System.out.println(choice + "choice");
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

			default: {
				flag = false;
			}
			}
		}
		// showIngredients();
		System.out.println("Good Bye!!!");
	}

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
		ingredient.setType(type);

		String name = null;

		System.out.println("Enter Ingredient Name: ");

		name = S.nextLine();

		ingredient.setIngredientName(name);
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
		ingredient.setMeasure(measure);

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

		ingredient.setPrice(price);

		System.out.println("Is an ingredient organic? (y/n)");
		String isOrganic = S.next();
		if (isOrganic.equals("y") || isOrganic.equals("Y")) {
			ingredient.setOrganic(true);
		} else {
			ingredient.setOrganic(false);
		}

		ingredientList.put(ingredientNumber++, ingredient);
		System.out.println("Ingredient added into the system...");
	}

	public static void showIngredients() {
		Set<Integer> keys = ingredientList.keySet();
		for (Integer key : keys) {
			System.out.println(key + " "
					+ ingredientList.get(key).getIngredientName());
		}
	}

	public static void addRecipe() {
		Recipe recipe = new Recipe();
		LinkedHashMap<Ingredient, Double> recipeIngredientList = new LinkedHashMap<Ingredient, Double>();
		String recipeName = "";
		int reloop = 1;
		Scanner S = new Scanner(System.in);

		System.out.println("Enter Recipe Name: ");

		recipeName = S.nextLine();

		recipe.setRecipeName(recipeName);

		System.out.println("List of available ingredients");
		showIngredients();
		boolean flag = true;
		while (flag) {

			int number = 0;
			reloop = 1;
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

			if (ingredientList.get(number).getMeasure() == 1) {
				System.out.println("How many "
						+ ingredientList.get(number).getIngredientName() + "?");
			} else {
				System.out.println("How many "
						+ measureMap.get(ingredientList.get(number)
								.getMeasure()) + " of "
						+ ingredientList.get(number).getIngredientName() + "?");
			}

			double count = 0.00;
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

			recipeIngredientList.put(ingredientList.get(number), count);
			System.out.println("Do you want to add more ingredients? (y/n)");
			String choice = S.next();
			if (choice.equals("n") || choice.equals("N")) {
				flag = false;
			}
		}
		recipe.setIngredientList(recipeIngredientList);
		recipeList.put(recipeNumber++, recipe);
		calculateRecipeCost(recipe);
		System.out.println("Recipe added into the system...");

	}

	public static void calculateRecipeCost(Recipe recipe) {
		RecipeCostBreakdown rcb = new RecipeCostBreakdown();
		RecipeCostCalc rc = new RecipeCostCalc(recipe, rcb);
		rc.calculateTotalPrice();
		rc.calculateSalesTax();
		rc.calculateWellnessDiscount();
		rc.calculateTotalCost();

		resultMap.put(recipe, rcb);

	}

	public static void showRecipeCostBreak() {
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
				System.out
						.println("Do you want to see other recipe's cost breakdown? (y/n)");

				String choice = S.next();
				if (choice.equals("n") || choice.equals("N")) {
					flag = false;
				} else {
					System.out.println("Enter Recipe Number: ");
				}

			} else {
				System.out.println("Enter proper recipe number: ");
			}
		}
	}

	public static void showIngredientDetail() {

		Set<Integer> numbers = ingredientList.keySet();
		for (Integer number : numbers) {
			System.out.println("1 "
					+ measureMap.get(ingredientList.get(number).getMeasure())
					+ " of " + ingredientList.get(number).getIngredientName()
					+ ": $" + ingredientList.get(number).getPrice());
		}
	}

	public static void showRecipeList() {
		Set<Integer> numbers = recipeList.keySet();
		for (Integer number : numbers) {
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