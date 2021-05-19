package solver;


import java.util.Arrays;

public class Matrix {
    private final int size;
    private double[][] matrix;

    public Matrix(int size, double[][] lines){
        this.size= size;
        matrix= new double[size+ 1][size+ 1];
        for(int i= 0; i< lines.length; i++){
            System.arraycopy(lines[i], 0, matrix[i], 0, lines[i].length);
        }
    }

    public Matrix(){
        size= -1;
    }

    public int getSize() {
        return size;
    }

    public void print(){
        for(double[] line: matrix) {
            System.out.println(Arrays.toString(line));
        }

    }

    public double[] getResults(){
        double[] result= new double[size];
        for(int i= 0; i< matrix.length; i++){
            result[i]= matrix[i][size- 1];
        }
        return result;
    }

    public void solve(){

    }





}
