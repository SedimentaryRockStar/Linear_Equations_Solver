package solver;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private final int size;
    private ArrayList<Row> matrix;

    public Matrix(int size, double[][] lines){
        this.size= size;
        matrix= new ArrayList<>();
        for(double[] line: lines){
            matrix.add(new Row(line));
        }
    }

    public Matrix(){
        size= -1;
    }

    public int getSize() {
        return size;
    }

    public void print(){
        for(Row row: matrix) {
            System.out.println(Arrays.toString(row.line));
        }

    }

    public double[] getResults(){
        double[] results= new double[size];
        int i= 0;
        for(Row row: matrix){
            results[i]= row.result;
            i++;
        }
        return results;
    }

    public void updateResults(){
        for(Row row: matrix){
            row.result= row.line[size];
        }
    }

    public boolean solve(){
        for(int i= 0; i< size; i++){
            Row curRow= matrix.get(i);
            curRow.multiRow();
            for(int j= i+ 1; j< size; j++){
                matrix.get(j).addRow(curRow);
            }
        }
        if(matrix.get(size- 1).line[size- 1]== 0 && matrix.get(size- 1).line[size]!= 0) return false;
        for(int i= size- 1; i>= 0; i--){
            for(int j= i- 1; j>= 0; j--) {
                matrix.get(j).deductRow(matrix.get(i));
            }
        }

        updateResults();

        return true;
    }








}
