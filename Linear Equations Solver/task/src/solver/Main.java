package solver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        try{
            Matrix m= file2Matrix(args[1]);
            File out= new File(args[3]);
            try(FileWriter writer = new FileWriter(out)) {
                Boolean res = m.solve();
                if (res == null) {
                    writer.write("Infinitely many solutions\n");
                } else if (res) {
                    for (double result : m.getResults()) {
                        writer.write(result + "\n");
                    }
                } else writer.write("No solutions\n");
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Matrix file2Matrix(String s) throws IOException{
            File file= new File(s);
            Scanner in = new Scanner(new FileInputStream(file));
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

    public static double[] constructLine(Scanner in ){
        String[] line= in.nextLine().split(" ");
        double[] a= new double[line.length];
        for(int i= 0; i< line.length; i++){
            a[i]= Double.parseDouble(line[i]);
        }
        return a;
    }


}