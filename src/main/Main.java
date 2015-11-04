package main;

import products.Book;
import products.Phone;
import products.Product;
import shopping.ItemElement;
import shopping.ShoppingCartVisitor;
import shopping.ShoppingCartVisitorImpl;
import utilities.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		boolean isValid = false;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Enter username : ");
			String userName = scanner.next();

			System.out.print("Enter password : ");
			String password = scanner.next();

			Login login = new Login();
			if (!login.validateLogin(userName, password)) {
				System.out.println("Either UserName or Password is invalid. Do you want to try again?[Y for yes]");
				String choice = scanner.next();
				if(!choice.toLowerCase().equals("y")){
					System.exit(0);
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		// if (!isValid) {
		// System.exit(0);
		// }

		List<Product> productList = new ArrayList<>();
		productList.add(new Book(0, "Harry Potter", 55.0d, "J.K Rowling",System.currentTimeMillis()));
		productList.add(new Book(1, "Gone Girl", 20.0d, "Gillian Flynn",System.currentTimeMillis()+45));
		productList.add(new Book(2, "The Girl on the Train", 40.0d, "Paula Hawkins",System.currentTimeMillis()-45));
		productList.add(new Phone(3, "Iphone", 300.0d, "6S","iOS 9"));
		

		System.out.print("Welcome.\n"
				+ "You can input 'help' to see the command list.\n");
		scanner = new Scanner(System.in);
		int screen = 0;
		while (true) {
			if (screen == 0) {
				System.out.println("====================HOME====================");
				switch (scanner.next()) {
				case "help":
					System.out.print("command list:\n"
							+ "exit:\tFinish the application.\n"
							+ "shop:\tEnter the shop page.\n"
							+ "cart:\tEnter the cart page.\n");
					break;
				case "exit":
					System.out.println("Bye.");
					System.exit(0);
					break;
				case "shop":
					screen = 1;
					show(productList);
					break;
				case "cart":
					screen = 2;
					show(Cart.INSTANCE.asList());
					break;
				default:
					System.out.println("Unknown.");
					break;
				}
			} else if (screen == 1) {
				System.out.println("====================SHOP====================");
				switch (scanner.next()) {
				case "help":
					System.out.print("command list:\n"
							+ "exit:\tBack to home page.\n"
							+ "cart:\tEnter the cart page.\n"
							+ "refresh:\tShow the product list.\n"
							+ "add:\tAdd a product into cart.\n");
					break;
				case "exit":
					screen = 0;
					break;
				case "cart":
					screen = 2;
					show(Cart.INSTANCE.asList());
					break;
				case "refresh":
					show(productList);
					break;
				case "add":
					System.out.println("Which one: ");
					Cart.INSTANCE.add(productList.get(scanner.nextInt()));
					break;
				default:
					System.out.println("Unknown.");
					break;
				}
			} else if (screen == 2) {
				System.out
						.println("====================CART====================");
				switch (scanner.next()) {
				case "help":
					System.out
							.print("command list:\n"
									+ "exit:\tBack to home page.\n"
									+ "shop:\tEnter the shop page.\n"
									+ "refresh:\tShow the product list.\n"
									+ "remove:\tRemove a product from cart.\n"
									+ "backup:\tCreate a backup in order to recover it.\n"
									+ "\t\t(If you need, it's easy to make this step auto run once remove a product)\n"
									+ "recover:\tRecover the cart to the last saved state.\n"
									+ "P:\tProceed to checkout\n");
					break;
				case "exit":
					screen = 0;
					break;
				case "shop":
					screen = 1;
					show(productList);
					break;
				case "refresh":
					show(Cart.INSTANCE.asList());
					break;
				case "remove":
					System.out.println("Which one: ");
					Cart.INSTANCE.remove(Cart.INSTANCE.asList().get(scanner.nextInt()));
					break;
				case "backup":
					Cart.INSTANCE.backup();
					break;
				case "recover":
					Cart.INSTANCE.recover();
					break;
				case "P":
				case "p":
					checkOut(Cart.INSTANCE.asList());
				default:
					System.out.println("Unknown.");
					break;
				}
			}
		}
	}

	public static void show(List<Product> productList) {
		System.out.println();
		System.out.println("############################################");
		for (int index = 0; index < productList.size(); index++)
			System.out.println(index + productList.get(index).toString());
		System.out.println("############################################");
		System.out.println();
	}

	public static void checkOut(List<Product> productList) {
		if (productList.isEmpty()) {
			System.out.println("Shopping cart is empty");
		} else {
			Scanner scanner = null;
			try {
				scanner = new Scanner(System.in);
				System.out.print("Enter Credit Card no : ");
				String creditCard = scanner.next();

				if (creditCard.isEmpty() || creditCard.length() != 4
						|| Long.parseLong(creditCard) <= 0) {
					System.out.println("Invalid Credit card number, must be 4 digits long");
				} else {
					ItemElement[] items = new ItemElement[productList.size()];
					int i = 0;
					for (Product product : productList) {
						items[i++] = product;
					}
					double sum = calculatePrice(items);

					System.out.println("Total Payment : " + sum);

					System.out.print("Enter C to complete order : ");
					String choice = scanner.next();

					if (choice.equals("c") || choice.equals("C")) {
						System.out.println("The order has been completed.");
					}
					System.exit(0);
				}
			} catch (Exception exception) {
				System.out.println("Invalid creditcard no.");
			} finally {
				if (scanner != null)
					;
				// scanner.close();
			}
		}
	}

	private static double calculatePrice(ItemElement[] items) {
		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		double sum = 0;
		for (ItemElement item : items) {
			sum = sum + item.accept(visitor);
		}
		return sum;
	}
}