public class mainTest {
    public static void main(String[] args) {
        CityBinaryTree map = new CityBinaryTree();

        map.root = new City(1);
        //map.root.left = new City(2);
      //  map.root.right = new City(3);
        //map.root.left.left = new City(4);
       // map.root.left.right = new City(5);

        map.insertCity("west",7);
        map.printLevelOrder(map.root);
    }
}