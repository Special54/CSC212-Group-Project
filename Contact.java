
public class Contact implements Comparable<Contact> {
	private String name, email, address, birthDay, notes, phoneNum;
	private LinkedList<Event> contactEvents;
	private Node<Event> head;

	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNum = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthDay = birthday;
		this.notes = notes;
		contactEvents = new LinkedList<>();
		head = null;
	}

	public void addContactEvent(Event event) {
		Node<Event> newNode = new Node<>(event);
		newNode.setNext(head);
		head = newNode;
	}

	public void getEvents() {
		Node<Event> current = head;
		while (current != null ) {
			current.getData().display();
			current = current.getNext();
		}
	}

	public void setHead(Node<Event> head) {
		this.head = head;
	}

	public int compareTo(Contact otherContact) {
		return this.name.compareTo(otherContact.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Phone Number: " + phoneNum);
		System.out.println("Email Address:  " + email);
		System.out.println("Address: " + address);
		System.out.println("Birthday " + birthDay);
		System.out.println("Notes: " + notes + "\n");
	}

}
