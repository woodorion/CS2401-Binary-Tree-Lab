import java.lang.Math.*;
/*
Orion's Notes:
-Change how values are stored. Distance of a node should represent the distance and direction from it's parent, not of root.
--Seem to have fixed the issue
--forgot that search function is private, still seems to work based off of cityDistance behavior
--cityDistance correctly finds cities, but incorrectly reports the stored distance as the total distance from the root
---When reporting distance between two points, do necessesary addition/subtraction at each step.
- look at methods: printEntire, mapHeight if time allows
 */

class CityBinaryTree {
    int totalDistance;
    boolean found = false;
    class City {
        int distance; //distance from parent node
        //negative is west, positive is east
        String name;
        City left;
        City right;

        public City(int item, String name) {
            this.distance = item;
            this.name = name;
            this.left = null;
            this.right = null;
        }

        //Believe this was added by Dr. De Blasio
        //If I understand correctly, prints the name(distance). then adds characters to more easily read chart.
        //Also prints the null nodes, if node is not on the bottom
        //NOTE: left and right were switched before edit, negative = west. Was putting west on the right instead of the left.
        public void print() { print(""); }
        private void print(String prefix) {
          System.out.println(prefix + name + " (" + distance + ")");
          prefix = prefix.replace('\u251C', '\u2502');
          prefix = prefix.replace('\u2514', ' ');
          if(right != null) right.print(prefix + "\u251C ");
          else if(left != null) System.out.println(prefix + "\u251C null");
          if(left != null) left.print(prefix + "\u2514 ");
          else if(right != null) System.out.println(prefix + "\u2514 null");
        }
    }
    City root;

    //initial constructor
    public CityBinaryTree() {
        root = null;
    }


    City passerCity = new City(-0, "error");    // used to pass a city value outside of a recursive method
    City errorCity = new City(-0, "error");     //holds error message, should not be changed
    //prints all the City's in the particular level.
    //Can be called independently, but mainly used in next method to print all nodes in all levels
    static void printLevel(City current, int level) {

        //if the root is null, map is empty. No need to continue, so return after printing nothing.
        if (current == null) {
            return;
        }
        if (level == 1) {
            System.out.print(current.distance + " ");
        }
        //if the level is higher than one, call method again for left and right halves, and one lower level
        else if (level > 1) {
            printLevel(current.left, level - 1);
            printLevel(current.right, level - 1);
        }
    }

    //prints the map top to bottom, uses printLevel
    static void printEntire(City current) {
         /* this is really a static method, since it takes in the node being worked on
         * ideally it woudn't take in a parameter and would just use the root since it then calls something
         * on each level. 
         */ //Note on above; fixed methods to be static. Not sure how to fix second issue without redoing a lot of code. Will have to look into it later - Orion
        int height = mapHeight(current);    //used to limit for loop
        for (int i = 1; i <= height; i++) {
            printLevel(current, i);    //print at current level (int i value), starting at City current
            System.out.println();   // after done with that entire level, start new line to delineate a new level
        }
    }

    //finds the height of the tree.Mainly used in other methods
    static int mapHeight(City root) {
        /* this is really a static method, since it takes in the node being worked on 
         * it would be good to do this like you did insert where you don't include the node in one call
         * then the recursive calls need it
         * but those can be private
         */

        //if the root is empty, the map is empty. Therefore height would be 0
        if (root == null) {
            return 0;
        }
        //finds the height of the left and right halves of the map
        int heightLeft = mapHeight(root.left);
        int heightRight = mapHeight(root.right);

        //if the left half is the higher half, return the height of that
        //the highest height is the height of the entire map
        if (heightLeft > heightRight) {
            return (heightLeft + 1);
        }
        //else return the right half height
        return (heightRight + 1);
    }

    //initial call for insert function. Essentially just points to recursive function.
    //cannot call recursive function initially, since a City/Node is needed as well
    void insertCity(int distance, String name){
        root = insertCity(root,distance, name);
    }

    //recursive call to insert function. Does most of the work
    //
    City insertCity(City current, int distance, String name){
       //if the current node is null, you can insert the new node here
        if(current == null){
            current = new City(distance, name);
            return current;
        }
        //if not empty
        //if the distance is negative (i.e. west), go towards left of the current node
        if(distance < current.distance){
            current.left = insertCity(current.left,distance-current.distance, name);
        }
        //if the distance is positive (i.e. east(, go right of the current node
        else if(distance > current.distance){
            current.right = insertCity(current.right,distance-current.distance,name);
        }
        else if(distance == current.distance){
            System.out.println("\nThe distance you have entered is within the " + current.name + " city limits. Please ensure you put in the correct distance.");
            System.out.println("Remember that negative values are West of " + root.name + " and positive values are East.");
        }
        //allows the method to return the node unedited
        return current;
    }

