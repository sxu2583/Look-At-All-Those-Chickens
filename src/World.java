import java.util.*;

public class World {
    public static int hawks_population_default = 20;
    public static int resource_default = 50;
    public static int loss_default = 100;


    public static void argumentsChecker(String[] args){
        if(args.length > 4 || args.length == 0){
            System.err.println("Usage: ./project02 popSize [percentHawks] [resourceAmt] [costHawk-Bird]");
            System.exit(0);
        }
    }

    public static Bird[] generate_population(int size, int percentage){
        //Use default
        double hawk_percentage = percentage / 100.0;
        //if (percentage == -1){ hawk_percentage = hawks_population_default / 100.0; }

        Bird[] birds = new Bird[size];
        double hawks = Math.floor(size * hawk_percentage);
        double doves = size - hawks;

        /*
        System.out.println("---------------------------------------");
        System.out.println("Hawk Percent " + hawk_percentage);
        System.out.println("Total Population " + size);
        System.out.println("Hawks "  + hawks);
        System.out.println("Doves " + doves);
        System.out.println("---------------------------------------");
        */

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


    //Just pass in an array of bird objects and it will check if there are more then 2 alive
    public static boolean is_Alive(Bird[] birds){
        boolean answer = true;
        int count = 0;
        for (int bird = 0 ; bird < birds.length; bird++){
            if (birds[bird].alive == 1){count += 1; }
        }
        if (count < 2){ answer = false; }
        return answer;
    }

    public static int how_many_alive(Bird[] birds){
        int count = 0;
        for (int bird = 0; bird < birds.length; bird++){
            if (birds[bird].alive == 1){
                count += 1;
            }
        }
        return count;
    }

    //TODO: FIX SAME BIRD ISSUE
    //Make Sure Random Pick Can't Pick Two Same Birds
    public static Bird[] random_Pick(Bird[] birds, int size){
        Bird[] chosen = new Bird[2];
        Random random = new Random();
        int min;
        int num1;
        int num2;

        if (size == 0){size = size + 1;}

        min = 0;
        num1 = random.nextInt(size - min) + min;
        num2 = random.nextInt(size - min) + min;

        //Make sure we always have different numbers
        if (num1 == num2 && size != 1) {
            while (num1 == num2) {
                num2 = random.nextInt(size - min) + min;
            }
        }

        //Check to see if there are more then 2 birds alive
        if (how_many_alive(birds) >= 2) {
            //Make sure our 2 chosen birds are alive
            while (birds[num1].alive == 0 || birds[num2].alive == 0) {
                num1 = random.nextInt(size - min) + min;
                num2 = random.nextInt(size - min) + min;
            }
            chosen[0] = birds[num1];
            chosen[1] = birds[num2];
        } else {
            //All dead or one alive
            chosen = null;
        }
        return chosen;
    }

    public static void menu(){
        System.out.println();
        System.out.println("===============MENU=============");
        System.out.println("1 ) Starting Stats");
        System.out.println("2 ) Display Individuals and Points");
        System.out.println("3 ) Display Sorted");
        System.out.println("4 ) Have 1000 interactions");
        System.out.println("5 ) Have 10000 interactions");
        System.out.println("6 ) Have N interactions");
        System.out.println("7 ) Step through interactions 'Stop' to return to menu");
        System.out.println("8 ) Quit");
        System.out.println("================================");
    }

    public static void status(int population, int hawks_percentage, int hawk_count, int resource, int loss){

        //Edge case handler
        int dove_percentage = 100 - hawks_percentage;
        if (population == 0){dove_percentage = 0;}

        System.out.println("" +
                "Population size: " + population + "\n" +
                "Percentage of Hawks: " + hawks_percentage + "%\n" +
                "Number of Hawks: " + hawk_count +"\n" +
                "\n" +
                "Percentage of Doves: " + dove_percentage + "%\n" +
                "Number of Doves: " + (population - hawk_count) + "\n" +
                "\n" +
                "Each resource is worth: " + resource + "\n" +
                "Cost of Hawk-Hawk interaction: " + loss);
    }

    public static Bird[] update_encounter(Bird[] birds){
        for (int bird = 0; bird < birds.length; bird++){
            birds[bird].update_encounter();
        }
        return birds;
    }


    public static Bird[] interaction(Bird[] birds, int resource_amount, int loss, int size){
        Bird[] all_birds = birds;
        Scanner scanner = new Scanner(System.in);

        //todo: maybe use a do while instead of a while
        //todo: When done set the menu to show up
        System.out.println("Interaction start (Press Enter to Step)");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Stop")){ break;}

            Bird[] bird_pair = random_Pick(birds, size);
            if (bird_pair == null) {
                System.out.println("Not enough Alive birds to continue");
                break;
            }

            //TODO: FIND THE REAL ISSUE SO YOU DONT NEED TO USE THIS ERROR
            if (bird_pair[0].id_number == bird_pair[1].id_number){
                random_Pick(birds, size);
            }

            //Increment encounter of all birds by 1
            all_birds = update_encounter(all_birds);

            //If both birds are doves
            if (bird_pair[0].strategy.equals("dove") && bird_pair[1].strategy.equals("dove")){
                int resource_split = resource_amount / 2;
                bird_pair[0].update_Resource(resource_split);
                bird_pair[1].update_Resource(resource_split);

                System.out.println("Two Doves");
                System.out.println("" +
                        "Encounter: " + bird_pair[0].encounter +"\n" +
                        "Individual " + bird_pair[0].id_number + ": Dove\n" +
                        "Individual " + bird_pair[1].id_number + ": Dove\n" +
                        "Dove/Dove: Dove: +" + resource_split + "\tDove: +" + resource_split + "\n" +
                        "Individual "+ bird_pair[0].id_number +"="+ bird_pair[0].resource +"\t        " +
                        "Individual "+ bird_pair[1].id_number +"="+ bird_pair[1].resource);

                //Add the two updated birds back to the pack
                all_birds[bird_pair[0].id_number] = bird_pair[0];
                all_birds[bird_pair[1].id_number] = bird_pair[1];
            }

            //If one hawk and one dove
            if (bird_pair[0].strategy.equals("hawk") && bird_pair[1].strategy.equals("dove")){
                bird_pair[0].update_Resource(resource_amount);
                bird_pair[1].update_Resource(0);

                System.out.println("One hawk and one dove");
                System.out.println("" +
                        "Encounter: " + bird_pair[0].encounter +"\n" +
                        "Individual " + bird_pair[0].id_number + ": Hawk\n" +
                        "Individual " + bird_pair[1].id_number + ": Dove\n" +
                        "Hawk/Dove: Hawk: +" + resource_amount + "\tDove: +0 \n" +
                        "Individual "+ bird_pair[0].id_number +"="+ bird_pair[0].resource +"\t        " +
                        "Individual "+ bird_pair[1].id_number +"="+ bird_pair[1].resource);

                //Add the two updated birds back to the pack
                all_birds[bird_pair[0].id_number] = bird_pair[0];
                all_birds[bird_pair[1].id_number] = bird_pair[1];
            }

            //If one dove and one hawk
            if (bird_pair[0].strategy.equals("dove") && bird_pair[1].strategy.equals("hawk")){
                bird_pair[0].update_Resource(0);
                bird_pair[1].update_Resource(resource_amount);

                System.out.println("One dove and one hawk");
                System.out.println("" +
                        "Encounter: " + bird_pair[0].encounter +"\n" +
                        "Individual " + bird_pair[0].id_number + ": Dove\n" +
                        "Individual " + bird_pair[1].id_number + ": Hawk\n" +
                        "Dove/Hawk: Dove: +0" + "\tHawk: +"+ resource_amount+" \n" +
                        "Individual "+ bird_pair[0].id_number +"="+ bird_pair[0].resource +"\t        " +
                        "Individual "+ bird_pair[1].id_number +"="+ bird_pair[1].resource);

                //Add the two updated birds back to the pack
                all_birds[bird_pair[0].id_number] = bird_pair[0];
                all_birds[bird_pair[1].id_number] = bird_pair[1];
            }

            //If both birds are hawks
            if (bird_pair[0].strategy.equals("hawk") && bird_pair[1].strategy.equals("hawk")){

                //Give first hawk the resources
                bird_pair[0].update_Resource(resource_amount);
                bird_pair[1].update_Resource(0);

                //inflict loss
                bird_pair[0].lose_Resource(loss);
                bird_pair[1].lose_Resource(loss);

                //To account for the plus minus symbol
                String hawk_result;
                if ((resource_amount - loss) > 0){
                    hawk_result = "+" + (resource_amount - loss);
                }else if ((resource_amount - loss) < 0) {
                    hawk_result = Integer.toString(resource_amount -loss);
                }else {hawk_result = "0";}

                System.out.println("Two hawks");
                System.out.println("" +
                        "Encounter: " + bird_pair[0].encounter +"\n" +
                        "Individual " + bird_pair[0].id_number + ": Hawk\n" +
                        "Individual " + bird_pair[1].id_number + ": Hawk\n" +
                        "Hawk/Hawk: Hawk: "+ hawk_result + "\tHawk: -"+ loss);

                //Check if Hawks died
                if (bird_pair[0].resource < 0){
                    bird_pair[0].died();
                    System.out.print("Hawk one has died!");
                }

                if (bird_pair[1].resource < 0){
                    bird_pair[1].died();
                    System.out.println("\nHawk two has died!");
                }

                System.out.print(
                        "Individual "+ bird_pair[0].id_number +"="+ bird_pair[0].resource +"\t    " +
                        "Individual "+ bird_pair[1].id_number +"="+ bird_pair[1].resource + "\n");

                //Add the two updated birds back to the pack
                all_birds[bird_pair[0].id_number] = bird_pair[0];
                all_birds[bird_pair[1].id_number] = bird_pair[1];
            }
        }
        return all_birds;
    }


