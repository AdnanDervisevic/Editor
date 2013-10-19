// Importerar n�dv�ndiga paket.
import static javax.swing.JOptionPane.showInputDialog;

/**
 * Komprimering och kryptering, h�r finns funktioner f�r att komprimera text och kryptera den enligt substituionschiffer.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Krypto
{
	/**
	 * Svenska alfabetet som inneh�ller alla karakt�rer som kan t�nkas bytas ut
	 */
	static String alfabet = "abcdefghijklmnopqrstuvwxyz���" ;

	/**
	 * Nyckelstr�ng som inneh�ller karakt�rer som man vill byta ut alfabetet mot
	 */
	static String nyckelstr�ng = "guwy1934psaeic�/(=)#@$zlfhdkj";

	/**
	 * nyckel �r en funktiom som l�ter personen �ndra nyckelstr�ngen.
	 */
	public static void nyckel()
	{
		String nyckel = "";

		// Loopa tills vi matat in en nyckel som �r 29 bokst�ver l�ng.
		do
		{
			nyckel = showInputDialog("Skriv in din nyckel!\nOBS: Beh�ver vara 29 karakt�rer l�ng");
		} while (nyckel.length() != 29);

		nyckelstr�ng = nyckel;
	}

	/**
	 * kryptera �r en funktiom som krypterar texten enligt ett substitionschiffer.
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
			// �ndrar bokstaven till liten bokstav.
			char c = Character.toLowerCase(klarText.charAt(i));

			// Loopar igenom 29 g�nger
			for(j = 0; j < 29; j++)
			{
				// Om vi hittar bokstaven i v�rt alfabet s� appendar vi det krypterade tecknet.
				if ( c == alfabet.charAt(j))
					tmp.append(nyckelstr�ng.charAt(j));
			}
		}

		// Returnera den krypterade texten.
		return tmp.toString();
	}

	/**
	 * Dekryptera �r en funktiom som dekrypterar texten.
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
			// �ndrar bokstaven till liten bokstav.
			char c = Character.toLowerCase(hemligText.charAt(i));

			// Loopar igenom 29 g�nger
			for(j = 0; j < 29; j++)
			{
				// Om vi hittar bokstaven i v�r nyckel s� appendar vi det dekrypterade tecknet.
				if ( c == nyckelstr�ng.charAt(j))
					tmp.append(alfabet.charAt(j));
			}
		}

		// Returnera den krypterade texten.
		return tmp.toString();
	}
}