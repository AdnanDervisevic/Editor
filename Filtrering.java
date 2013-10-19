/**
 * Filtrering, här finns funktioner för att flitrera bort HTML taggar, ta bort tomrader
 * och även lägga in en xHTML mall.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class Filtrering
{
	/**
	 * Taggar är en funktion som tar bort alla < och > tecken i input strängen.
	 *
	 * @param inText Strängen som taggarna ska tas bort ifrån.
	 */
	public static void Taggar(String inText)
	{
		/**
		 * En buffer där man läser in texten från textarean (inparameter) som sedan kan editeras och skrivas ut, man kan appenda med suffix och prefix t.ex.
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
		
		// Ändrar texten i texteditorns text area.		
		TextEditor.textArea.setText(htmlText.toString());
		htmlText = null; // Gör strängbufferten tillgänglig för skräpsamlaren
	}

	/**
	 * Tomrader är en funktion som tar emot en sträng och tar sedan bort alla
	 * tomma rader.
	 *
	 * @param inText Strängen som taggarna ska tas bort ifrån.
	 *
	 * @return Returnerar den färdiga strängen med alla taggar bortagna.
	 */
	public static String Tomrader(String inText)
	{
		/**
		 * En buffer som innehåller inTexten så vi sedan kan manipulera den.
		 */
		StringBuffer htmlText = new StringBuffer(inText);

		int i = 0;
		
		// Loopa igenom tills bufferten är slut.
		while ( i < htmlText.length()-1 )
		{
			// Hittar vi ett nyrads-tecken så kollar vi även om nästa rad börjar på ett nyrads-tecken.
			if ( htmlText.charAt(i) == '\n' )
			{
				if ( htmlText.charAt(i+1)	== '\n' )
				{
					// I så fall tar vi bort den raden.
					htmlText.deleteCharAt(i+1);
					i--;
				}
			}
			i++;
		}
		
		// returnera bufferten som en sträng.
		return htmlText.toString();
	}

	/**
	 * Spaces är en funktion som tar emot en sträng och tar sedan och ersätter alla &nbsp; med ett mellanslag.
	 *
	 * @param inText Strängen som &nbsp; ska ersättas i.
	 *
	 * @return Returnerar den färdiga strängen med alla &nbsp; texter ersätta med ett space.
	 */
	public static String Spaces(String inText)
	{	
		/**
		 * En buffert som innehåller inTexten så vi sedan kan manipulera den.
		 */
		StringBuffer htmlText = new StringBuffer(inText);

		int i = 0;

		// Loopa igenom tills bufferten är slut.
		while ( i < htmlText.length()-5 )
		{
			// Vi letar efter "&nbsp;" i bufferten
			if ( (htmlText.substring(i,i+6)).equals("&nbsp;"))
			{
				// Hittar vi den så lägger vi till ett mellanslag på tecknets position.
				htmlText.setCharAt(i,' ');

				// Vi tar sedan bort resterande tecken i &nbsp;
				for (int j = 1; j < 6; j++)
					htmlText.deleteCharAt(i+1);
			}
			i++;
		}
		
		// returnera bufferten som en sträng.
		return htmlText.toString();
	}
	
	/**
	 * Mall är en funkition som tar emot en sträng för att sedan göra om det till en xHTML mall.
	 *
	 * @param inText Strängen som ska finnas inuti xHTML mallens body taggar.
	 *
	 * @return Returnerar den färdiga strängen.
	 */
	public static String Mall(String inText)
	{
		/**
		 * En buffert som innehåller inTexten så vi sedan kan manipulera den.
		 */
		StringBuffer xhtml = new StringBuffer(inText);
		
		// Skapar vår prefix sträng
		String prefix = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n                    " +
						"       \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" +
						"\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
						"<head>\n	<title>Din sida</title>\n</head>\n<body>\n	";
						
		// Skapar vår suffix sträng.
		String suffix = "\n</body>\n</html>";
		
		// Sätter in prefix strängen först i bufferten
		xhtml.insert(0, prefix);
		
		// Sätter in suffix strängen sist i bufferten.
		xhtml.append(suffix);

		// returnera bufferten som en sträng.
		return xhtml.toString();
	}
}
