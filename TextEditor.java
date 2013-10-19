// Importerar n�dv�ndiga paket.
import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.event.*;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.text.DefaultEditorKit;

/**
 * TextEditor, h�r �r sj�lva main klassen som hela programmet k�rs ifr�n.
 *
 * @author Adnan Dervisevic & Tobias Oskarsson
 * @version 1.00 2013/01/13
 */
public class TextEditor extends JFrame
{
	/**
	 * serialversionID s� den inte genereras automatiskt av programmet
	 */
	private static final long serialVersionUID = 1L;
	public static JTextArea textArea = new JTextArea();
	private boolean edited = false;
	private String filename = "namnl�s";
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	private JColorChooser colorChooser = new JColorChooser();
	
	// Menybaren och dess menyer.
	private JMenuBar menuBar = new JMenuBar();
	private JMenu arkivMenu = new JMenu("Arkiv");
	private JMenu RedigeraMenu = new JMenu("Redigera");
	private JMenu xHTMLMenu = new JMenu("xHTML");
	private JMenu KomprimeringMenu = new JMenu("Komprimering");
	private JMenu KrypteringMenu = new JMenu("Kryptering");
	private JMenu StartaMenu = new JMenu("Starta");
	private JMenu HjalpMenu = new JMenu("Hj�lp");
	
