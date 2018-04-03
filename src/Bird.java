public class Bird extends Hawk_Dove_Functions {
    String strategy;
    int resource;
    int alive;
    int id_number;

    public Bird(String strategy){
        this.strategy = strategy;
        this.resource = 0;
        this.alive = 1;
        this.id_number = 0;
    }

    //Allows our objects to update their values
    public void update_Resource(int value){
        resource = value;
    }

    //Kills a bird
    public void died(){
        alive = 0;
    }

    //Allows us to give each bird an id
    public void set_id_number(int number){
        id_number = number;
    }

    //Just a test
    public void tweet(){
        System.out.println("Tweet");
    }

}
