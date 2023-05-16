package engine;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Type the file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Product> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();

            }
            //Agora usando pipeline/strem na lista criada para achar o valor médio

            double avg = list.stream()
                    .map( p-> p.getPrice())
                    .reduce(0.0, (x,y) -> x + y ) / list.size();

            System.out.println("Average Price: " + String.format("%.2f",avg));




        }catch (IOException e){
            System.out.println("Error message: " + e.getMessage());


        }

        sc.close();

    }
}
