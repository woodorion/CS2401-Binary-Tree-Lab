//prefer the test that Dr. De Blasio implemented, adding to my own tests
public class mainTest {
    public static void main(String[] args) {
        CityBinaryTree map = new CityBinaryTree();

        map.insertCity(0, "R");
        map.insertCity(8, "E8");
        map.insertCity(6, "E6");
        map.insertCity(12,"E12");
        map.insertCity(10,"E10");
        map.insertCity(-2, "W2");
        map.insertCity(-10,"W10");
        map.insertCity(-5,"W5");

        //code to set insert function. Seems fully operational
        //map.root.print();
       // System.out.println("Should print:\nR (0)\n├ E8 (8)\n│ ├ E12 (4)\n| | ├ null \n| | └ E10 (-2)\n| └ E6 (-2)\n└ W2 (-2)\n  ├ null \n  └ W10 (-8)\n    ├ W5 (3)\n    └ null");

        map.cityDistance("R", "E12");
        System.out.println("Total distance: " + map.totalDistance("W5"));
    }
}