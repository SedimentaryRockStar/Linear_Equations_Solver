package solver;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private final int size;
    private final int length;
    private ArrayList<Row> matrix;

    public Matrix(int size, int length, double[][] lines){
        this.size= size;
        this.length= length;
        matrix= new ArrayList<>();
        for(int i= 0; i< lines.length; i++){
            matrix.add(new Row(lines[i], i));
        }
    }

    public void print(){
        for(Row row: matrix) {
            System.out.println(Arrays.toString(row.line));
        }
    }

    public double[] getResults(){
        double[] results= new double[Math.min(size, length- 1)];
        for(int i= 0; i< results.length; i++){
            results[i]= matrix.get(i).result;
        }
        return results;
    }

    public void updateResults(){
        for(int i= 0; i< Math.min(size, length- 1); i++){
            matrix.get(i).result= (double) Math.round(matrix.get(i).line[length- 1]* 100)/ 100;
        }
    }

    /**
     * This is the function to solve the linear equations matrix.
     * @return Use Wrapper Boolean to to indicate 3 cases: Infinitely many solutions, No solutions, Has a solution
     */

    public Boolean solve(StringBuilder str){
        str.append("This is a "+ size+ " by"+ length+ " matrix.\n");
        str.append("With steps to solve the matrix followed.\n");
        for(int i= 0; i< size; i++){
            int idx= i;
            while(idx< length && !matAdjustment(i, idx, str)){
                idx++;
            }
            if(idx!= length) {
                Row curRow = matrix.get(i);
                curRow.multiRow(idx, str);
                for (int j = i + 1; j < size; j++) {
                    matrix.get(j).addRow(curRow, idx, str);
                }
            }
        }
        for(int i= size- 1; i>= 0; i--){
            Row row= matrix.get(i);
            int idx= row.leadingNum();
            if (idx== -1 ) continue;
            if (idx== length- 1) return false;
            for(int j= i- 1; j>= 0; j--) {
                matrix.get(j).addRow(row, idx, str);
            }
        }
        if(matrix.get(size- 1).leadingNum()== -1 || length- 1> size) return null; // Some infinite solutions cases
        updateResults();

        str.append("Matrix/ Linear equations solved!");
        return true;
    }

    /**
     * A helper function to adjust the matrix to swap the line and the index
     * @param row indicates the row index
     * @param column indicates the column index
     * @return a boolean variable indicating whether the adjustment successful or not
     */

    public boolean matAdjustment(int row, int column, StringBuilder str){
        if( matrix.get(row).line[column]!= 0) return true;
        for(int i= row+ 1; i< size; i++){
            if( matrix.get(i).line[column]!= 0){
                double[] tmp= matrix.get(i).line;
                matrix.get(i).line= matrix.get(row).line;
                matrix.get(row).line= tmp;
                int idx= matrix.get(i).index;
                matrix.get(i).index= matrix.get(row).index;
                matrix.get(row).index= idx;
                str.append("Interchange Row"+ row+ "<--->"+ " Row"+ i+"\n");
                return true;
            }
        }
        return false;
    }


}