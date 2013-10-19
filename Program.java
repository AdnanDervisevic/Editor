// Importerar nödvändiga paket.
import java.io.*;

/**
 * Program klassen, här startar man program som kalkylatorn och kommandotolken.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Program
{
	/**
	 * Starta
	 *
	 * @param input Strängen innehåller vilket program du vill starta, Calc för kalkylatorn och CMD för kommandotolken.
	 */
	public static void Starta(String input)
	{
		// Om input är Calc
		if (input == "Calc")
		{
			// Försök starta programmet annars skriv ut ett IOException.
			try
			{
				Runtime.getRuntime().exec("calc.exe");
			}
			catch( IOException ie)
			{
				System.out.println("IOException!");
			}
		} 

		// Om input är CMD
		if (input == "CMD")
		{
			// Försök starta programmet annars skriv ut ett IOException.
			try
			{
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe");
			}
			catch( IOException ie)
			{
				System.out.println("IOException!");
			}
		}
	}
}