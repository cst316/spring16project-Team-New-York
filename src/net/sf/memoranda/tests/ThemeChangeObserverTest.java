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
	private AppFrame testFrame = new AppFrame();
	private WorkPanel testWorkPanel = new WorkPanel();
	private DailyItemsPanel dip = new DailyItemsPanel(testWorkPanel);
	private EventsPanel testEventsPanel = new EventsPanel(dip);
	private AgendaPanel testAgendaPanel = new AgendaPanel(dip);
	private TaskPanel testTaskPanel = new TaskPanel(dip);
	private NotesControlPanel testNotesControlPanel = new NotesControlPanel();
	private NotesListPanel testNotesListPanel = new NotesListPanel();
	private ProjectsPanel testProjectsPanel = new ProjectsPanel();
	private ResourcesPanel testResourcesPanel = new ResourcesPanel();
	
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
	public void test() 
	{
		workPanelBackground = testWorkPanel.backgroundColorIndicator;
		workPanelForeground = testWorkPanel.foregroundColorIndicator;
		eventsPanelBackground = testEventsPanel.backgroundColorIndicator;
		eventsPanelForeground = testEventsPanel.foregroundColorIndicator;
		agendaPanelBackground = testAgendaPanel.backgroundColorIndicator;
		agendaPanelForeground = testAgendaPanel.foregroundColorIndicator;
		taskPanelBackground = testTaskPanel.backgroundColorIndicator;
		taskPanelForeground = testTaskPanel.foregroundColorIndicator;
		notesControlPanelBackground = testNotesControlPanel.backgroundColorIndicator;
		notesControlPanelForeground = testNotesControlPanel.foregroundColorIndicator;
		notesListPanelBackground = testNotesListPanel.backgroundColorIndicator;
		notesListPanelForeground = testNotesListPanel.foregroundColorIndicator;
		projectsPanelBackground = testProjectsPanel.backgroundColorIndicator;
		projectsPanelForeground = testProjectsPanel.foregroundColorIndicator;
		resourcesPanelBackground = testResourcesPanel.backgroundColorIndicator;
		resourcesPanelForeground = testResourcesPanel.foregroundColorIndicator;
		
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
		
		workPanelBackground2 = testWorkPanel.backgroundColorIndicator;
		workPanelForeground2 = testWorkPanel.foregroundColorIndicator;
		eventsPanelBackground2 = testEventsPanel.backgroundColorIndicator;
		eventsPanelForeground2 = testEventsPanel.foregroundColorIndicator;
		agendaPanelBackground2 = testAgendaPanel.backgroundColorIndicator;
		agendaPanelForeground2 = testAgendaPanel.foregroundColorIndicator;
		taskPanelBackground2 = testTaskPanel.backgroundColorIndicator;
		taskPanelForeground2 = testTaskPanel.foregroundColorIndicator;
		notesControlPanelBackground2 = testNotesControlPanel.backgroundColorIndicator;
		notesControlPanelForeground2 = testNotesControlPanel.foregroundColorIndicator;
		notesListPanelBackground2 = testNotesListPanel.backgroundColorIndicator;
		notesListPanelForeground2 = testNotesListPanel.foregroundColorIndicator;
		projectsPanelBackground2 = testProjectsPanel.backgroundColorIndicator;
		projectsPanelForeground2 = testProjectsPanel.foregroundColorIndicator;
		resourcesPanelBackground2 = testResourcesPanel.backgroundColorIndicator;
		resourcesPanelForeground2 = testResourcesPanel.foregroundColorIndicator;
		
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