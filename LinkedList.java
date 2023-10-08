
public class LinkedList<T> {
	private Node<T> head;

	public LinkedList() {
		head = null;
	}

	public boolean empty() {
		return head == null;
	}

	private boolean isContactUnique(T data) {
		Node<T> current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return false;
			}
			current = current.getNext();
		}
		return true;
	}

	public void addContact(T data) {
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

	public void add(T data) {
		if (data instanceof Event) {
			Node<T> newNode = new Node<>(data);

			if (head == null || ((Event) data).getTitle().compareTo(((Event) head.getData()).getTitle()) < 0) {
				newNode.setNext(head);
				head = newNode;
			} else {
				Node<T> current = head;
				while (current.getNext() != null
						&& ((Event) data).getTitle().compareTo(((Event) current.getNext().getData()).getTitle()) >= 0) {
					current = current.getNext();
				}
				newNode.setNext(current.getNext());
				current.setNext(newNode);
			}
		}
	}

	public T searchName(String n) {
		Node<T> current = head;
		while (current != null) {
			if (((Contact) current.getData()).getName().equals(n)) {
				return current.getData();
			}
			current = current.getNext();
		}
		return null;
	}

	public Event searchTitle(String t) {
		Node<T> current = head;
		while (current != null) {
			if (((Event) current.getData()).getTitle().equals(t))
				return (Event) current.getData();
			current = current.getNext();
		}
		return null;

	}

	public Contact searchPhone(String p) {
		Node<T> current = head;
		while (current != null) {
			if (((Contact) current.getData()).getPhoneNum().equals(p)) {
				return (Contact) current.getData();
			}
			current = current.getNext();
		}
		return null;
	}

	public LinkedList<T> searchEmail(String e) {
		Node<T> current = head;
		LinkedList<T> email = new LinkedList<T>();
		while (current != null) {
			if (((Contact) current.getData()).getEmail().equals(e))
				email.addContact(current.getData());
			current = current.getNext();
		}
		return email;
	}

	public LinkedList<T> searchAddress(String a) {
		Node<T> current = head;
		LinkedList<T> address = new LinkedList<T>();
		while (current != null) {
			if (((Contact) current.getData()).getAddress().equals(a))
				address.addContact(current.getData());
			current = current.getNext();
		}
		return address;
	}

	public LinkedList<T> searchBirthday(String b) {
		Node<T> current = head;
		LinkedList<T> birthDay = new LinkedList<T>();
		while (current != null) {
			if (((Contact) current.getData()).getBirthDay().equals(b))
				birthDay.addContact(current.getData());
			current = current.getNext();
		}
		return birthDay;
	}

	public boolean deleteContact(String n) {
		if (empty())
			return false;

		Node<T> current = head;
		while (current != null) {
			if (((Contact) current.getData()).getName().equals(n)) {
				if (head == current) {
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
