public class ContactBST {
	private TreeNode root;

	private class TreeNode {
		private Contact contact;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(Contact contact) {
			this.contact = contact;
			this.left = null;
			this.right = null;
		}

		public Contact getContact() {
			return contact;
		}

		public TreeNode getLeft() {
			return left;
		}

		public TreeNode getRight() {
			return right;
		}

	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isContactInTree(TreeNode root, Contact contact) { // Checks if a contact with the same name or phone
																		// number already exists in the tree.
		return isContactInTreeRecursive(root, contact.getName(), contact.getPhoneNum());
	}

	private boolean isContactInTreeRecursive(TreeNode root, String name, String phoneNumber) {
		if (root == null) {
			return false;
		}

		if (isContactInTreeRecursive(root.left, name, phoneNumber)) {
			return true;
		}

		if (root.contact.getName().equalsIgnoreCase(name) || root.contact.getPhoneNum().equals(phoneNumber)) { // Check
																												// for
																												// same
																												// name
																												// or
																												// number.
			return true;
		}

		if (isContactInTreeRecursive(root.right, name, phoneNumber)) {
			return true;
		}

		return false;
	}

	public boolean addContact(Contact contact) {
		if (isContactInTree(root, contact)) {
			System.out.println("Contact with the same name or phone number already exists. Not added.\n");
			return false;
		}
		root = addContactRecursive(root, contact);
		return true;
	}

	private TreeNode addContactRecursive(TreeNode root, Contact contact) {
		if (root == null) {
			return new TreeNode(contact);
		}

		if (contact.compareTo(root.contact) < 0) {
			root.left = addContactRecursive(root.left, contact);
		} else if (contact.compareTo(root.contact) > 0) {
			root.right = addContactRecursive(root.right, contact);
		}

		return root;
	}

	public void addEventToAllContacts(Event newEvent) { // Add the event to the contact's event list
		inOrderTraversal(root, newEvent);
	}

	private void inOrderTraversal(TreeNode current, Event newEvent) {
		if (current != null) {
			inOrderTraversal(current.left, newEvent);

			Contact contact = current.contact;
			contact.addContactEvent(newEvent);

			inOrderTraversal(current.right, newEvent);
		}
	}

	public void printContacts() { // Method that prints all the names of contacts in a BST.
		inOrderTraversal(root);
	}

	private void inOrderTraversal(TreeNode current) {
		if (current != null) {
			inOrderTraversal(current.left);
			System.out.println(current.contact.getName());
			inOrderTraversal(current.right);
		}
	}

	public Contact searchContact(String name) { // Searches for a contact using their email and returns the contact.
		return searchContactRecursive(root, name);
	}

	private Contact searchContactRecursive(TreeNode root, String name) {
		if (root == null || root.contact.getName().equals(name)) {
			return (root != null) ? root.contact : null;
		}

		if (name.compareTo(root.contact.getName()) < 0) {
			return searchContactRecursive(root.left, name);
		} else {
			return searchContactRecursive(root.right, name);
		}
	}

	public Contact searchPhone(String p) { // Searches for a contact using their email and returns the contact.
		return searchPhoneRecursive(root, p);
	}

	private Contact searchPhoneRecursive(TreeNode current, String phone) {
		if (current != null) {
			searchPhoneRecursive(current.getLeft(), phone);

			Contact currentContact = (Contact) current.getContact();
			if (currentContact.getPhoneNum().equalsIgnoreCase(phone))
				return currentContact;

			searchPhoneRecursive(current.getRight(), phone);
		}
		return null;
	}

	public Contact searchEmail(String p) { // Searches for a contact using their email and returns the contact.

		return searchEmailRecursive(root, p);
	}

	private Contact searchEmailRecursive(TreeNode current, String email) {
		if (current != null) {
			searchEmailRecursive(current.getLeft(), email);
			Contact currentContact = (Contact) current.getContact();
			if (currentContact.getEmail().equalsIgnoreCase(email)) {
				return currentContact;
			}
			searchEmailRecursive(current.getRight(), email);
		}
		return null;
	}

	public Contact searchAddress(String p) { // Searches for a contact using their Address and returns the contact.
		return searchAddressRecursive(root, p);
	}

	private Contact searchAddressRecursive(TreeNode current, String address) {
		if (current != null) {
			searchAddressRecursive(current.getLeft(), address);
			Contact currentContact = (Contact) current.getContact();
			if (currentContact.getAddress().equalsIgnoreCase(address)) {
				return currentContact;
			}
			searchAddressRecursive(current.getRight(), address);
		}
		return null;
	}

	public Contact searchBirthday(String p) { // Searches for a contact using their birthday and prints the contact's
												// information.
		return searchBirthdayRecursive(root, p);
	}

	private Contact searchBirthdayRecursive(TreeNode current, String birthday) {
		if (current != null) {
			searchBirthdayRecursive(current.getLeft(), birthday);
			Contact currentContact = (Contact) current.getContact();
			if (currentContact.getBirthDay().equalsIgnoreCase(birthday)) {
				return currentContact;
			}
			searchBirthdayRecursive(current.getRight(), birthday);
		}
		return null;
	}

	public void printContactsByFirstName(String firstName) { // Prints contacts by the first name.
		printContactsByFirstNameRecursive(root, firstName);
	}

	private void printContactsByFirstNameRecursive(TreeNode root, String firstName) {
		if (root != null) {
			printContactsByFirstNameRecursive(root.left, firstName);

			String[] nameParts = root.contact.getName().split(" ");
			if (nameParts.length > 0 && nameParts[0].equalsIgnoreCase(firstName)) {
				root.contact.display();
			}

			printContactsByFirstNameRecursive(root.right, firstName);
		}
	}

	public void deleteContact(String name) {
		root = deleteContactRecursive(root, name);
	}

	private TreeNode deleteContactRecursive(TreeNode root, String name) {
		if (root == null) {
			return null;
		}

		if (name.compareTo(root.contact.getName()) < 0) {
			root.left = deleteContactRecursive(root.left, name);
		} else if (name.compareTo(root.contact.getName()) > 0) {
			root.right = deleteContactRecursive(root.right, name);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			root.contact = findMin(root.right).contact;
			root.right = deleteContactRecursive(root.right, root.contact.getName());
		}

		return root;
	}

	private TreeNode findMin(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

}