    public static void display_individuals(Bird[] birds){
        for (int bird = 0; bird < birds.length; bird++){
            System.out.print("Individual[" + birds[bird].id_number + "]=");

            //Check Name
            String strat;
            if (birds[bird].strategy.equals("dove")){
                strat = "Dove";
            }else {
                strat = "Hawk";
            }

            //Set as Dead
            if (birds[bird].alive == 0){
                strat = "DEAD";
            }

            System.out.print(strat + ":" + birds[bird].resource +"\n");
        }
        System.out.println("Living:" + how_many_alive(birds));
    }


    public static void display_sorted(Bird[] birds){
        Bird[] sorted_birds = new Bird[birds.length];
        Integer[] resource_amounts = new Integer[birds.length];
        for (int bird = 0; bird < birds.length; bird++){
            resource_amounts[bird] = birds[bird].resource;
        }
        //Sort resource array in descending order
        Arrays.sort(resource_amounts, Collections.reverseOrder());

        ArrayList<Integer> checked = new ArrayList<>();
        for (int resource = 0; resource < resource_amounts.length; resource++){
            for (int bird = 0; bird < birds.length; bird++){
                if ((birds[bird].resource == resource_amounts[resource]) && !checked.contains(birds[bird].id_number)){
                    sorted_birds[resource] = birds[bird];
                    checked.add(birds[bird].id_number);
                    break;
                }
            }
        }

        //Display Sorted
        for (int bird = 0; bird < sorted_birds.length; bird++){
            //Check Name
            String strat;
            if (sorted_birds[bird].strategy.equals("dove")){ strat = "Dove"; }
            else { strat = "Hawk"; }

            //Set as Dead
            if (sorted_birds[bird].alive == 0){ strat = "DEAD"; }
            System.out.print(strat + ":" + sorted_birds[bird].resource +"\n");
        }

    }
}
