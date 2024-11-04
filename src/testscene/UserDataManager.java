package testscene;

public class UserDataManager {
	private static double moneySpent = 0.0;

	public static void addPurchase(double amount) {
		moneySpent += amount;
	}

	public static double getMoneySpent() {
		return moneySpent;
	}
}
