class DeBlasioTests{
    public static void main(String[] args){
        CityBinaryTree map1 = new CityBinaryTree();
        map1.insertCity(0, "R");
        map1.insertCity(4, "W4");
        map1.insertCity(6, "W6");
        map1.insertCity(-6, "E6");
        map1.insertCity(-4, "E4");
        map1.insertCity(-8, "E8");

        map1.root.print();

        System.out.println("Should print:\nR (0)\n├ E6 (6)\n│ ├ E8 (2)\n│ └ E4 (2)\n└ W4 (4)\n  ├ null\n  └ W6 (2)");
    }
}