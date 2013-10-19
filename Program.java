// Importerar n�dv�ndiga paket.
import java.io.*;

/**
 * Program klassen, h�r startar man program som kalkylatorn och kommandotolken.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Program
{
	/**
	 * Starta
	 *
	 * @param input Str�ngen inneh�ller vilket program du vill starta, Calc f�r kalkylatorn och CMD f�r kommandotolken.
	 */
	public static void Starta(String input)
	{
		// Om input �r Calc
		if (input == "Calc")
		{
			// F�rs�k starta programmet annars skriv ut ett IOException.
			try
			{
				Runtime.getRuntime().exec("calc.exe");
			}
			catch( IOException ie)
			{
				System.out.println("IOException!");
			}
		} 

		// Om input �r CMD
		if (input == "CMD")
		{
			// F�rs�k starta programmet annars skriv ut ett IOException.
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