package solver;

public class Row {
    double result;
    int length;
    double[] line;
    int index;


    // Construct every row for further operation
    public Row(double[] line, int index){
        this.length= line.length;
        this.result= line[length- 1];
        this.line= line;
        this.index= index;
    }

    public int leadingNum(){
        for(int i= 0; i< length; i++){
            if(line[i]!= 0) return i;
        }
        return -1;
    }

    public double addRow(Row aRow, int idx, StringBuilder str){
        double coefficient= line[idx]/aRow.line[idx];
        for(int i= idx; i< line.length; i++){
            line[i]-= aRow.line[i]* coefficient;
        }
        str.append("Row "+ this.index+ " adds "+ (double) Math.round(coefficient* 100)/ 100+"x Row "+ aRow.index+ "\n");
        return coefficient;
    }


    public double multiRow(int idx, StringBuilder str){
        double coefficient= 1/line[idx];
        if (coefficient== 1 || coefficient== 0) return coefficient;
        for(int i= idx; i< line.length; i++){
            line[i]= line[i]* coefficient;
        }
        str.append("Row "+ this.index+ " times "+ (double) Math.round(coefficient* 100)/ 100 + " itself\n");
        return coefficient;
    }

}