    //can rewrite values on a preexisting node. Used to pass values to placeHolder nodes without tying them together
    void rewrite(City target, City info){
        target.distance = info.distance;
        target.name = info.name;
        target.left = info.left;
        target.right = info.right;
    }

    //initial call to search function. Resets passerCity to standard error messages, and prints either info of city, or that city can't be found
    void searchCityPrint(String name){
        rewrite(passerCity, errorCity); //must restore passerCity so that error messages work properly
        //error message if a city of that name was not found
        if(passerCity.name.equals("error")) {
            System.out.println(name + " cannot be found in this map");
        }
        //messages if city was successfully found
        else{
            System.out.println(name + " is " + Math.abs(passerCity.distance) +  ((passerCity.distance > 0) ?" miles East from " : " miles West from ") + root.name);
            System.out.println(name + " can be found on level " + (mapHeight(root) - mapHeight(passerCity)) + " of the map");
        }
        rewrite(passerCity, errorCity);   //restore passerCity again for safety

    }

    //searches through tree to find city by name. Depth first search (i think). Goes left to right
    //recursive, and private so it cannot be called accidentally in main method
    //finds city, then passes the node location to a node outside the method. Otherwise would always returns root
    private City searchCity(City current, String name){
        //rewrites dummy node to hold error messages if this is the first iteration
        if(current == root)
            rewrite(passerCity, errorCity);

        //if the name in the current node matches the desired name, print confirmation and return node
        if(current.name.equals(name)){

            //this operation is done so that the values can be easily passed to other methods that require them
            rewrite(passerCity,current);

            return passerCity; //passes
        }
        //searches through left and right of method, starting with left
        else{
            if(current.left != null){
                 searchCity(current.left,name);
            }
            if(current.right != null){
                 searchCity(current.right,name);
            }
        }
        return passerCity;
    }

    //method for various calculations regarding distance
    void cityDistance(String name1, String name2){

        //creates two new nodes, uses rewrite method to give them the values of the desired nodes
        City temp1 = new City(0, " ");
        rewrite(temp1,searchCity(root,name1));
        City temp2 = new City(0, " ");
        rewrite(temp2,searchCity(root,name2));

        //error messages if one or more of the cities can be found
        if(temp1.name.equals("error") && temp2.name.equals("error")){
            System.out.println("Neither " + name1 + " nor " + name2 + " can be found on this map");
            return;
        }
        else if(temp1.name.equals("error")){
            System.out.println(name1 + " cannot be found on this map");
            return;
        }
        else if(temp2.name.equals("error")){
            System.out.println(name2 + " cannot be found on this map");
            return;
        }

        //reports to user if each node is left or right of the root
        if(temp1.distance > 0){
            if(temp2.distance > 0)
                System.out.println("Both cities are east of " + root.name);
            else
                System.out.println(temp1.name + " is east of " + root.name + ", and " + temp2.name + " is west");
        }
        else if(temp1.distance < 0){
            if(temp2.distance < 0)
                System.out.println("Both cities are west of " + root.name);
            else
                System.out.println(temp2.name + " is east of " + root.name + ", and " + temp1.name + " is west");
        }


        //calculates total distance between two nodes, using their distance from the root, then informs user
        int totalDistance = Math.abs(temp1.distance - temp2.distance);
        System.out.println("Total distance between " + temp1.name + " and " + temp2.name + " is " + totalDistance + " miles");

        //is one left of node, one right of node

        //checks if either node is the parent of the other
        //not null checks are needed to avoid errors
        if(temp1.right != null && temp1.right.name.equals(temp2.name))
            System.out.println(temp2.name + " is the right child of " + temp1.name + " on the map");
        else if(temp1.left != null && temp1.left.name.equals(temp2.name))
            System.out.println(temp2.name + " is the left child of " + temp1.name + " on the map");
        else if(temp2.right != null && temp2.right.name.equals(temp1.name))
            System.out.println(temp1.name + " is the right child of " + temp2.name + " on the map");
        else if(temp2.left != null && temp2.left.name.equals(temp1.name))
            System.out.println(temp1.name+ " is the left child of " + temp2.name + " on the map");
        else
            System.out.println("Neither of the nodes are parents of each other");
    }
    public int totalDistance(String name){
        int distance = 0;
        return totalDistance(root,name,distance);
    }
    private int totalDistance(City current, String name, int distance){
        System.out.println("Start at " +current.name);
        totalDistance += current.distance;
        if(current.name.equals(name)){
            System.out.println("Found city" + current.distance);
            found = true;
            return totalDistance;
        }
        else{
            System.out.println("Didn't find" + current.distance);
            if(current.left != null && !found){
                System.out.println("Going left");
                totalDistance(current.left, name, distance);
            }
            if(current.right != null && !found){
                System.out.println("Going right" + current.distance);
                totalDistance(current.right, name, distance);
            }
        }
        System.out.println("Ending function");
        if(!found)
            totalDistance -= current.distance;
        return totalDistance;
    }



}
