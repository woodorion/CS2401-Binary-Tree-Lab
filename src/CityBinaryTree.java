class  City{
    int data;
    City left;
    City right;
    public City(int item){
        data = item;
        left = null;
        right = null;
    }
}
class CityBinaryTree{
    City root;

    public CityBinaryTree(){
        root = null;
    }
    void printMapOrder(){
        int h = height(root);
        for(int i = 1; i <= h; i++){
            printLevel(root,i);
        }
    }
    int height(City root){
        if(root == null){
            return 0;
        }
        int heightLeft = height(root.left);
        int heightRight = height(root.right);

        if(heightLeft > heightRight){
            return(heightLeft+1);
        }
        return(heightRight+1);
    }
    void printLevel(City root, int level){
        if(root == null){
            return;
        }
        if(level == 1){
            System.out.print(root.data + " ");
        }
        else if(level > 1){
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
        }
    }
     void printLevelOrder(City root){
        int h = height(root);
        for(int i = 1; i <= h; i++){
            printLevel(root,i);
            System.out.println();
        }
    }
    void insertCity(String direction, int distance){
        //method takes east or west, and how far the new city is from the root city
        //this initial method will insert value if the first node after root is empty. Otherwise, it will call similar recursive method
        City current = root;

        if(direction.equalsIgnoreCase("west")){
            if(current.left == null){
                current.left = new City(distance);
            }
            insertCity(direction,distance);
        }
    }
    void insertCity(City root, String direction, int distance){
        //recursive method that acts similar to previous, but also takes in a node so that it can start at a higher level each iteration
    }

}

