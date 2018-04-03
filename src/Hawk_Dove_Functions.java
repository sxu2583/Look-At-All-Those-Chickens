import java.util.ArrayList;
import java.util.List;

public class Hawk_Dove_Functions {
    public static int hawks_population_default = 20;
    public static int resource_default = 50;
    public static int loss_default = 100;


    public static void argumentsChecker(String[] args){
        if(args.length > 4 || args.length == 0){
            System.err.println("Usage: ./project02 popSize [percentHawks] [resourceAmt] [costHawk-Bird]");
            System.exit(0);
        }
        System.out.println("Arguments check out");
    }

    //TODO: Make sure hawk values like 1.5 get rounded down
    public static Bird[] generate_population(int size, int percentage){
        //Use default
        double hawk_percentage = percentage;
        if (percentage == -1){ hawk_percentage = hawks_population_default / 100.0; }

        Bird[] birds = new Bird[size];
        double hawks = size * hawk_percentage;
        double doves = size - hawks;

        System.out.println("---------------------------------------");
        System.out.println("Hawk Percent " + hawk_percentage);
        System.out.println("Total Population " + size);
        System.out.println("Hawks "  + hawks);
        System.out.println("Doves " + doves);
        System.out.println("---------------------------------------");

        //Create all birds and set their id numbers
        int hawk_count = (int)hawks;
        int dove_count = (int)doves;
        for(int index = 0; index < size; index++){
            if (hawk_count != 0){
                birds[index] = new Bird("hawk");
                hawk_count -= 1;
            }
            else if (dove_count != 0){
                birds[index] = new Bird("dove");
                dove_count -= 1;
            }
            birds[index].set_id_number(index);
        }
        return birds;
    }

    //TODO: Setup the menu bars except the running ones and step
    public static void menu(String[] args){
        System.out.println("Here's where menu goes");
    }

    //TODO: Create function that actually runs through a sim; test with dove/dove
    public static void fight(){
        System.out.println("Simulation started");
    }

}
