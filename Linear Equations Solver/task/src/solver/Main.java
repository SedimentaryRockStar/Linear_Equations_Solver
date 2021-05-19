package solver;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        Matrix m= file2Matrix(args[0]);
        if(m.getSize()== -1) throw new IllegalArgumentException(" The matrix is not valid");
        //Write the results to the target
        File outPut= new File(args[1]);
        try(PrintWriter printWriter = new PrintWriter(outPut)){
            for(double result: m.getResults()){
                printWriter.println(result);
            }
        }catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }



    }


    public static Matrix file2Matrix(String file) throws Exception{
        File f = new File(file);
        try {
            Scanner in = new Scanner(f);
            int size= Integer.parseInt(in.nextLine());
            double[][] m= new double[size][];
            int i= 0;
            do{
                m[i]= constructLine(in);
                i++;
            }while(in.hasNextLine());
            return new Matrix(size, m);
        }catch(FileNotFoundException e) {
            System.out.println("The file is not founded!!!");
            return new Matrix();
        }
    }

    public static double[] constructLine(Scanner in ){
        String[] line= in.nextLine().split(" ");
        double[] a= new double[line.length];
        for(int i= 0; i< line.length; i++){
            a[i]= Double.parseDouble(line[i]);
        }
        return a;
    }


}