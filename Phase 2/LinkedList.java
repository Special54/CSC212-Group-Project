
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean add(T data) {
		Event newEvent = (Event) data;
		Node<T> newNode = new Node<>(data);

		if (newEvent.getContact() != null) {
			String newEventTitle = newEvent.getTitle();
			if (empty() || newEventTitle.compareTo(((Event) head.getData()).getTitle()) < 0) {
				newNode.setNext(head);
				head = newNode;
				return true;
			} else {
				Node<T> current = head;
				Node<T> prev = null;
				while (current != null && newEventTitle.compareTo(((Event) current.getData()).getTitle()) > 0) {
					prev = current;
					current = current.getNext();
				}

				newNode.setNext(current);
				if (prev != null) {
					prev.setNext(newNode);
				} else {
					head = newNode;
				}
				return true;
			}
		} else {
			System.out.println("Contact not found in the contacts' list!");
			return false;
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
					return;
				}
				current = current.getNext();
			}
		} else
			System.out.println("\nContact not found!\n");

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
