
public class Event implements Comparable<Event> {
	private String title, dateTime, location;
	private Contact contact;

	public Event(String title, Contact contact, String dateTime, String location) {
		this.title = title;
		this.dateTime = dateTime;
		this.location = location;
		this.contact = contact;
	}

	public int compareTo(Event otherEvent) {
		return this.title.compareTo(otherEvent.title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Contact getContact() {
		return contact;
	}

	public String getContactName() {
		return contact.name;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void display() {
		System.out.println("Event title: " + title);
		System.out.println("Contact Name:  " + getContactName());
		System.out.println("Event date and time (MM/DD/YYYY HH:MM): " + dateTime);
		System.out.println("Event Location:  " + location);

	}

}