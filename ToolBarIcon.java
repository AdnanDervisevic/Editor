// Importerar nödvändiga paket.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ToolBarIcon är en förenklad AbstractAction klass som 
 * har en konstruktor som tar emot en sträng, en ikon och en beskrivning.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class ToolBarIcon extends AbstractAction
{
	/**
	 * Konstruktor för CustomAction
	 *
	 * @param text Texten som ska synas.
	 * @param icon Ikonen som ska synas.
	 * @param description Texten som ska synas när man till exempel håller musen över.
	 */
	public ToolBarIcon(String text, Icon icon, String description)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, description);
	}
	
	/**
	 * Default actionPerformed, om man inte anger en egen actionPerformed så ska inget hända.
	 *
	 * @param e Vårt ActionEvent objekt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) { }
}