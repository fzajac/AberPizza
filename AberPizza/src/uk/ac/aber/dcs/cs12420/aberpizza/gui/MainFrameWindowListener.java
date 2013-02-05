package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/** 
 * Listener for the main window. 
 * It is used to set different operation on close
 * @author Filip Zajac (fiz)
 * @see MainFrame
 * @see Till#save()
 */
public class MainFrameWindowListener implements WindowListener
{
	private Till till; 
	private MainFrame frame;
	
	public MainFrameWindowListener(Till t, MainFrame f)
	{
		till = t; 
		frame = f;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	/**
	 * Save the till, dispose the frame, exit the application. 
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		till.save();
		frame.dispose();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}

}
