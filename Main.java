import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        for (int i = 0; i < list.size(); i++){
            String[] info = list.get(i).split("\\s+");
            int does = Integer.parseInt(info[0]);
            int bucks = Integer.parseInt(info[1]);
            ArrayList<Integer> bucksPopulation = new ArrayList<Integer>();
            ArrayList<Integer> doesPopulation = new ArrayList<Integer>();
            ArrayList<Integer> totalPopulation = new ArrayList<Integer>();
            System.out.println();
            System.out.println("Starting with " + does + " doe(s) and " + bucks + " buck(s)...");


            for (int k = 0; k < 10; k++) {
                RabbitSimulation rs = new RabbitSimulation();
                rs.generate(bucks, does);
                for (int j = 0; j < 365; j++) {
                    rs.oneDay();
                }
                bucksPopulation.add(rs.getBucks());
                doesPopulation.add(rs.getDoes());
                totalPopulation.add(rs.getDoes() + rs.getBucks());

                System.out.println("Trial " + k + ": " + (rs.getBucks() + rs.getDoes()) + " was the final population of rabbits; " + rs.getDoes() + " does, " + rs.getBucks() + " bucks.");
            }
            double doesSum = 0.0;
            double bucksSum = 0.0;
            double totalSum = 0.0;

            for (int k = 0; k < bucksPopulation.size(); k++) {
                bucksSum += bucksPopulation.get(k);
            }
            for (int k = 0; k < doesPopulation.size(); k++) {
                doesSum += doesPopulation.get(k);
            }
            for (int k = 0; k < totalPopulation.size(); k++) {
                totalSum += totalPopulation.get(k);
            }

            double doesSD = 0.0;
            double bucksSD = 0.0;
            double totalSD = 0.0;

            double totalMean = totalSum / totalPopulation.size();
            double doesMean = doesSum / doesPopulation.size();
            double bucksMean = bucksSum / bucksPopulation.size();

            for (int l = 0; l < bucksPopulation.size(); l++) {
                bucksSD += Math.pow(bucksPopulation.get(l) - bucksMean, 2);
            }
            for (int l = 0; l < doesPopulation.size(); l++) {
                doesSD += Math.pow(doesPopulation.get(l) - doesMean, 2);
            }
            for (int l = 0; l < totalPopulation.size(); l++) {
                totalSD += Math.pow(totalPopulation.get(l) - (doesMean + bucksMean), 2);
            }

            bucksSD /= bucksPopulation.size();
            doesSD /= doesPopulation.size();
            totalSD /= totalPopulation.size();
            bucksSD = Math.sqrt(bucksSD);
            doesSD = Math.sqrt(doesSD);
            totalSD = Math.sqrt(totalSD);

            DecimalFormat df = new DecimalFormat("0.000");

            System.out.println("Average number of rabbits: " +  df.format(totalMean) + " with standard deviation of " +  df.format(totalSD));
            System.out.println("Average number of female rabbits: " +  df.format(doesMean) + " with standard deviation of " +  df.format(doesSD));
            System.out.println("Average number of male rabbits: " + df.format(bucksMean) + " with standard deviation of " +  df.format(bucksSD));

        }
    }
}