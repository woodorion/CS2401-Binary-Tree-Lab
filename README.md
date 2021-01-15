# CS2401-Binary-Tree-Lab

## City Binary Tree Node Class
contains the name of the city, two child pointers, and the distance to the node's parent
* the distance at the root should be 0
* the distance should always be positive (the constructor should verify this) 
* the direction is dictated by the path (i.e. left child with distance 5, then right child distance 2 would be a total distance of 3 from the root).

## Binary Tree Class
* insert, takes a name and a distance from the root (+ being one direction, - being the other), repeat names shoud not be inserted.
 - the public (non-static) method takes only these two parameter and calls the static method on the root
 - the private (static) method takes 3 parameters and either creates a new node at one of the null children (depending on the direction) or makes a recursive call on a new node, a modified distance, and the original new name
* find, takes in a string and determines if its in the tree
 - the public (non-static) method takes only the string, calls the static method on the root
 - the private (static) method takes the string and a node, check if the node is the target and calls recrusively on its children
* distanceFromRoot, non-static method that when given a city name returns a distance from the root to that node (if it exists) and returns a *positive* value (hint: you should use distanceDownTree called on the root and possibly modify the value)
* distanceDownTree, static method that takes a city name and a city node returns a value that may be positive or negative depending on direction, returns -999999 if the search node is not in the subtree of the given node. 
* distanceBetweenNodes, given two node names determine the distance between them
 - the public (non-static) method calls the static method on the root
 - the private (static) method determines if: (1) one of the search values is the current node, (2) both search values are in the same child, (3) one is in the left child the other is in the right, or (4) one or both of the nodes can't be found in this subtree. The first three will compute the distance and return it (as an absolute distance) the last will return -999999 since it is invalid. 
 
Here is a basic idea of the method signatures, you can modify the names to match what you have written by please identify in a comment which one they match to: 
``` 
class CityBinaryTree{
  static class City{
    String name;
    int distance;
    City left;
    City right;
    City(String inName, int inDist){}
  }
  
  private City root;
  CityBinaryTree(String rootName){}
  
  public void insert(String name, int distanceFromRoot) {}
  private static void insert(String name, int distanceToGo, City current) {}
  
  public boolean find(String name) {}
  private static boolean find(String name, City current) {}
  
  public int distanceFromRoot(String name) {}
  private static int distanceDownTree(String name, City current) {}
  
  public int distanceBetweenNodes(String a, String b) {}
  private static int distanceBetweenNodes(String a, String b, City current) {}
  
 }
 ```
 
 I have also included an example test case, which could be modified to fir your method names:
 ```
    CityBinaryTree tree2 =  new CityBinaryTree("R");
    tree2.insert("W4",-4);
    tree2.insert("W6",-6);
    tree2.insert("E6",6);
    tree2.insert("E4",4);
    tree2.insert("E8",8);
    tree2.print();

    assert(tree2.distanceFromRoot("R")==0);
    assert(tree2.distanceFromRoot("W4")==-4);
    assert(tree2.distanceFromRoot("W6")==-6);
    assert(tree2.distanceFromRoot("E4")==4);
    assert(tree2.distanceFromRoot("E6")==6);
    assert(tree2.distanceFromRoot("E8")==8);

    assert(tree2.distanceBetweenNodes("R", "R")==0);
    assert(tree2.distanceBetweenNodes("R", "W4")==4);
    assert(tree2.distanceBetweenNodes("R", "W6")==6);
    assert(tree2.distanceBetweenNodes("R", "E4")==4);
    assert(tree2.distanceBetweenNodes("R", "E6")==6);
    assert(tree2.distanceBetweenNodes("R", "E8")==8);
    assert(tree2.distanceBetweenNodes("E4", "E8")==4);
    assert(tree2.distanceBetweenNodes("E8", "E4")==4);
    assert(tree2.distanceBetweenNodes("W4", "E4")==8);
```

using the print statements below the tree would looks like the following:
``` 
R (0)
├ W4 (4)
│ ├ W6 (2)
│ └ null
└ E6 (6)
  ├ E4 (2)
  └ E8 (2)
```
in the city class
```
    private void print(String prefix) {
      System.out.println(prefix + name + " (" + distance + ")");
      prefix = prefix.replace('\u251C', '\u2502');
      prefix = prefix.replace('\u2514', ' ');
      if(left != null) left.print(prefix + "\u251C ");
      else if(right != null) System.out.println(prefix + "\u251C null");
      if(right != null) right.print(prefix + "\u2514 ");
      else if(left != null) System.out.println(prefix + "\u2514 null");
    }
```
in the tree class
```
public void print() { root.print(""); }
```
