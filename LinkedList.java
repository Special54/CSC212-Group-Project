
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = null;
	}

	public boolean empty() {
		return head == null;
	}

	private boolean isContactUnique(T data) {
		current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return false;
			}
			current = current.getNext();
		}
		return true;
	}

	public void add(T data) {
		if (data instanceof Event) {
			Event newEvent = (Event) data;
			Node<T> newNode = new Node<>(data);
			if (newEvent.getContact() != null) {
				String newEventTitle = newEvent.getTitle();
				String newEventDateTime = newEvent.getDateTime();

				if (empty()) {
					head = newNode;
				} else if (newEventTitle.compareTo(((Event) head.getData()).getTitle()) < 0) {
					if (newEvent.getContact().equals(((Event) head.getData()).getContact())) {
						String existingEventDate = ((Event) head.getData()).getDateTime();
						if (existingEventDate.equals(newEventDateTime)) {
							System.out.println("Event scheduling failed because its conflicting with another event.");
							return;
						}
					}
					newNode.setNext(head);
					head = newNode;
					System.out.println("\nEvent scheduled successfully!\n");
				} else {
					Node<T> current = head.getNext();
					Node<T> prev = head;

					while (current != null) {
						Event currentEvent = (Event) current.getData();
						String currentEventTitle = currentEvent.getTitle();
						String currentEventDateTime = currentEvent.getDateTime();

						if (newEvent.getContact().equals(currentEvent.getContact())
								&& newEventDateTime.equals(currentEventDateTime)) {
							System.out.println("Event scheduling failed: conflicting with another event.");
							return;
						}
						if (newEventTitle.compareTo(currentEventTitle) < 0) {
							if (prev == null) {
								newNode.setNext(head);
								head = newNode;
							} else {
								prev.setNext(newNode);
								newNode.setNext(current);
							}
							return;
						}
						prev = current;
						current = current.getNext();
					}
					prev.setNext(newNode);
				}
			} else
				System.out.println("Contact not found in the contacts' list!");
		}
		if (data instanceof Contact) {
			if (isContactUnique(data)) {
				Node<T> newNode = new Node<T>(data);
				Node<T> current = head;

				if (empty() || ((Contact) data).compareTo((Contact) current.getData()) < 0) {
					newNode.setNext(head);
					head = newNode;
				} else {
					while (current.getNext() != null
							&& ((Contact) data).compareTo((Contact) current.getNext().getData()) >= 0) {
						current = current.getNext();
					}
					newNode.setNext(current.getNext());
					current.setNext(newNode);
				}
			}
		}
	}

	public Contact searchName(String n) {
		current = head;
		while (current != null) {
			if (((Contact) current.getData()).getName().equalsIgnoreCase(n)) {
				return (Contact) current.getData();
			}
			current = current.getNext();
		}
		return null;
	}

	public Event searchTitle(String t) {
		current = head;
		while (current != null) {
			if (((Event) current.getData()).getTitle().equalsIgnoreCase(t))
				return (Event) current.getData();
			current = current.getNext();
		}
		return null;
	}

	public void searchPhone(String p) {
		current = head;
		if (current != null) {
			System.out.println("Contact found!\n");
			while (current != null) {
				if (((Contact) current.getData()).getPhoneNum().equalsIgnoreCase(p)) {
					((Contact) current.getData()).display();
				}
				current = current.getNext();
			}
		} else
			System.out.println("Contact not found!");

	}

	public void searchEmail(String e) {
		current = head;
		if (current != null) {
			System.out.println("Contact(s) found!\n");
			while (current != null) {
				if (((Contact) current.getData()).getEmail().equalsIgnoreCase(e))
					((Contact) current.getData()).display();
				current = current.getNext();
			}
		} else
			System.out.println("No contact found!\n");
	}

	public void searchAddress(String a) {
		current = head;
		if (current != null) {
			System.out.println("Contact(s) found!\n");
			while (current != null) {
				if (((Contact) current.getData()).getAddress().equalsIgnoreCase(a))
					((Contact) current.getData()).display();
				current = current.getNext();
			}
		} else
			System.out.println("No contact found!\n");

	}

	public void searchBirthday(String b) {
		current = head;
		if (current != null) {
			System.out.println("Contact(s) found!\n");
			while (current != null) {
				if (((Contact) current.getData()).getBirthDay().equalsIgnoreCase(b))
					((Contact) current.getData()).display();
				current = current.getNext();
			}
		} else
			System.out.println("No contact found!\n");
	}

	public boolean deleteContact(String n) {
		if (empty())
			return false;

		current = head;
		while (current != null) {
			if (((Contact) current.getData()).getName().equalsIgnoreCase(n)) {
				if (current == head) {
					head = current.getNext();
				} else {
					Node<T> prev = head;
					while (prev.getNext() != current)
						prev = prev.getNext();
					prev.setNext(current.getNext());
				}
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

}
