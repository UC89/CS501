// Problem 8.2  Taylor Somma
// CS501

import java.io.*;
import java.util.*;

class Node
{
	public int iData;
	public double dData;
	public Node leftChild;
	public Node rightChild;

	public void displayNode()
	{
		System.out.print("{");
		System.out.print(iData);
		System.out.print(", ");
		System.out.print("dData");
		System.out.print("} ");

	}
}

class Tree
{
	private Node root;

	public Tree()
	{
		root=null;
	}

	public Node find(int key)
	{
		Node current=root;
		while(current.iData != key)
		{
			if (key < current.iData)
				current= current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert (int id, double dd)
	{
		Node newNode = new Node();
		newNode.idata = id;
		newNode.dData = dd;
		if (root==null)
			root = newNode;
		else
		{
			Node current = root;
			Node parent;
			while(true)
				parent = current;
			if (id < current.leftChild)
			{
				current = current.leftChild;
				if (current == null)
				{
					parent.leftChild = newNode;
					return;
				}
			}
			else
			{
				current = current.rightChild;
				if (current == null)
				{
					parent.rightChild = newNode;
					return;
				}
			}
		}
	}

	public boolean delete(int key)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while(current.iData != key)
		{
			parent = current;
			if(key < current.iData)
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
			{
				return false;
			}
		}

		if(current.leftChild==null && current.rightChild==null) //No Children
		{
			if (current==root)
				root = null;
			else if(isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}

		else if(current.leftChild==null)  //No left child, only right
		{
			if (current==root)
				root=current.rightChild;
			else if(isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		}

		else if(current.rightChild == null)  // No right child, only left
		{
			if (current==root)
			{
				root=current.leftChild;
			}

			if (isLeftChild)
			{
				parent.leftChild = current.leftChild;
			}
			else
			{
				parent.rightChild = current.leftChild;
			}
		}

		else      //Two Children
		{
			Node successor = getSuccessor(current);

			if (current==root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
		}
		successor.leftChild = current.leftChild;    //connects successor to current left child
	return true;
	}

	private Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while(current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if(successor != delNode.rightChild)
		{
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}

		return sucessor;
	}

	public void traverse(int traversType)
	{
		switch(traverseType)
		{
			case 1: System.out.print("\nPreorder traversal: ");
			preOrder(root);
			break;
			case 2: System.out.print("\nInorder traversal: ");
			inOrder(root);
			break;
			case 3: System.out.print("\nPostorder traversal: ");
			postOrder();
			break;
		}
		System.out.println();
	}

	private void preOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			System.out.print(localRoot.iData + ' ');
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	private void postOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + ' ');
		}
	}
	private void inOrder(Node localRoot)
	{
		inOrder(localRoot.leftChild);
		System.out.print(localRoot.iData + ' ');
		inOrder(localRoot.rightChild);
	}

	public void displayTree()
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		in nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
			"----------------------------------");
		while (isRowEmpty==False)
		{
			Stack localStack = newStack();
			isRowEmpty = true;

			for (int j=0; j<Blanks; j++)
			{
				System.out.print(' ');
			}

			while (globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
					{
						isRowEmpty = false;
					}
				}
				else
				{
					System.out.print("..");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j=0; j<nBlanks*2-2; j++)
				{
					System.out.print(" ");
				}
				System.out.println();
				nBlanks /= 2;
				while(localStack.isEmpty() == false)
				{
					globalStack.push(localStack.pop() );
				}
			}
		}
	}
}

public class balancedTreeTest
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the jungle");
	}
}