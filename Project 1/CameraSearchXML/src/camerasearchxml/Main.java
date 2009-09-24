/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package camerasearchxml;

/**
 *
 * @author csimoes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
					ShowErrorMessage("Wrong number of arguments!");
					return;
				}
				Settings settings = new Settings();
				System.out.println(settings.getListOfBrandsUrl());
    }

		private static void ShowHelpMessage() {
			System.out.println("usage: camerasearchxml.exe <brand>");
		}

		private static void ShowErrorMessage(String errorMessage) {
			System.out.println(errorMessage);
			ShowHelpMessage();
		}

}
