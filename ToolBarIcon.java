// Importerar n�dv�ndiga paket.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ToolBarIcon �r en f�renklad AbstractAction klass som 
 * har en konstruktor som tar emot en str�ng, en ikon och en beskrivning.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class ToolBarIcon extends AbstractAction
{
	/**
	 * Konstruktor f�r CustomAction
	 *
	 * @param text Texten som ska synas.
	 * @param icon Ikonen som ska synas.
	 * @param description Texten som ska synas n�r man till exempel h�ller musen �ver.
	 */
	public ToolBarIcon(String text, Icon icon, String description)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, description);
	}
	
	/**
	 * Default actionPerformed, om man inte anger en egen actionPerformed s� ska inget h�nda.
	 *
	 * @param e V�rt ActionEvent objekt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) { }
}