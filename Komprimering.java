/**
 * Komprimering, h�r finns funktioner f�r att komprimera text.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Komprimering
{
	/**
	 * komprimera tar en str�ng och kompirmerar den.
	 *
	 * @param txtIn Str�ngen inneh�ller str�ngen som du vill komprimera.
	 */
	public static String komprimera(String txtIn)
	{
		/**
		 * En str�ngbuffert.
		 */
		StringBuffer tmp = new StringBuffer();
		int i = 0;

		// Loopa tills input str�ngen �r slut.
		while ( i < txtIn.length() )
		{
			// H�mta nuvarande bokstaven.
			char lastCh = txtIn.charAt(i);

			int nEqual = 1;
			i++;
			
			// Loopa tills textIn �r slut och att bokstaven p� plats i �r samma som lastCh
			while ( i < txtIn.length() && txtIn.charAt(i) == lastCh)
			{
				i++;
				nEqual++;
			}
			// Om nEqual �r mindre �n tre s� ska vi appenda lastCh till bufferten s� m�nga g�nger.
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
		
		// Returnerar den f�rdiga str�ngen.
		return tmp.toString();
	}

	/**
	 * dekomprimera tar en str�ng och dekomprimerar den.
	 *
	 * @param txtOut Str�ngen inneh�ller str�ngen som du vill dekomprimera.
	 */
	public static String dekomprimera(String txtOut)
	{
		/**
		 * En str�ngbuffert.
		 */
		StringBuffer tmp = new StringBuffer();
		int i = 0;

		// Loopa tills input str�ngen �r slut.
		while ( i < txtOut.length() )
		{
			// H�mta nuvarande bokstaven.
			char ch = txtOut.charAt(i);

			// Om bokstaven inte �r ett dollartecken s� appendar vi till tmp.
			if ( ch != '$')
				tmp.append(ch);
			else
			{
				// Om det �r ett dollartecken s� dekomprimerar vi den biten.
				i++;
				int nbr = txtOut.charAt(i);
				i++;
				ch = txtOut.charAt(i);

				for ( int k = 1; k <= nbr; k++)
					tmp.append(ch);
			}
			i++;
		}
		
		// Returnerar den f�rdiga str�ngen.
		return tmp.toString();
	}
}
