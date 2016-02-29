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
	private static CalendarDate cd3; 
	private static CalendarDate cd4; 
	/// Create Events to test 
	private static Event ev;
	private static Event ev2;
	private static Event ev3;
	private static Event ev4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		eventDate = new CalendarDate(7,5,2016);
		cd1 = new CalendarDate(11,2,2016);
		cd2 = new CalendarDate(12,3, 2016);
		cd3 = new CalendarDate(10,4, 2017);
		cd4 = new CalendarDate(29,2, 2016);
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
	
	/*
	 * Note: It is very difficult to limit test getValuesForNumberOfEventsTest()
	 * because I don't know when you the teacher/TA is running this test. Leap years
	 * aren't accounted for but I could added it in by adding an additional layer
	 * to the getNumberOfEventsForTheYear() method but IDK it's just to messy.
	 */
	
	@Test
	public void  getValuesForNumberOfEventsTest() {
		ev3 = EventsManager.createEvent(cd3, 6, 15, 11, 45, "Homework");
		ev4 = EventsManager.createEvent(cd4, 4, 5, 10, 30, "Work");
		
		assertEquals(2, EventsManager.getNumberOfEventsForTheYear());//two events in the next 365 days
		assertEquals(1, EventsManager.getNumberOfEventsForTheMonth());//one event this month
		assertEquals(0, EventsManager.getNumberOfEventsForToday());//one event today
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
	/*
	@Test
	public void recoverDeletedEvents()  {
		/// Make sure the Vector is empty 
		EventsManager.flushEventsVector();
		/// Make sure it's empty 
		assertEquals(0,EventsManager.getNumberOfStoredItems());
		/// Create New Event because vector flushEventsVector() makes vector empty 
		ev2 = EventsManager.createEvent(cd2, 10, 30, 11, 00, "Doctor Visit");
		///Make sure Event isn't null
		assertNotNull(EventsManager.getEvent(cd2, 10, 30, 11, 00));
		/// Store the Event 
		EventsManager.storeDeletedEvents(ev2);
		/// And store the Event date
		EventsManager.storeCalendarDate(cd2);
		/// Makes sure the vector isn't empty 
		assertEquals(1,EventsManager.getNumberOfStoredItems());
		/// Remove the Event 
		EventsManager.removeEvent(ev2);
		/// Check to make sure the Event is not in EventsManager
		assertNull(EventsManager.getEvent(cd2, 10, 30, 11, 00));
		/// Recovers deleted Events 
		EventsManager.recoverDeletedEvents();
		/// Check to see if there are 0 items in vector after recovering the lost ones 
		assertEquals(0,EventsManager.getNumberOfStoredItems()); 
		/// Check to see if event was recovered. 
		assertNotNull(EventsManager.getEvent(cd2, 10, 30, 11, 00));		
	}*/
	
}