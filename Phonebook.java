import java.util.Scanner;

public class Phonebook {
	private LinkedList<Contact> contacts;
	private LinkedList<Event> events;

	public Phonebook() {
		contacts = new LinkedList<Contact>();
		events = new LinkedList<Event>();
	}

	public void addEvent(Event event) {
		String contactName = event.getContactName();

		// Check if the contact exists in the contacts list
		Contact contact = contacts.searchContact(contactName);
		if (contact == null) {
			System.out.println("Contact does not exist in the phonebook. Event not scheduled.");
			return;
		}

		if (hasEventConflict(event)) {
			System.out.println("Event scheduling failed, conflict at: " + event.getDateTime());
			return;
		}
		// If the contact exists and there are no conflicts, add the event to the events
		// list
		events.add(event);
		System.out.println("Event scheduled successfully!");
	}

	// Helper method to check for conflicts with existing events
	private boolean hasEventConflict(Event newEvent) {
		String newEventDate = newEvent.getDateTime();
		Node<Event> current = events.getHead();
		while (current != null) {
			String existingEventDate = current.getData().getDateTime();

			if (newEventDate.equals(existingEventDate)) {
				return true;
			}
			current = current.getNext();
		}

		return false;
	}

	public LinkedList<Event> searchContactEvents(String name) {
		LinkedList<Event> contactEvents = new LinkedList<>();
		Node<Event> current = events.getHead();

		while (current != null) {
			Event event = current.getData();
			if (event.getContactName().equalsIgnoreCase(name)) {
				contactEvents.add(event);
			}
			current = current.getNext();
		}

		return contactEvents;
	}

	public void printAllContactsSharingEvent(Event event) {
		Node<Event> current = events.getHead();
		while (current != null) {
			if (event.getTitle().equals(current.getData().getTitle()))
				current.getData().getContact().display();
			current = current.getNext();
		}

	}

	public LinkedList<Contact> findContactsByFirstName(String firstName) {
		LinkedList<Contact> matchingContacts = new LinkedList<>();
		Node<Contact> current = contacts.getHead();

		while (current != null) {
			String fullName = current.getData().getName();
			String[] nameParts = fullName.split(" ");

			if (nameParts.length > 0 && nameParts[0].equalsIgnoreCase(firstName)) {
				matchingContacts.add(current.getData());
			}

			current = current.getNext();
		}

		return matchingContacts;
	}

