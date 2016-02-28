package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.ui.WorkPanel;

 
public class ThemeChangeObserverTest 
{
	private static AppFrame testFrame = null;
	private static JButton testNightThemeChangeButton = null;
	private static JButton testDayThemeChangeButton = null;
	private static WorkPanel testWorkPanel = null;
	java.awt.Color background = null;
	
	@Before
	public void setUp() throws Exception 
	{
		testFrame = new AppFrame();
		testNightThemeChangeButton = new JButton();
		testNightThemeChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuThemeNightTheme_actionPerformed(e);
            }
			private void jMenuThemeNightTheme_actionPerformed(ActionEvent e) {
				testFrame.setAppTheme("night");	
			}
        });
		testDayThemeChangeButton = new JButton();
		testDayThemeChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuThemeDayTheme_actionPerformed(e);
            }
			private void jMenuThemeDayTheme_actionPerformed(ActionEvent e) {
				testFrame.setAppTheme("night");	
			}
        });
		testFrame.add(testNightThemeChangeButton);
		testFrame.add(testDayThemeChangeButton);		
		testWorkPanel = new WorkPanel();
	}

	@Test
	public void test() 
	{
		//assertFalse(testWorkPanel.getBackground() == Color.WHITE);
		background = testWorkPanel.getBackground();
		assertFalse(Color.white.equals(background));
		System.out.println("Color is: " + background.toString());
		testFrame.setAppTheme("night");
		background = testWorkPanel.getBackground();
		System.out.println("Color is: " + background.toString());
		assertFalse(Color.DARK_GRAY.equals(background));
	}

}
