import java.util.InputMismatchException;
import java.util.Scanner;

public class Phonebook {
	private static LinkedList<Contact> contacts = new LinkedList<Contact>();
	private static LinkedList<Event> events = new LinkedList<Event>();
	public static Scanner scanner = new Scanner(System.in);

	public static void addContact() {
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
		contacts.add(contact);
		System.out.println("\nContact added!\n");
	}

	public static void searchContacts() {
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
			if (contacts.searchName(Name) != null) {
				System.out.println("Contact found!\n");
				contacts.searchName(Name).display();
			} else
				System.out.println("Contact not found!");
			break;
		case 2:
			System.out.print("\nEnter the contact's phone number: \n");
			String number = scanner.nextLine();
			contacts.searchPhone(number);
			break;
		case 3:
			System.out.print("\nEnter the contact's email address: \n");
			String email = scanner.nextLine();
			contacts.searchEmail(email);
			break;
		case 4:
			System.out.print("Enter the contact's address: ");
			String address = scanner.nextLine();
			contacts.searchAddress(address);
			break;
		case 5:
			System.out.print("Enter the contact's Birthday: ");
			String day = scanner.nextLine();
			contacts.searchBirthday(day);
			break;
		default:
			System.out.println("Invalid choice. Please choose a valid option. (1-5)");
		}
	}

	public static boolean deleteEventsByContact(Contact contact) {
		Node<Event> current = events.getHead();
		Node<Event> prev = null;

		while (current != null) {
			if (current.getData().getContact().equals(contact)) {
				if (current == events.getHead()) {
					events.setHead(current.getNext());
				} else {
					prev.setNext(current.getNext());
				}
			} else {
				prev = current;
			}
			current = current.getNext();
		}
		return true;
	}

	public static void deleteContact() {
		System.out.println("\nEnter the contact's name: ");
		String contactName = scanner.nextLine();
		Contact contactToDelete = contacts.searchName(contactName);
		if (contactToDelete != null && events.getHead() != null)
			deleteEventsByContact(contactToDelete);
		if (contacts.deleteContact(contactName)) {
			System.out.println("Contact and related events (if any) are deleted successfully!\n");
		} else {
			System.out.println("Delete unsuccessful.\n");
		}
	}

	public static void addEvent() {
		System.out.println("\nEnter event title: ");
		String title = scanner.nextLine();
		System.out.println("Enter contact name: ");
		String contactName = scanner.nextLine();
		Contact contact = contacts.searchName(contactName);
		System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
		String dateAndTime = scanner.nextLine();
		System.out.println("Enter event location: ");
		String location = scanner.nextLine();
		Event event = new Event(title, contact, dateAndTime, location);

		if (events.add(event)) {
			contact.addContactEvent(event);
		}
	}

	public static void printEvent() {
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
			Contact contact = contacts.searchName(contactName);
			if (contact != null)
				contact.getEvents();
			else
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

	public static void printContactsByFirstName() {
		System.out.println("\nEnter the first name: ");
		String firstName = scanner.nextLine();
		Node<Contact> current = contacts.getHead();
		if (current != null)
			while (current != null) {
				String fullName = current.getData().getName();
				String[] nameParts = fullName.split(" ");

				if (nameParts.length > 0 && nameParts[0].equalsIgnoreCase(firstName))
					current.getData().display();

				current = current.getNext();
			}
		else
			System.out.println("\nNo contact(s) found.\n");

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

	public static void printAllContactsSharingEvent() {
		System.out.println("\nEnter the event title: ");
		String eventTitle = scanner.nextLine();
		Event event = events.searchTitle(eventTitle);
		if (event != null) {
			Node<Event> current = events.getHead();
			while (current != null) {
				if (event.getTitle().equalsIgnoreCase(current.getData().getTitle()))
					current.getData().getContact().display();
				current = current.getNext();
			}
		} else
			System.out.println("Event not found!\n");
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Linked Tree Phonebook!");

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
