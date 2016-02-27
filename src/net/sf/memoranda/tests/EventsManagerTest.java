package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Vector;

import net.sf.memoranda.EventsManager;
import net.sf.memoranda.Event; 
import net.sf.memoranda.date.CalendarDate;

public class EventsManagerTest {

	/// Holds the deleted Events
	public static Vector<Event> storage = new Vector<Event>(); 
	/// Holds the CalendarDate of the Events
	private static CalendarDate eventDate;
	private static CalendarDate cd1;
	private static CalendarDate cd2; 
	/// Create Events to test 
	private static Event ev;
	private static Event ev2; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		eventDate = new CalendarDate(7,5,2016);
		cd1 = new CalendarDate(11,2,2016);
		cd2 = new CalendarDate(12,3, 2016);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNumberOfStoredItemsTest()
	{
		assertEquals(0,EventsManager.getNumberOfStoredItems());
		/// Lets add an event to storage
		ev = EventsManager.createEvent(eventDate, 8, 23, 9, 30, "School Play");
		/// Let's store a new event to see if Number of Stored Items has changed.  
		EventsManager.storeDeletedEvents(ev);
		/// Makes sure the vector isn't empty 
		assertEquals(1,EventsManager.getNumberOfStoredItems());
	}
	
	@Test
	public void getCalendarDateTest()
	{
		/// Stores the Event 
		EventsManager.storeCalendarDate(cd1);
		/// Checks to make sure the Event is returned
		assertEquals(cd1, EventsManager.getCalendarDate());
	} 

	
	@Test
	public void  getStoredEventsTest() {
		 /// Checks to make sure that a vector is being returned
		assertNotNull( EventsManager.getStoredEvents());
		/// Checks to make sure the vector isn't empty
		assertEquals(1,EventsManager.getNumberOfStoredItems());
		 /// Checks to see if the event (ev) was stored in the vector
		 assertEquals(ev, EventsManager.getStoredEvents().get(0));
	}
	
	@Test
	public void  flushEventsVector()  {
		/// Should make Vector empty (); 
		EventsManager.flushEventsVector();
		/// Check to see if there are 0 items in vector
		assertEquals(0,EventsManager.getNumberOfStoredItems());	
	}
	
	@Test
	public void recoverDeletedEvents()  {
		/// Make sure the Vector is empty 
		EventsManager.flushEventsVector();
		/// Create New Event because vector flushEventsVector() makes vector empty 
		ev2 = EventsManager.createEvent(cd2, 10,30, 11, 00, "Doctor Visit");
		/// Store the Event 
		EventsManager.storeDeletedEvents(ev2);
		/// Makes sure the vector isn't empty 
		assertEquals(1,EventsManager.getNumberOfStoredItems());
		/// Remove the Event 
		EventsManager.removeEvent(ev2);
		/// Check to make sure the Event is not in EventsManager
		assertNull(EventsManager.getEvent(cd2, 10, 30, 11, 00));
		/// Recovers deleted Events 
		EventsManager.recoverDeletedEvents();
		/// Check to see if event was recovered. 
		//assertNotNull(EventsManager.getEvent(cd2, 10, 30, 11, 00));
		/// Check to see if there are 0 items in vector after recovering the lost ones 
		assertEquals(0,EventsManager.getNumberOfStoredItems());
		
	}
	
}