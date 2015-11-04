package utilities;

import java.io.File;
import java.util.Scanner;

public class Login {

	public boolean validateLogin(String userName, String password)
			throws Exception {
		boolean isValidLogin = false;
		File csvFile = null;
		Scanner csvScanner = null;
		String loginInfo[] = null;
		try {
			csvFile = new File(getClass().getResource("/users/Users.csv").getPath());
			if (!csvFile.exists()) {
				throw new Exception("The Users.csv file could not be found.");
			} else {
				csvScanner = new Scanner(csvFile);
				csvScanner.useDelimiter(System.getProperty("line.separator"));
				
				while(csvScanner.hasNext()){
					loginInfo = csvScanner.nextLine().split(",");

					if (loginInfo[0].equals(userName)
							&& loginInfo[1].equals(password)) {
						isValidLogin = true;
						break;
					}
				}
			}
		} finally {
			loginInfo = null;
			csvFile = null;
			if (csvScanner != null) {
				csvScanner.close();
				csvScanner = null;
			}
		}
		return isValidLogin;
	}
}