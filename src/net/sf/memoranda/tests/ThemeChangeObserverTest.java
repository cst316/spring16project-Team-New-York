package net.sf.memoranda.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import net.sf.memoranda.ui.AgendaPanel;
import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.ui.DailyItemsPanel;
import net.sf.memoranda.ui.EventsPanel;
import net.sf.memoranda.ui.NotesControlPanel;
import net.sf.memoranda.ui.NotesListPanel;
import net.sf.memoranda.ui.ProjectsPanel;
import net.sf.memoranda.ui.ResourcesPanel;
import net.sf.memoranda.ui.TaskPanel;
import net.sf.memoranda.ui.WorkPanel;

 
public class ThemeChangeObserverTest 
{
	private static AppFrame testFrame = new AppFrame();
	
	static String workPanelBackground = null;
	static String workPanelForeground = null;
	static String workPanelBackground2 = null;
	static String workPanelForeground2 = null;
	
	static String eventsPanelBackground = null;
	static String eventsPanelForeground = null;
	static String eventsPanelBackground2 = null;
	static String eventsPanelForeground2 = null;
	
	static String agendaPanelBackground = null;
	static String agendaPanelForeground = null;
	static String agendaPanelBackground2 = null;
	static String agendaPanelForeground2 = null;
	
	static String taskPanelBackground = null;
	static String taskPanelForeground = null;
	static String taskPanelBackground2 = null;
	static String taskPanelForeground2 = null;
	
	static String notesControlPanelBackground = null;
	static String notesControlPanelForeground = null;
	static String notesControlPanelBackground2 = null;
	static String notesControlPanelForeground2 = null;
	
	static String notesListPanelBackground = null;
	static String notesListPanelForeground = null;
	static String notesListPanelBackground2 = null;
	static String notesListPanelForeground2 = null;
	
	static String projectsPanelBackground = null;
	static String projectsPanelForeground = null;
	static String projectsPanelBackground2 = null;
	static String projectsPanelForeground2 = null;
	
	static String resourcesPanelBackground = null;
	static String resourcesPanelForeground = null;
	static String resourcesPanelBackground2 = null;
	static String resourcesPanelForeground2 = null;
	
	static String black = "java.awt.Color[r=0,g=0,b=0]";
	static String white = "java.awt.Color[r=255,g=255,b=255]";
	static String darkGrey = "java.awt.Color[r=64,g=64,b=64]";



	@Test
	public void testTheme() 
	{
		workPanelBackground = WorkPanel.backgroundColorIndicator;
		workPanelForeground = WorkPanel.foregroundColorIndicator;
		eventsPanelBackground = EventsPanel.backgroundColorIndicator;
		eventsPanelForeground = EventsPanel.foregroundColorIndicator;
		agendaPanelBackground = AgendaPanel.backgroundColorIndicator;
		agendaPanelForeground = AgendaPanel.foregroundColorIndicator;
		taskPanelBackground = TaskPanel.backgroundColorIndicator;
		taskPanelForeground = TaskPanel.foregroundColorIndicator;
		notesControlPanelBackground = NotesControlPanel.backgroundColorIndicator;
		notesControlPanelForeground = NotesControlPanel.foregroundColorIndicator;
		notesListPanelBackground = NotesListPanel.backgroundColorIndicator;
		notesListPanelForeground = NotesListPanel.foregroundColorIndicator;
		projectsPanelBackground = ProjectsPanel.backgroundColorIndicator;
		projectsPanelForeground = ProjectsPanel.foregroundColorIndicator;
		resourcesPanelBackground = ResourcesPanel.backgroundColorIndicator;
		resourcesPanelForeground = ResourcesPanel.foregroundColorIndicator;
		
		//test initial state
		assertTrue(workPanelBackground.equalsIgnoreCase(white));
		assertTrue(workPanelForeground.equalsIgnoreCase(black));
		assertTrue(eventsPanelBackground.equalsIgnoreCase(white));
		assertTrue(eventsPanelForeground.equalsIgnoreCase(black));
		assertTrue(agendaPanelBackground.equalsIgnoreCase(white));
		assertTrue(agendaPanelForeground.equalsIgnoreCase(black));
		assertTrue(taskPanelBackground.equalsIgnoreCase(white));
		assertTrue(taskPanelForeground.equalsIgnoreCase(black));
		assertTrue(notesControlPanelBackground.equalsIgnoreCase(white));
		assertTrue(notesControlPanelForeground.equalsIgnoreCase(black));
		assertTrue(notesListPanelBackground.equalsIgnoreCase(white));
		assertTrue(notesListPanelForeground.equalsIgnoreCase(black));
		assertTrue(projectsPanelBackground.equalsIgnoreCase(white));
		assertTrue(projectsPanelForeground.equalsIgnoreCase(black));
		assertTrue(resourcesPanelBackground.equalsIgnoreCase(white));
		assertTrue(resourcesPanelForeground.equalsIgnoreCase(black));

	    /////////////////////////////////////////////////////////////////////
		testFrame.setAppTheme("night");
		/////////////////////////////////////////////////////////////////////
		
		workPanelBackground2 = WorkPanel.backgroundColorIndicator;
		workPanelForeground2 = WorkPanel.foregroundColorIndicator;
		eventsPanelBackground2 = EventsPanel.backgroundColorIndicator;
		eventsPanelForeground2 = EventsPanel.foregroundColorIndicator;
		agendaPanelBackground2 = AgendaPanel.backgroundColorIndicator;
		agendaPanelForeground2 = AgendaPanel.foregroundColorIndicator;
		taskPanelBackground2 = TaskPanel.backgroundColorIndicator;
		taskPanelForeground2 = TaskPanel.foregroundColorIndicator;
		notesControlPanelBackground2 = NotesControlPanel.backgroundColorIndicator;
		notesControlPanelForeground2 = NotesControlPanel.foregroundColorIndicator;
		notesListPanelBackground2 = NotesListPanel.backgroundColorIndicator;
		notesListPanelForeground2 = NotesListPanel.foregroundColorIndicator;
		projectsPanelBackground2 = ProjectsPanel.backgroundColorIndicator;
		projectsPanelForeground2 = ProjectsPanel.foregroundColorIndicator;
		resourcesPanelBackground2 = ResourcesPanel.backgroundColorIndicator;
		resourcesPanelForeground2 = ResourcesPanel.foregroundColorIndicator;
		
		assertTrue(workPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(workPanelForeground2.equalsIgnoreCase(white));
		assertTrue(eventsPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(eventsPanelForeground2.equalsIgnoreCase(white));
		assertTrue(agendaPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(agendaPanelForeground2.equalsIgnoreCase(white));
		assertTrue(taskPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(taskPanelForeground2.equalsIgnoreCase(white));
		assertTrue(notesControlPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(notesControlPanelForeground2.equalsIgnoreCase(white));
		assertTrue(notesListPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(notesListPanelForeground2.equalsIgnoreCase(white));
		assertTrue(projectsPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(projectsPanelForeground2.equalsIgnoreCase(white));
		assertTrue(resourcesPanelBackground2.equalsIgnoreCase(darkGrey));
		assertTrue(resourcesPanelForeground2.equalsIgnoreCase(white));		
	}

}