	// Alla aktions som �ven fungerar som meny objekt f�r varje meny.
	private Action nyttMenuItem = new AbstractAction("Nytt")
	{
		public void actionPerformed(ActionEvent e)
		{
			toolBarNew.actionPerformed(e);
		}
	};
	private Action oppnaMenuItem = new AbstractAction("�ppna")
	{
		public void actionPerformed(ActionEvent e)
		{
			toolBarOpen.actionPerformed(e);
		}
	};
	private Action sparaMenuItem = new AbstractAction("Spara")
	{
		public void actionPerformed(ActionEvent e)
		{
			toolBarSave.actionPerformed(e);
		}
	};
	private Action sparaSomMenuItem = new AbstractAction("Spara som...")
	{
		public void actionPerformed(ActionEvent e)
		{
			saveFileDialog();
		}
	};
	private Action skrivUtMenuItem = new AbstractAction("Skriv ut")
	{
		public void actionPerformed(ActionEvent e)
		{
			toolBarPrint.actionPerformed(e);
		}
	};
	private Action avslutaMenuItem = new AbstractAction("Avsluta")
	{
		public void actionPerformed(ActionEvent e)
		{
			saveOld();
			System.exit(0);
		}
	};
	private Action rensaMenuItem = new AbstractAction("Rensa")
	{
		public void actionPerformed(ActionEvent e)
		{
			if (JOptionPane.showConfirmDialog(null, "�r du s�ker p� att du vill rensa filen?", "Varning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				textArea.setText("");
		}
	};
	private Action klippUtMenuItem = textArea.getActionMap().get(DefaultEditorKit.cutAction);
	private Action kopieraMenuItem = textArea.getActionMap().get(DefaultEditorKit.copyAction);
	private Action klistraInMenuItem = textArea.getActionMap().get(DefaultEditorKit.pasteAction);
	private Action teckenfargMenuItem = new AbstractAction("Teckenf�rg")
	{
		public void actionPerformed(ActionEvent e)
		{
			Color JColor = new Color(0, 0, 0);
			textArea.setForeground(JColor = colorChooser.showDialog(null, "Teckenf�rg", Color.BLACK));
		}
	};
	private Action taBortTaggarMenuItem = new AbstractAction("Ta bort taggar")
	{
		public void actionPerformed(ActionEvent e)
		{
			Filtrering.Taggar(textArea.getText());
		}
	};
	private Action taBortExtraBlankteckenMenuItem = new AbstractAction("Ta bort extra blanktecken")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Filtrering.Spaces(textArea.getText()));
		}
	};
	private Action taBortExtraTomraderMenuItem = new AbstractAction("Ta bort extra tomrader")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Filtrering.Tomrader(textArea.getText()));
		}
	};
	private Action infogaMallMenuItem = new AbstractAction("Infoga xHTML-mall")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Filtrering.Mall(textArea.getText()));
		}
	};
	private Action komprimeringMenuItem = new AbstractAction("Komprimera texten")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Komprimering.komprimera(textArea.getText()));
		}
	};
	private Action dekomprimeraMenuItem = new AbstractAction("Dekomprimera texten")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Komprimering.dekomprimera(textArea.getText()));
		}
	};
	private Action krypteraMenuItem = new AbstractAction("Kryptera texten")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Krypto.kryptera(textArea.getText()));
		}
	};
	private Action dekrypteraMenuItem = new AbstractAction("Dekryptera texten")
	{
		public void actionPerformed(ActionEvent e)
		{
			textArea.setText(Krypto.dekryptera(textArea.getText()));
		}
	};
	private Action andraNyckelMenuItem = new AbstractAction("�ndra nyckel")
	{
		public void actionPerformed(ActionEvent e)
		{
			Krypto.nyckel();
		}
	};
	private Action kommandotolkenMenuItem = new AbstractAction("Kommandotolken (cmd.exe)")
	{
		public void actionPerformed(ActionEvent e)
		{
			Program.Starta("CMD");
		}
	};
	private Action kalkylatornMenuItem = new AbstractAction("Kalkylatorn (calc.exe)")
	{
		public void actionPerformed(ActionEvent e)
		{
			Program.Starta("Calc");
		}
	};
	private Action omMenuItem = new AbstractAction("Om")
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, "Utvecklad av Adnan Dervisevic och Tobias Oskarsson \nProjekt f�r Javakurs under l�rare Malin och G�sta\nDatateknisk Systemutveckling 2011-2014\nH�gskolan V�st, Trollh�ttan");
		}
	};
	private Action hjalpMenuItem = new AbstractAction("Hj�lp")
	{
		public void actionPerformed(ActionEvent e)
		{
			help();
		}
	};
	private KeyListener textAreaKeyListener = new KeyAdapter()
	{
		public void keyPressed(KeyEvent e)
		{
			edited = true;
			toolBarSave.setEnabled(true);
			sparaMenuItem.setEnabled(true);
			sparaSomMenuItem.setEnabled(true);
		}
	};
	private Action bytFont = new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			String teckensnitt = (String) toolBarFonts.getSelectedItem();
			textArea.setFont(new Font(teckensnitt, Font.PLAIN, 16));
		}
	};

	
	// JToolbaren och dess items.
	private JToolBar toolBar = new JToolBar();

	private ToolBarIcon toolBarNew = new ToolBarIcon("Nytt", new ImageIcon("images/new.gif"), "Nytt dokument")
	{
		public void actionPerformed(ActionEvent e)
		{
			saveOld();
			textArea.setText("");
			filename = "namnl�s";
			setTitle(filename);
			sparaMenuItem.setEnabled(false);
			sparaSomMenuItem.setEnabled(false);
			toolBarSave.setEnabled(false);
		}
	};
	private ToolBarIcon toolBarOpen = new ToolBarIcon("Oppna", new ImageIcon("images/open.gif"), "�ppna dokument")
	{
		public void actionPerformed(ActionEvent e)
		{
			saveOld();
			
			if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				readFile(dialog.getSelectedFile().getAbsolutePath());
			
			sparaSomMenuItem.setEnabled(true);
		}
	};
	private ToolBarIcon toolBarSave = new ToolBarIcon("Spara", new ImageIcon("images/save.gif"), "Spara dokument")
	{
		public void actionPerformed(ActionEvent e)
		{
			if (!filename.equals("namnl�s"))
				saveFile(filename);
			else
				saveFileDialog();
		}
	};
	private ToolBarIcon toolBarCut = new ToolBarIcon("KlippUt", new ImageIcon("images/cut.gif"), "Klipp ut")
	{
		public void actionPerformed(ActionEvent e)
		{
			klippUtMenuItem.actionPerformed(e);
		}
	};
	private ToolBarIcon toolBarCopy = new ToolBarIcon("Kopiera", new ImageIcon("images/copy.gif"), "Kopiera")
	{
		public void actionPerformed(ActionEvent e)
		{
			kopieraMenuItem.actionPerformed(e);
		}
	};
	private ToolBarIcon toolBarPaste = new ToolBarIcon("KlistraIn", new ImageIcon("images/paste.gif"), "Klistra in")
	{
		public void actionPerformed(ActionEvent e)
		{
			klistraInMenuItem.actionPerformed(e);
		}
	};
	private ToolBarIcon toolBarBiggerTxt = new ToolBarIcon("StorreText", new ImageIcon("images/biggerTxt.gif"), "St�rre text")
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int size = textArea.getFont().getSize() + 5;
				textArea.setFont(new Font(textArea.getFont().getFontName(), textArea.getFont().getStyle(), size));
			} catch (Exception ex)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	private ToolBarIcon toolBarSmallerTxt = new ToolBarIcon("MindreText", new ImageIcon("images/smallerTxt.gif"), "Mindre text")
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int size = textArea.getFont().getSize() - 5;
				textArea.setFont(new Font(textArea.getFont().getFontName(), textArea.getFont().getStyle(), size));
			} catch (Exception ex)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	private ToolBarIcon toolBarPrint = new ToolBarIcon("SkrivUt", new ImageIcon("images/print.gif"), "Skriv ut")
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				textArea.print();
			} catch(Exception ex)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	private String[] availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 	
	private JComboBox toolBarFonts = new JComboBox(availableFonts);
	
	/**
	 * TextEditor �r konstruktorn f�r hela programmet.
	 */
	public TextEditor()
	{		
		// �ndra layout f�r JFramen.
		this.setLayout(new BorderLayout());

		// L�gg till menubaren
		setJMenuBar(menuBar);
		
		// L�gg till arkiv menyn samt alla menu objekten som tillh�r arkiv menyn.
		this.menuBar.add(this.arkivMenu);
		this.arkivMenu.add(this.nyttMenuItem);
		this.arkivMenu.add(this.oppnaMenuItem);
		this.arkivMenu.add(this.sparaMenuItem);
		this.sparaMenuItem.setEnabled(false);
		this.arkivMenu.add(this.sparaSomMenuItem);
		this.sparaSomMenuItem.setEnabled(false);
		this.arkivMenu.add(this.skrivUtMenuItem);
		this.arkivMenu.add(this.avslutaMenuItem);
		
		// L�gg till redigera menyn samt alla menu objekten som tillh�r redigera menyn.
		this.menuBar.add(this.RedigeraMenu);
		this.RedigeraMenu.add(this.rensaMenuItem);
		this.RedigeraMenu.add(this.klippUtMenuItem);
		this.RedigeraMenu.getItem(1).setText("Klipp ut");
		this.RedigeraMenu.add(this.kopieraMenuItem);
		this.RedigeraMenu.getItem(2).setText("Kopiera");
		this.RedigeraMenu.add(this.klistraInMenuItem);
		this.RedigeraMenu.getItem(3).setText("Klistra in");
		this.RedigeraMenu.add(this.teckenfargMenuItem);
		
		// L�gg till xHTML menyn samt alla menu objekten som tillh�r xHTML menyn.
		this.menuBar.add(this.xHTMLMenu);
		this.xHTMLMenu.add(this.taBortTaggarMenuItem);
		this.xHTMLMenu.add(this.taBortExtraBlankteckenMenuItem);
		this.xHTMLMenu.add(this.taBortExtraTomraderMenuItem);
		this.xHTMLMenu.add(this.infogaMallMenuItem);
		
		// L�gg till komprimering menyn samt alla menu objekten som tillh�r komprimering menyn.
		this.menuBar.add(this.KomprimeringMenu);
		this.KomprimeringMenu.add(this.komprimeringMenuItem);
		this.KomprimeringMenu.add(this.dekomprimeraMenuItem);
				
		// L�gg till kryptering menyn samt alla menu objekten som tillh�r kryptering menyn.
		this.menuBar.add(this.KrypteringMenu);
		this.KrypteringMenu.add(this.krypteraMenuItem);
		this.KrypteringMenu.add(this.dekrypteraMenuItem);
		this.KrypteringMenu.add(this.andraNyckelMenuItem);
				
		// L�gg till starta-menyn samt alla menu objekten som tillh�r starta-menyn.
		this.menuBar.add(this.StartaMenu);
		this.StartaMenu.add(this.kommandotolkenMenuItem);
		this.StartaMenu.add(this.kalkylatornMenuItem);
		
		// L�gg till hj�lp menyn samt alla menu objekten som tillh�r hj�lp menyn.
		this.menuBar.add(this.HjalpMenu);
		this.HjalpMenu.add(this.omMenuItem);
		this.HjalpMenu.add(this.hjalpMenuItem);

		this.add(this.toolBar, BorderLayout.NORTH);
		this.toolBar.add(this.toolBarNew);
		this.toolBar.add(this.toolBarOpen);
		this.toolBar.add(this.toolBarSave);
		this.toolBarSave.setEnabled(false);
		this.toolBar.addSeparator();
		this.toolBar.add(this.toolBarCut);
		this.toolBar.add(this.toolBarCopy);
		this.toolBar.add(this.toolBarPaste);
		this.toolBar.addSeparator();
		this.toolBar.add(this.toolBarBiggerTxt);
		this.toolBar.add(this.toolBarSmallerTxt);
		this.toolBar.addSeparator();
		this.toolBar.add(this.toolBarFonts);
		this.toolBar.addSeparator();
		this.toolBar.add(this.toolBarPrint);
		this.toolBarFonts.setAction(bytFont);

		this.textArea.setFont(new Font("Arial", Font.PLAIN, 16));
		this.textArea.addKeyListener(textAreaKeyListener);
		JScrollPane textScroll = new JScrollPane(this.textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(textScroll, BorderLayout.CENTER);
		
		// Fixar obligatoriska inst�llningar till jFramen.
		this.setTitle("Text Editor");
		this.setMinimumSize(new Dimension(800,600));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
	/**
	 * saveOld �r funktionen som fr�gar om man ska spara den gammla filen om den �r �ndrad.
	 */	
	private void saveOld()
	{
		if (this.edited)
		{
			if (JOptionPane.showConfirmDialog(this, "Ska filen " + filename + " sparas?", "Varning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				if (filename.equals("namnl�s"))
					this.saveFileDialog();
				else
					this.saveFile(filename);
			}
		}
	}
	
	/**
	 * saveFileDialog �r funktionen som tar upp en fildialog d�r man v�ljer namn
	 * p� filen och vart den ska sparas.
	 */
	private void saveFileDialog()
	{
		if (dialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath());
	}
	
	/**
	 * saveFile �r funktionen som sparar inneh�llet i textarean till en textfil.
	 *
	 * @param filename �r filnamnet p� filen.
	 */
	private void saveFile(String filename)
	{
		try
		{
			FileWriter fw = new FileWriter(filename);
			this.textArea.write(fw);
			fw.close();
			this.edited = false;
			this.sparaMenuItem.setEnabled(false);
			this.toolBarSave.setEnabled(false);
		} catch (IOException ex)
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Gick inte att spara filen.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * readFile �r funktionen som l�ser in en textfil och 
	 * l�gger inneh�llet i textArean.
	 *
	 * @param filename �r filnamnet p� filen som ska l�sas in.
	 */
	private void readFile(String filename)
	{
		try
		{
			FileReader fr = new FileReader(filename);
			this.textArea.read(fr, null);
			fr.close();
			this.filename = filename;
			this.setTitle(filename);
			this.edited = false;
		} catch (IOException ex)
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Det g�r inte att hitta filen: " + filename + ".", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * help �r funktionen som tar upp hj�lpf�nstret.
	 */
	public void help()
	{
		JFrame helpFrame = new JFrame();
		JEditorPane helpArea = new JEditorPane();
		helpFrame.add(helpArea);

		try
		{
			URL pageUrl = getClass().getResource("help.html");	// URL objekt som h�mtar html sidan
			helpArea.setPage(pageUrl);
		}
		catch (IOException he)
		{
			JOptionPane.showMessageDialog(helpFrame, "Hj�lpfilen saknas ");	// Vid fel (thrown exception) ska den rita upp det h�r felmeddelandet i helpFrame.
		}
		
		Color bgColor = new Color(131, 219, 255);
		
		// S�tter allt utseende p� hj�lpomr�det
		helpArea.setEditable(false);
		helpArea.setBackground(bgColor);
		helpFrame.setTitle("Hj�lp");
		helpFrame.setLocation(600,200);
		helpFrame.setSize(650,600); 
		helpFrame.setVisible(true);
	}
	
	/**
	 * Main funktionen startar sj�lva programmet.
	 *
	 * @param args �r argumenten man skickar med till main funktionen.
	 */
	public static void main(String[] args)
	{
		// Skapar ett nytt textEditor objekt.
		TextEditor textEditor = new TextEditor();
	}
}
