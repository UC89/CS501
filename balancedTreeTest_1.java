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

	public Node find(int key)   // from listing 8.1
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

	public void insert (int id, double dd)   // from listing 8.1
	{
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		if (root==null)
		{
			root = newNode;
			System.out.println("Adding " + id + ", " + dd +" As Root");
			return;
		}
		else
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if (id < current.iData)
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
	}

	public boolean delete(int key)   // from listing 8.1
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
		successor.leftChild = current.leftChild;    //connects successor to current left child
		}
	return true;
	}

	private Node getSuccessor(Node delNode)   // from listing 8.1
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

		return successor;
	}

	public void traverse(int traverseType)   // from listing 8.1
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
			postOrder(root);
			break;
		}
		System.out.println();
	}

	public void balanceTree()
	{
		LinkedList treeList = new LinkedList();
		treeList = balanceHelper_1(treeList, root);
		System.out.println("In Balance Helper");
		printTreeList(treeList);
		Object[] treeArray = treeList.toArray(new Integer[treeList.size()]);
		int[] treeArrayInt = myListToArray(treeArray);
		Tree balancedTree = new Tree();
		int[] emptyArray = new int[0];
		balancedTree = arrayToTree(treeArrayInt,emptyArray,balancedTree);
		balancedTree.displayTree();
	}

	public int[] myListToArray(Object[] oldList)
	{
		int[] returnArray = new int[oldList.length];
		for (int x=0;x<oldList.length;x++)
		{
			//System.out.print("Converting obj: " + oldList[x]);
			returnArray[x] = (int) oldList[x];
		}
		return returnArray;
	}

	public void printTreeList(LinkedList list1)
	{
		Object[] treeArray = list1.toArray(new Integer[list1.size()]);
		for (int x=0; x<list1.size();x++)
		{
			System.out.print(treeArray[x] + ", ");
		}
	}

	// To initialize must make an empty array for arrayToConvert_2
	public Tree arrayToTree(int[] arrayToConvert_1, int[] arrayToConvert_2, Tree inTree)
	{
		System.out.println("in arrayToTree");

			System.out.print("Array1: ");
			printArray(arrayToConvert_1);
			if (arrayToConvert_2!=null)
			{
				System.out.print(" Array2: ");
				printArray(arrayToConvert_2);
			}
		if (arrayToConvert_1.length > 2 && arrayToConvert_2.length==0)
		{
			int divSpot = arrayToConvert_1.length/2;
			int[] arrayLeft = Arrays.copyOfRange(arrayToConvert_1,0,divSpot);
			int[] arrayRight = Arrays.copyOfRange(arrayToConvert_1,divSpot+1,arrayToConvert_1.length);
			inTree.insert(arrayToConvert_1[divSpot],0);
			System.out.print("Inserting : " + arrayToConvert_1[divSpot]);
			arrayToTree(arrayLeft,arrayRight, inTree);
		}
		else if (arrayToConvert_1.length==0 && arrayToConvert_2.length==1)
		{
			inTree.insert(arrayToConvert_2[0],0);
			return inTree;
		}
		else if (arrayToConvert_1.length==1 && arrayToConvert_2.length==1)
		{
			inTree.insert(arrayToConvert_1[0],0);
			inTree.insert(arrayToConvert_2[0],0);
			//inTree.displayTree();
			return inTree;
		}
		else if (arrayToConvert_1.length<=arrayToConvert_2.length)
		{
			int divSpot = arrayToConvert_2.length/2;
			System.out.print("Inserting 2<1: " + arrayToConvert_2[divSpot]);
			inTree.insert(arrayToConvert_2[divSpot],0);
			int[] arrayRight_1 = Arrays.copyOfRange(arrayToConvert_2,0,divSpot);
			int[] arrayRight_2 = Arrays.copyOfRange(arrayToConvert_2,divSpot+1,arrayToConvert_2.length);
			int[] arrayRight = joinArrays(arrayRight_1,arrayRight_2);
			//inTree.displayTree();
			arrayToTree(arrayToConvert_1,arrayRight,inTree);
		}
		else if (arrayToConvert_1.length>arrayToConvert_2.length)
		{
			int divSpot = arrayToConvert_1.length/2;
			System.out.print("Inserting 1<2: " + arrayToConvert_1[divSpot]);
			inTree.insert(arrayToConvert_1[divSpot],0);
			int[] arrayLeft_1 = Arrays.copyOfRange(arrayToConvert_1,0,divSpot);
			int[] arrayLeft_2 = Arrays.copyOfRange(arrayToConvert_1,divSpot+1,arrayToConvert_1.length);
			int[] arrayLeft = joinArrays(arrayLeft_1,arrayLeft_2);
			//inTree.displayTree();
			arrayToTree(arrayLeft,arrayToConvert_2,inTree);
		}
		// if right has more than left, take the middle element of right and add it to tree
		else if (arrayToConvert_1.length==arrayToConvert_2.length)
		{
			int divSpot = arrayToConvert_1.length/2;
			System.out.print("Inserting : " + arrayToConvert_1[divSpot]);
			inTree.insert(arrayToConvert_1[divSpot],0);
			int[] arrayRight_1 = Arrays.copyOfRange(arrayToConvert_2,0,divSpot-1);
			int[] arrayRight_2 = Arrays.copyOfRange(arrayToConvert_2,divSpot,arrayToConvert_2.length);
			int[] arrayRight = joinArrays(arrayRight_1,arrayRight_2);
			//inTree.displayTree();
			arrayToTree(arrayToConvert_1,arrayRight,inTree);
		}

		else
			return inTree;
		return inTree;
	}

	public int[] joinArrays(int[] array1,int[] array2)
	{
		int[] returnArray = new int[array1.length+array2.length];
		for (int x=0;x<array1.length;x++)
			returnArray[x]=array1[x];
		for (int y=0;y<array2.length;y++)
			returnArray[array1.length+y]=array2[y];
		return returnArray;
	}

	public void printArray(int[] array1)
	{
		for (int x=0;x<array1.length; x++)
		{
			System.out.print(array1[x] + ", ");
		}
	}
	public LinkedList balanceHelper_1(LinkedList returnList, Node localRoot)
	{
		if(localRoot!=null)
		{
			balanceHelper_1(returnList, localRoot.leftChild);
			returnList.add(localRoot.iData);
			System.out.println("Adding " + localRoot.iData + " To List");
			balanceHelper_1(returnList, localRoot.rightChild);
		}
		return returnList;
	}

	private void preOrder(Node localRoot)   // from listing 8.1
	{
		if (localRoot != null)
		{
			System.out.print("" + localRoot.iData + ' ');
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	private void postOrder(Node localRoot)   // from listing 8.1
	{
		if (localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print("" + localRoot.iData + ' ');
		}
	}

	private void inOrder(Node localRoot)   // from listing 8.1
	{
		if(localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print("" + localRoot.iData + ' ');
			inOrder(localRoot.rightChild);
		}
	}

	public void displayTree()   // from listing 8.1
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
			"------------------------------------------------------------");
		while (isRowEmpty==false)
		{
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j=0; j<nBlanks; j++)
			{
				System.out.print(' ');
				//System.out.println("in for loop # 1 printing blank J: " + j);
			}

			while (globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();  //changed
				if(temp != null)
				{
					//System.out.println("temp!=null temp: " + temp.iData);
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
					{
						//System.out.println("no left or right child");
						isRowEmpty = false;
					}
				}
				else
				{
					//System.out.println("printing blank space.. and pushing null");
					System.out.print("..");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j=0; j<nBlanks*2-2; j++)
				{
					//System.out.println("Printing in second j for loop");
					System.out.print(" ");
				}
			}
				System.out.println();
				nBlanks /= 2;
				while(localStack.isEmpty() == false)
				{
					globalStack.push(localStack.pop());
				}
				System.out.println("------------------------------------------------------------");

		}
	}
}

public class balancedTreeTest_1
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the jungle");

		Tree tree1 = new Tree();
		tree1.insert(7,0);
		tree1.insert(9,0);
		tree1.insert(5,0);
		tree1.displayTree();
		tree1.traverse(1);
		tree1.traverse(2);
		tree1.traverse(3);
		tree1.insert(1,0);
		tree1.insert(2,0);
		tree1.insert(3,0);
		tree1.insert(4,0);
		tree1.insert(6,0);
		tree1.displayTree();
		tree1.traverse(1);
		tree1.traverse(2);
		tree1.traverse(3);
		tree1.insert(44,0);
		tree1.insert(32,0);
		tree1.insert(43,0);
		tree1.insert(12,0);
		tree1.insert(89,0);
		tree1.balanceTree();
	}
}