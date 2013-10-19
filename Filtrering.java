/**
 * Filtrering, h�r finns funktioner f�r att flitrera bort HTML taggar, ta bort tomrader
 * och �ven l�gga in en xHTML mall.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Filtrering
{
	/**
	 * Taggar �r en funktion som tar bort alla < och > tecken i input str�ngen.
	 *
	 * @param inText Str�ngen som taggarna ska tas bort ifr�n.
	 */
	public static void Taggar(String inText)
	{
		/**
		 * En buffer d�r man l�ser in texten fr�n textarean (inparameter) som sedan kan editeras och skrivas ut, man kan appenda med suffix och prefix t.ex.
		 */
		StringBuffer htmlText = new StringBuffer(inText);

		for (int i = 0; i < htmlText.length(); i++)
		{
			if (htmlText.charAt(i) == '<' )
			{
				while(htmlText.charAt(i)  != '>' &&	i < htmlText.length())
				{
					htmlText.deleteCharAt(i);
				}
				if (htmlText.charAt(i)  == '>' )
				{
					htmlText.deleteCharAt(i);
					i--;
				}
			}
		}
		
		// �ndrar texten i texteditorns text area.		
		TextEditor.textArea.setText(htmlText.toString());
		htmlText = null; // G�r str�ngbufferten tillg�nglig f�r skr�psamlaren
	}

	/**
	 * Tomrader �r en funktion som tar emot en str�ng och tar sedan bort alla
	 * tomma rader.
	 *
	 * @param inText Str�ngen som taggarna ska tas bort ifr�n.
	 *
	 * @return Returnerar den f�rdiga str�ngen med alla taggar bortagna.
	 */
	public static String Tomrader(String inText)
	{
		/**
		 * En buffer som inneh�ller inTexten s� vi sedan kan manipulera den.
		 */
		StringBuffer htmlText = new StringBuffer(inText);

		int i = 0;
		
		// Loopa igenom tills bufferten �r slut.
		while ( i < htmlText.length()-1 )
		{
			// Hittar vi ett nyrads-tecken s� kollar vi �ven om n�sta rad b�rjar p� ett nyrads-tecken.
			if ( htmlText.charAt(i) == '\n' )
			{
				if ( htmlText.charAt(i+1)	== '\n' )
				{
					// I s� fall tar vi bort den raden.
					htmlText.deleteCharAt(i+1);
					i--;
				}
			}
			i++;
		}
		
		// returnera bufferten som en str�ng.
		return htmlText.toString();
	}

	/**
	 * Spaces �r en funktion som tar emot en str�ng och tar sedan och ers�tter alla &nbsp; med ett mellanslag.
	 *
	 * @param inText Str�ngen som &nbsp; ska ers�ttas i.
	 *
	 * @return Returnerar den f�rdiga str�ngen med alla &nbsp; texter ers�tta med ett space.
	 */
	public static String Spaces(String inText)
	{	
		/**
		 * En buffert som inneh�ller inTexten s� vi sedan kan manipulera den.
		 */
		StringBuffer htmlText = new StringBuffer(inText);

		int i = 0;

		// Loopa igenom tills bufferten �r slut.
		while ( i < htmlText.length()-5 )
		{
			// Vi letar efter "&nbsp;" i bufferten
			if ( (htmlText.substring(i,i+6)).equals("&nbsp;"))
			{
				// Hittar vi den s� l�gger vi till ett mellanslag p� tecknets position.
				htmlText.setCharAt(i,' ');

				// Vi tar sedan bort resterande tecken i &nbsp;
				for (int j = 1; j < 6; j++)
					htmlText.deleteCharAt(i+1);
			}
			i++;
		}
		
		// returnera bufferten som en str�ng.
		return htmlText.toString();
	}
	
	/**
	 * Mall �r en funkition som tar emot en str�ng f�r att sedan g�ra om det till en xHTML mall.
	 *
	 * @param inText Str�ngen som ska finnas inuti xHTML mallens body taggar.
	 *
	 * @return Returnerar den f�rdiga str�ngen.
	 */
	public static String Mall(String inText)
	{
		/**
		 * En buffert som inneh�ller inTexten s� vi sedan kan manipulera den.
		 */
		StringBuffer xhtml = new StringBuffer(inText);
		
		// Skapar v�r prefix str�ng
		String prefix = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n                    " +
						"       \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" +
						"\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
						"<head>\n	<title>Din sida</title>\n</head>\n<body>\n	";
						
		// Skapar v�r suffix str�ng.
		String suffix = "\n</body>\n</html>";
		
		// S�tter in prefix str�ngen f�rst i bufferten
		xhtml.insert(0, prefix);
		
		// S�tter in suffix str�ngen sist i bufferten.
		xhtml.append(suffix);

		// returnera bufferten som en str�ng.
		return xhtml.toString();
	}
}
