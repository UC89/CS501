// Circular List Project 5.3
// Taylor Somma   CS501

import java.io.*;

class circleNode
{
	public circleNode next;
	private Double value;

	public circleNode(Double inValue)
	{
		value = inValue;
	}
	public void setNext(circleNode nextNode)
	{
		next = nextNode;
	}

	public circleNode getNext()
	{
		return next;
	}

	public Double getValue()
	{
		if (value != null)
		{
			return value;
		}
		else
		{
			return 0.0;
		}
	}
}

class CircularList
{
	private int length;
	public circleNode currentNode;

	public CircularList()
	{
		length = 0;
		currentNode = null;
	}

	public void step()
	{
		if (length ==0)
		{
			System.out.println("Cannot step, this list is empty");
		}
		else if (length ==1)
		{
			System.out.println("List only contains one node");
		}
		else
		{
			currentNode = currentNode.getNext();
		}
	}

	public int getLength()
	{
		return length;
	}

	public void add(Double newValue)
	{
		if(length == 1)
		{
			circleNode newNode = new circleNode(newValue);
			newNode.setNext(currentNode);
			currentNode.setNext(newNode);
			currentNode = newNode;
			length++;
		}
		else if(length > 1)
		{
			circleNode newNode = new circleNode(newValue);
			newNode.setNext(currentNode.getNext());
			currentNode.setNext(newNode);
			currentNode = newNode;
			length++;
		}

		else if (length == 0)
		{
			circleNode newNode = new circleNode(newValue);
			currentNode = newNode;
			length++;
		}
	}

	public void delete()
	{
		if (length ==0)
		{
			System.out.println("Nothing to delete, list is empty");
		}
		else if (length ==1)
		{
			length = 0;
			currentNode.next = null;
		}
		else
		{
			circleNode tempNode = currentNode.getNext();
			for (int x=0; x<length-1; x++)
			{
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(tempNode);
			length--;
		}
	}

	public boolean searchList(Double searchValue)
	{
		if (length == 0)
			return false;
		else if (length ==1)
		{
			if (currentNode.getValue()==searchValue)
				return true;
			else
				return false;
		}
		else
		{
			System.out.println("Searching through list");
			for (int x=0; x<length; x++)
			{
				if (searchValue==currentNode.getValue())
				{
					System.out.println("Found Comparing " + searchValue + " with " + currentNode.getValue());
					return true;
				}
				else
				{
					System.out.println("Comparing " + searchValue + " with " + currentNode.getValue());
					currentNode = currentNode.getNext();
				}

			}
			return false;
		}
	}
	public void printList()
	{
		String returnString = new String();
		if (length == 0)
			System.out.println("Empty List");
		else if (length ==1)
		{
			System.out.println(currentNode.getValue());
		}
		else
		{
			for (int x=0; x<length; x++)
			{
				returnString += (currentNode.getValue() + ", ");
				currentNode = currentNode.getNext();
			}
			System.out.println(returnString);
			//currentNode = currentNode.getNext();
		}
	}
}

public class circularListTest
{
	public static void main(String[] args)
	{
		CircularList list1 = new CircularList();
		System.out.println("Welcome to the circular list");
		System.out.println("Please enter a value for the first node");

		list1.printList();
		list1.add(10.3);
		System.out.println("\nCurrent Node: " + list1.currentNode.getValue());
		list1.printList();
		list1.add(19.4);
		System.out.println("\nCurrent Node: " + list1.currentNode.getValue());
		list1.printList();
		list1.add(585.03);
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.printList();
		list1.add(1224.42);
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.printList();
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.step();
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.step();
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.add(42327.242);
		list1.printList();
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.step();
		System.out.println("Current Node: " + list1.currentNode.getValue());
		list1.printList();
		list1.printList();

		System.out.println("\n\nTo loop through list with 15 steps");
		for (int x=0; x<15; x++)
		{
			list1.step();
			System.out.println("Current Node: " + list1.currentNode.getValue());
		}

		System.out.println("\nSearch list");
		System.out.println("10.3 is in list: " + list1.searchList(10.3));
		System.out.println("1841.34 is in list: " + list1.searchList(1841.34));
		System.out.println("1224.42 is in list: " + list1.searchList(1224.42));
		System.out.println(122.4==122.4);
		System.out.println("\n Delete List 1 by 1");
		int list1LengthTemp = list1.getLength();
		for (int x=0; x<list1LengthTemp+1; x++)
		{
			System.out.println("\n Deleting a Node");
			list1.delete();
			list1.printList();
		}

	}
}
