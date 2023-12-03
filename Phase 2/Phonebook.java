import java.util.InputMismatchException;
import java.util.Scanner;

public class Phonebook {
	private static ContactBST contacts = new ContactBST();
	private static LinkedList<Event> events = new LinkedList<>();
	public static Scanner scanner = new Scanner(System.in);

	public static void addContact() { // Adds a contact
		System.out.println("\nEnter the contact's name: ");
		String name = scanner.nextLine();
		System.out.println("Enter the contact's phone number: ");
		String number = scanner.nextLine();
		System.out.println("Enter the contact's email address: ");
		String email = scanner.nextLine();
		System.out.println("Enter the contact's address: ");
		String address = scanner.nextLine();
		System.out.println("Enter the contact's birthday: ");
		String birthday = scanner.nextLine();
		System.out.println("Enter any notes for the contact: ");
		String notes = scanner.nextLine();

		Contact contact = new Contact(name, number, email, address, birthday, notes);
		if (contacts.addContact(contact))
			System.out.println("\nContact added!\n");
	}

	public static void searchContacts() { // Searches for contacts according to user-chosen criteria.
		System.out.println("\nEnter search criteria: ");
		System.out.println("1. Contact name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday\n");
		System.out.print("Enter your choice: ");
		int criteria = 0;
		try {
			criteria = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			scanner.nextLine();
		}
		switch (criteria) {
		case 1:
			System.out.print("\nEnter the contact's name: ");
			String Name = scanner.nextLine();
			Contact foundContact = contacts.searchContact(Name);
			if (foundContact != null) {
				System.out.println("Contact found!\n");
				foundContact.display();
			} else
				System.out.println("Contact not found!");
			break;
		case 2:
			System.out.print("\nEnter the contact's phone number: \n");
			String number = scanner.nextLine();

			if (contacts.searchPhone(number) != null)
				contacts.searchPhone(number).display();
			else
				System.out.println("\nContact not found.\n");
			break;
		case 3:
			System.out.print("\nEnter the contact's email address: \n");
			String email = scanner.nextLine();

			if (contacts.searchEmail(email) != null)
				contacts.searchEmail(email).display();
			else
				System.out.println("\nContact not found.\n");
			break;
		case 4:
			System.out.print("Enter the contact's address: ");
			String address = scanner.nextLine();

			if (contacts.searchAddress(address) != null)
				contacts.searchAddress(address).display();
			else
				System.out.println("\nContact not found.\n");
			break;
		case 5:
			System.out.print("Enter the contact's Birthday: ");
			String day = scanner.nextLine();
			contacts.searchBirthday(day);
			if (contacts.searchBirthday(day) != null)
				contacts.searchBirthday(day).display();
			else
				System.out.println("\nContact not found.\n");
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option. (1-5)");
		}
	}

	public static void deleteContact() {
		System.out.println("\nEnter the contact's name: ");
		String contactName = scanner.nextLine();
		Contact contactToDelete = contacts.searchContact(contactName);
		if (contactToDelete != null && events.getHead() != null)
			deleteEventsByContact(contactToDelete);
		if (contactToDelete != null)
			contacts.deleteContact(contactName);

		System.out.println("Contact and related events (if any) are deleted successfully!\n");
	}

	public static void addEvent() { // Adds the event
		System.out.println("\nEnter search criteria: ");
		System.out.println("1. Event");
		System.out.println("2. Appointment");
		System.out.print("\nEnter your choice: \n");
		int choice = 0;
		try {
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character
		} catch (InputMismatchException e) {
			scanner.nextLine(); // Consume the invalid input
		}

		switch (choice) {
		case 1:
			System.out.println("Enter event title: ");
			String title = scanner.nextLine();
			System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
			String dateAndTime = scanner.nextLine();
			System.out.println("Enter event location: ");
			String location = scanner.nextLine();
			if (isEventConflict(dateAndTime)) {
				System.out.println("Event scheduling failed: conflicting with another event.");
				return;
			}
			ContactBST eventContacts = new ContactBST();
			String contactName;
			int count = 0;
			while (true) {
				System.out.println("Enter contact name (or 'done' to finish adding contacts): ");
				contactName = scanner.nextLine();

				if (contactName.equalsIgnoreCase("done")) {
					break;
				}
				Contact contact = contacts.searchContact(contactName);
				if (contact != null) {
					eventContacts.addContact(contact);
					count++;
				} else {
					System.out.println("Contact not found in the contacts' list!");
				}
			}
			if (count > 0) {
				Event newEvent = new Event(true, title, dateAndTime, location, eventContacts);
				events.add(newEvent);
				eventContacts.addEventToAllContacts(newEvent);
				System.out.println("\nEvent scheduled successfully!\n");
			} else
				System.out.println("Event not Scheduled.\n");
			break;

		case 2:
			System.out.println("Enter event title: ");
			String title1 = scanner.nextLine();
			System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
			String dateAndTime1 = scanner.nextLine();
			System.out.println("Enter event location: ");
			String location1 = scanner.nextLine();
			if (isEventConflict(dateAndTime1)) {
				System.out.println("Event scheduling failed: conflicting with another event.");
				return;
			}
			System.out.println("Enter contact name: ");
			String name = scanner.nextLine();
			Contact appointmentContact = contacts.searchContact(name);
			Event event = new Event(false, title1, dateAndTime1, location1, appointmentContact);
			if (appointmentContact != null) {
				System.out.println("Appointment Scheduled!\n");
				events.add(event);
				appointmentContact.addContactEvent(event);
			} else {
				System.out.println("Contact not found in the contacts' list!");
			}
			break;

		default:
			System.out.println("Invalid choice. Please choose a valid option. (1-2)");
		}
	}

