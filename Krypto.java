// Importerar nödvändiga paket.
import static javax.swing.JOptionPane.showInputDialog;

/**
 * Komprimering och kryptering, här finns funktioner för att komprimera text och kryptera den enligt substituionschiffer.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Krypto
{
	/**
	 * Svenska alfabetet som innehåller alla karaktärer som kan tänkas bytas ut
	 */
	static String alfabet = "abcdefghijklmnopqrstuvwxyzåäö" ;

	/**
	 * Nyckelsträng som innehåller karaktärer som man vill byta ut alfabetet mot
	 */
	static String nyckelsträng = "guwy1934psaeic¤/(=)#@$zlfhdkj";

	/**
	 * nyckel är en funktiom som låter personen ändra nyckelsträngen.
	 */
	public static void nyckel()
	{
		String nyckel = "";

		// Loopa tills vi matat in en nyckel som är 29 bokstäver lång.
		do
		{
			nyckel = showInputDialog("Skriv in din nyckel!\nOBS: Behöver vara 29 karaktärer lång");
		} while (nyckel.length() != 29);

		nyckelsträng = nyckel;
	}

	/**
	 * kryptera är en funktiom som krypterar texten enligt ett substitionschiffer.
	 * Det betyder att a blir likamed b om a representeras av b i substitutionstabellen
	 *
	 * @param klarText Texten som ska krypteras
	 *
	 * @return Returnerar den krypterade texten.
	 */
	public static String kryptera(String klarText)
	{
		int i,j;
		StringBuffer tmp =  new StringBuffer();

		// Loopar igenom hela klartText
		for(i = 0; i< klarText.length(); i++)
		{
			// Ändrar bokstaven till liten bokstav.
			char c = Character.toLowerCase(klarText.charAt(i));

			// Loopar igenom 29 gånger
			for(j = 0; j < 29; j++)
			{
				// Om vi hittar bokstaven i vårt alfabet så appendar vi det krypterade tecknet.
				if ( c == alfabet.charAt(j))
					tmp.append(nyckelsträng.charAt(j));
			}
		}

		// Returnera den krypterade texten.
		return tmp.toString();
	}

	/**
	 * Dekryptera är en funktiom som dekrypterar texten.
	 *
	 * @param hemligText Texten som ska dekrypteras
	 *
	 * @return Returnerar den dekrypterade texten.
	 */
	public static String dekryptera(String hemligText)
	{
		int i,j;
		StringBuffer tmp =  new StringBuffer();

		// Loopar igenom hela hemligText
		for(i = 0; i< hemligText.length(); i++)
		{
			// Ändrar bokstaven till liten bokstav.
			char c = Character.toLowerCase(hemligText.charAt(i));

			// Loopar igenom 29 gånger
			for(j = 0; j < 29; j++)
			{
				// Om vi hittar bokstaven i vår nyckel så appendar vi det dekrypterade tecknet.
				if ( c == nyckelsträng.charAt(j))
					tmp.append(alfabet.charAt(j));
			}
		}

		// Returnera den krypterade texten.
		return tmp.toString();
	}
}