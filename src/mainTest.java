public class mainTest {
    public static void main(String[] args) {
        CityBinaryTree map = new CityBinaryTree();

      map.insertCity(0, "town 1");
      map.insertCity(-7, "town 2");
      map.insertCity(4, "town 3");
      map.insertCity(-10,"town 4");
      map.insertCity(90,"town 5");
      map.insertCity(20, "town 6");

      map.cityDistance("town 4", "town 6");

        System.out.println("Height is : " + map.mapHeight(map.root) );


        map.printEntire(map.root);
    }
}