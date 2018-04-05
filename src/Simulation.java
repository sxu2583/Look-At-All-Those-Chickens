/*
@Author:Shihab Uddin

Error/Edge Cases to Check:

- Make sure the hawks population accounts for 1.5
- Make sure that two alive birds are always chosen
- Account for when only 2 birds are alive
- End when 1 bird or no birds are alive
 */

import java.util.Scanner;

public class Simulation extends World {
    public static void main(String[] args){
        //Check error cases for arguments
        argumentsChecker(args);

        //Check the correct argument data gets read in
        int size = Integer.parseInt(args[0]);

        //Set the hawk percent if arg is not used
        int hawk_percent = hawks_population_default;
        if (args.length >= 2){ hawk_percent = Integer.parseInt(args[1]); }
        int resource = resource_default;
        if (args.length >= 3){ resource = Integer.parseInt(args[2]); }
        int loss = loss_default;
        if (args.length == 4){ loss = Integer.parseInt(args[3]); }

        //Generate all the birds
        Bird[] all_birds = generate_population(size, hawk_percent);

        //Count how many hawks
        int hawk_count = 0;
        for (int bird = 0; bird < all_birds.length; bird++){
            if (all_birds[bird].strategy.equals("hawk")){
                hawk_count += 1;
            }
        }

        //Need to update the percent value used after rounding
        float updated_hawks_percent = (hawk_count / (float)size) * 100;
        hawk_percent = (int)updated_hawks_percent;


        //Menu
        Scanner scanner = new Scanner(System.in);
        boolean play_game  = true;
        while (play_game) {
            menu();
            System.out.print(">");
            String input = scanner.next();
            switch (input) {
                case "1":
                    status(size, hawk_percent, hawk_count ,resource, loss);
                    break;
                case "2":
                    display_individuals(all_birds);
                    break;
                case "3":
                    display_sorted(all_birds);
                    break;
                case "4":
                    all_birds = n_interactions(1000, all_birds, resource, loss, size);
                    break;
                case "5":
                    all_birds = n_interactions(10000, all_birds,resource, loss, size);
                    break;
                case "6":
                    System.out.print("Enter Your Desired N Interactions: ");
                    int N = scanner.nextInt();
                    all_birds = n_interactions(N, all_birds, resource, loss, size);
                    break;
                case "7":
                    all_birds = interaction(all_birds,resource, loss, size);
                    break;
                case "8":
                    play_game = false;
                    System.out.println("Simulation Over");
                    break;
                default:
                    menu();
                    break;
            }
        }
    }
}
