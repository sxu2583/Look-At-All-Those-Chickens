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
        int hawk_percent = -1;
        if (args.length >= 2){ hawk_percent = Integer.parseInt(args[1]); }
        int resource = resource_default;
        if (args.length >= 3){ resource = Integer.parseInt(args[2]); }
        int loss = loss_default;
        if (args.length == 4){ loss = Integer.parseInt(args[3]); }

        //Generate all the birds
        Bird[] all_birds = generate_population(size, hawk_percent);
        int hawk_count = 0;
        for (int bird = 0; bird < all_birds.length; bird++){
            if (all_birds[bird].strategy.equals("hawk")){
                hawk_count += 1;
            }
        }

        //Choosing the birds has been set
        Bird[] bird_pair;
        bird_pair = random_Pick(all_birds, size);

        System.out.println("The Two Birds Chosen");
        System.out.println(bird_pair[0].strategy + " " + bird_pair[0].id_number + " " + bird_pair[0].alive);
        System.out.println(bird_pair[1].strategy + " " + bird_pair[1].id_number + " " + bird_pair[1].alive);

        //Menu
        Scanner scanner = new Scanner(System.in);
        menu();
        boolean play_game  = true;
        while (play_game) {
            System.out.print(">");
            String input = scanner.next();
            switch (input) {
                case "1":
                    status(size, hawk_percent, hawk_count ,resource, loss);
                    break;
                case "2":
                    System.out.println("Hello World");
                    break;
                case "3":
                    System.out.println("Hello World");
                    break;
                case "4":
                    System.out.println("Hello World");
                    break;
                case "5":
                    System.out.println("Hello World");
                    break;
                case "6":
                    System.out.println("Hello World");
                    break;
                case "7":
                    System.out.println("Hello World");
                    break;
                case "8":
                    play_game = false;
                    System.out.println("Simulation Over");
                    break;
            }
        }
    }
}
