package javaFundamentals;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args){
        Scanner file= new Scanner(System.in);
        System.out.println("Please specify your \n >");
        File inputFile= new File(file.nextLine());
        File outputFile= new File("out.txt");
        File operationProcess= new File("output.txt");
        StringBuilder builder= new StringBuilder();
        try ( Scanner in = new Scanner(new FileInputStream(inputFile))){
            Matrix m= file2Matrix(in);
            try(FileWriter writer = new FileWriter(outputFile)) {
                Boolean res = m.solve(builder); //Use the three kinds of return types to distinguish different results.
                try(FileWriter wr = new FileWriter(operationProcess)) {
                    if (res == null) {
                        writer.write("Infinitely many solutions\n");
                        wr.write("Infinitely many solutions\n");
                    } else if (res) {
                        double[] ans= m.getResults();
                        for (int i= 0; i< ans.length; i++) {
                            writer.write("X"+ i+ "= " + ans[i]+ "\n");
                        }
                        wr.write(builder.toString());
                    } else {
                        writer.write("No solutions\n");
                        wr.write("No solutions\n");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
            try(FileWriter writer = new FileWriter(operationProcess)) {
                writer.write(builder.toString());
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A helper function here to construct a matrix merely from a file input
     * @param in Takes in a Scanner variable linked on the input file.
     * @return a matrix object for further operation
     * @throws FileNotFoundException Deals with the case when the input file source is not valid
     */

    public static Matrix file2Matrix(Scanner in) throws FileNotFoundException{
        String str= in.nextLine();
        String[] params= str.split(" ");
        int length= Integer.parseInt(params[0])+ 1;
        int size=  Integer.parseInt(params[1]);
        double[][] m= new double[size][];
        int i= 0;
        do{
            m[i]= constructLine(in);
            i++;
        }while(in.hasNextLine());
        return new Matrix(size, length, m);
    }


    /**
     * A helper function of the previous function to separate the work done by it
     * @param in The same scanner as in the last function
     * @return A constructed line from a String
     */
    public static double[] constructLine(Scanner in ){
        String[] line= in.nextLine().split(" ");
        double[] a= new double[line.length];
        for(int i= 0; i< line.length; i++){
            a[i]= Double.parseDouble(line[i]);
        }
        return a;
    }


}