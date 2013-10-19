/**
 * Komprimering, här finns funktioner för att komprimera text.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Komprimering
{
	/**
	 * komprimera tar en sträng och kompirmerar den.
	 *
	 * @param txtIn Strängen innehåller strängen som du vill komprimera.
	 */
	public static String komprimera(String txtIn)
	{
		/**
		 * En strängbuffert.
		 */
		StringBuffer tmp = new StringBuffer();
		int i = 0;

		// Loopa tills input strängen är slut.
		while ( i < txtIn.length() )
		{
			// Hämta nuvarande bokstaven.
			char lastCh = txtIn.charAt(i);

			int nEqual = 1;
			i++;
			
			// Loopa tills textIn är slut och att bokstaven på plats i är samma som lastCh
			while ( i < txtIn.length() && txtIn.charAt(i) == lastCh)
			{
				i++;
				nEqual++;
			}
			// Om nEqual är mindre än tre så ska vi appenda lastCh till bufferten så många gånger.
			if ( nEqual <= 3 )
			{
				for ( int k = 1; k <= nEqual; k++)
					tmp.append(lastCh);
			}
			else
			{
				// annars appendar vi ett dollar-tecken och nEqual som ett tecken plus lastCh.
				tmp.append('$');
				tmp.append( (char) nEqual);
				tmp.append(lastCh);
			}
		}
		
		// Returnerar den färdiga strängen.
		return tmp.toString();
	}

	/**
	 * dekomprimera tar en sträng och dekomprimerar den.
	 *
	 * @param txtOut Strängen innehåller strängen som du vill dekomprimera.
	 */
	public static String dekomprimera(String txtOut)
	{
		/**
		 * En strängbuffert.
		 */
		StringBuffer tmp = new StringBuffer();
		int i = 0;

		// Loopa tills input strängen är slut.
		while ( i < txtOut.length() )
		{
			// Hämta nuvarande bokstaven.
			char ch = txtOut.charAt(i);

			// Om bokstaven inte är ett dollartecken så appendar vi till tmp.
			if ( ch != '$')
				tmp.append(ch);
			else
			{
				// Om det är ett dollartecken så dekomprimerar vi den biten.
				i++;
				int nbr = txtOut.charAt(i);
				i++;
				ch = txtOut.charAt(i);

				for ( int k = 1; k <= nbr; k++)
					tmp.append(ch);
			}
			i++;
		}
		
		// Returnerar den färdiga strängen.
		return tmp.toString();
	}
}