	public void printAllEventsAlphabetically() {
		Node<Event> current = events.getHead();

		while (current != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Phonebook phonebook = new Phonebook();

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
			System.out.println("8. Exit\n");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("\nEnter the contact's name: ");
				String name = scanner.nextLine();
				System.out.println("Enter the contact's phone number: ");
				String pNumber = scanner.nextLine();
				System.out.println("Enter the contact's email address: ");
				String email = scanner.nextLine();
				System.out.println("Enter the contact's address: ");
				String address = scanner.nextLine();
				System.out.println("Enter the contact's birthday: ");
				String birthday = scanner.nextLine();
				System.out.println("Enter any notes for the contact: ");
				String notes = scanner.nextLine();
				Contact contact = new Contact(name, pNumber, email, address, birthday, notes);
				phonebook.contacts.addContact(contact);
				break;
			case 2:
				System.out.println("\nEnter search criteria: ");
				System.out.println("1. Contact name");
				System.out.println("2. Phone Number");
				System.out.println("3. Email Address");
				System.out.println("4. Address");
				System.out.println("5. Birthday\n");
				System.out.print("Enter your choice: ");
				int criteria = scanner.nextInt();
				scanner.nextLine();
				switch (criteria) {
				case 1:
					System.out.print("\nEnter the contact's name: ");
					String Name = scanner.nextLine();
					Contact foundContact = phonebook.contacts.searchContact(Name);
					if (foundContact != null) {
						System.out.println("Contact found!");
						foundContact.display();
					} else
						System.out.println("Contact with name: " + Name + " is not found.");

					break;
				case 2:
					System.out.print("\nEnter the contact's phone number: ");
					String num = scanner.nextLine();
					Contact foundContactNum = phonebook.contacts.searchPhone(num);
					if (foundContactNum != null) {
						System.out.println("Contact found!");
						foundContactNum.display();
					} else
						System.out.println("Contact with phone number: " + num + " is not found.");
					break;
				case 3:
					System.out.print("Enter the contact's email address: ");
					String email2 = scanner.nextLine();
					LinkedList<Contact> emailList = phonebook.contacts.searchEmail(email2);
					Node<Contact> current = emailList.getHead();
					while (current != null) {
						current.getData().display();
						current = current.getNext();
					}
					break;
				case 4:
					System.out.print("Enter the contact's address: ");
					String address2 = scanner.nextLine();
					LinkedList<Contact> addressList = phonebook.contacts.searchEmail(address2);
					Node<Contact> current2 = addressList.getHead();
					while (current2 != null) {
						current2.getData().display();
						current2 = current2.getNext();
					}
					break;
				case 5:
					System.out.print("Enter the contact's Birthday: ");
					String day = scanner.nextLine();
					LinkedList<Contact> dayList = phonebook.contacts.searchEmail(day);
					Node<Contact> current3 = dayList.getHead();
					while (current3 != null) {
						current3.getData().display();
						current3 = current3.getNext();
					}
					break;
				}
				break;
			case 3:
				System.out.println("\nEnter the contact's name: ");
				String deleteName = scanner.nextLine();
				if (phonebook.contacts.deleteContact(deleteName))
					System.out.println("Contact deleted successfully!\n");
				else {
					System.out.println("Delete unsuccessful\n");
				}
				break;
			case 4:
				System.out.println("\nEnter event title: ");
				String title = scanner.nextLine();
				System.out.println("Enter contact name: ");
				String contactName = scanner.nextLine();
				Contact contact2 = phonebook.contacts.searchContact(contactName);
				System.out.println("Enter event date and time (MM/DD/YYYY HH:MM): ");
				String dateAndTime = scanner.nextLine();
				System.out.println("Enter event location: ");
				String location = scanner.nextLine();
				Event event = new Event(title, contact2, dateAndTime, location);
				phonebook.addEvent(event);
				break;
			case 5:
				System.out.println("\nEnter search criteria: ");
				System.out.println("1.Contact name");
				System.out.println("2.Event title\n");
				System.out.print("Enter your choice: ");
				int criteria2 = scanner.nextInt();
				scanner.nextLine();
				switch (criteria2) {
				case 1:
					System.out.println("\nEnter the contact's name: ");
					String name1 = scanner.nextLine();
					System.out.println("Searching for events for contact: " + name1);
					LinkedList<Event> foundEvents = phonebook.searchContactEvents(name1);

					if (!foundEvents.empty()) {
						System.out.println("Events for " + name1 + ":");
						Node<Event> current = foundEvents.getHead();
						while (current != null) {
							current.getData().display();
							current = current.getNext();
						}
					} else {
						System.out.println("No events found for " + name1);
					}
					break;
				case 2:
					System.out.println("\nEnter the event title: ");
					String name2 = scanner.nextLine();
					Event foundEvent = phonebook.events.searchTitle(name2);
					if (foundEvent != null) {
						foundEvent.display();
					} else {
						System.out.println("Event not found.");
					}
					break;
				default:
					System.out.println("Invalid criteria");
				}
				break;
			case 6:
				System.out.println("\nEnter the first name: ");
				String fName = scanner.nextLine();
				LinkedList<Contact> nameList = phonebook.findContactsByFirstName(fName);
				Node<Contact> current = nameList.getHead();
				int count = 0;
				while (current != null) {
					if (++count == 1)
						System.out.println("Contacts found!");
					current.getData().display();
					current = current.getNext();
				}
				break;
			case 7:
				if (phonebook.events.getHead() != null) {
					System.out.println("Events found!\n");
					phonebook.printAllEventsAlphabetically();
				}
				break;
			case 8:
				// Exit the program
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please choose a valid option.");
			}

		}
	}
}
