/*
@Author:Shihab Uddin
 */

public class Simulation extends Hawk_Dove_Functions {
    public static void main(String[] args){
        //Check error cases for arguments
        argumentsChecker(args);

        //Check the correct argument data gets read in
        int size = Integer.parseInt(args[0]);
        int hawk_percent = -1;
        if (args.length >= 2){ hawk_percent = Integer.parseInt(args[1]); }
        int resource = resource_default;
        if (args.length >= 3){ resource = Integer.parseInt(args[2]); }
        int loss = loss_default;
        if (args.length == 4){ loss = Integer.parseInt(args[3]); }

        //Generate all the birds
        Bird[] normal = generate_population(size, hawk_percent);
        for (int i =0; i < 10; i++){
            System.out.println(normal[i].strategy + " " + normal[i].id_number);
        }



    }
}
