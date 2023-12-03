public class Event {
	private boolean isEvent;
	private String title;
	private String dateAndTime;
	private String location;
	private ContactBST contactBST;
	private Contact contact;
	
    // Constructor for appointments with a single contact
    public Event(boolean isEvent, String title, String dateAndTime, String location, Contact contact) {
        this.isEvent = isEvent;
        this.title = title;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.contact = contact;
        this.contactBST = null;
    }

    // Constructor for events with a set of contacts
    public Event(boolean isEvent, String title, String dateAndTime, String location, ContactBST contacts) {
        this.isEvent = isEvent;
        this.title = title;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.contact = null;
        this.contactBST = contacts;
    }

	public boolean isEvent() {
		return isEvent;
	}

	public void setEvent(boolean isEvent) {
		this.isEvent = isEvent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateTime() {
		return dateAndTime;
	}

	public String getContactName() {
		return contact.getName();
	}

	public void setDateTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ContactBST getContactBST() {
		return contactBST;
	}

	public void setContactBST(ContactBST contactBST) {
		this.contactBST = contactBST;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void display() {
	    System.out.println("\nEvent title: " + title);
	    System.out.println("Event date and time (MM/DD/YYYY HH:MM): " + dateAndTime);
	    System.out.println("Event Location: " + location);

	    if (contact != null) {
	        // Single contact
	        System.out.println("Contact Name: " + contact.getName());
	    } else if (contactBST != null) {
	        // Set of contacts
	        System.out.println("Contact Names:");
	        contactBST.printContacts();
	    }

	    System.out.println();
	}


}