	private static boolean isEventConflict(String date) { // Checks if theres a time conflict.
		Node<Event> current = events.getHead();
		while (current != null) {
			if (date.equals(current.getData().getDateTime())) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public static void printEvent() { // Prints all events of a certain contact or prints all events with a certain
										// title.
		System.out.println("\nEnter search criteria: ");
		System.out.println("1.Contact name");
		System.out.println("2.Event title\n");
		System.out.print("Enter your choice: ");
		int criteria = 0;
		try {
			criteria = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			scanner.nextLine();
		}
		switch (criteria) {
		case 1:
			System.out.println("\nEnter the contact's name: ");
			String contactName = scanner.nextLine();
			Contact contact = contacts.searchContact(contactName);
			if (contact != null) {
				contact.getEvents();
			} else
				System.out.println("Contact not found!");
			break;
		case 2:
			System.out.println("\nEnter the event title: ");
			String eventTitle = scanner.nextLine();
			Event foundEvent = events.searchTitle(eventTitle);

			if (foundEvent != null) {
				foundEvent.display();
			} else {
				System.out.println("Event not found.\n");
			}
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option. (1-2)");
		}
	}

	public static void printContactsByFirstName() { // Prints all contacts with the same first name.
		System.out.println("\nEnter the first name: ");
		String firstName = scanner.nextLine();
		contacts.printContactsByFirstName(firstName);
	}

	public static void printAllEventsAlphabetically() {
		if (events.getHead() != null) {
			System.out.println("Events found!\n");
			Node<Event> current = events.getHead();

			while (current != null) {
				current.getData().display();
				current = current.getNext();
			}
		} else
			System.out.println("\nNo events found!\n");
	}

	public static void printAllContactsSharingEvent() { // Prints all contacts sharing a certain event.
		System.out.println("\nEnter the event title: ");
		String eventTitle = scanner.nextLine();
		Event event = events.searchTitle(eventTitle);
		if (event != null) {
			Node<Event> current = events.getHead();
			while (current != null) {
				if (event.getTitle().equalsIgnoreCase(current.getData().getTitle()))
					if (current.getData().getContact() != null)
						current.getData().getContact().display();
					else
						current.getData().getContactBST().printContacts();
				current = current.getNext();
			}
		} else
			System.out.println("Event not found!\n");
	}

	public static boolean deleteEventsByContact(Contact contact) {
		Node<Event> current = events.getHead();
		Node<Event> prev = null;

		while (current != null) {
			if (current.getData().isEvent()) {
				Event event = current.getData();
				if (event.getContactBST().searchContact(contact.getName()) != null) {
					event.getContactBST().deleteContact(contact.getName());
				}
				// Check if the event has no contacts associated, then delete the entire event
				if (event.getContactBST().isEmpty()) {
					if (prev == null) {
						events.setHead(current.getNext());
					} else {
						prev.setNext(current.getNext());
					}
				}
			} else { // Appointment
				Event event = current.getData();
				if (!event.isEvent() && event.getContact() != null && event.getContact().equals(contact)) {
					if (prev == null) {
						events.setHead(current.getNext());
					} else {
						prev.setNext(current.getNext());
					}
				} else {
					prev = current; // Update prev only if the current node is not deleted
				}
			}
			current = current.getNext();
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Binary Search Tree Phonebook!");

		while (true) {
			System.out.println("Please choose an option:");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Print all Contacts sharing Event");
			System.out.println("9. Exit\n");

			System.out.print("Enter your choice: ");
			int choice;
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid option. (1-9)");
				scanner.nextLine();
				continue;
			}
			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				searchContacts();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				addEvent();
				break;
			case 5:
				printEvent();
				break;
			case 6:
				printContactsByFirstName();
				break;
			case 7:
				printAllEventsAlphabetically();
				break;
			case 8:
				printAllContactsSharingEvent();
				break;
			case 9:
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please choose a valid option.");
			}
		}
	}
}
