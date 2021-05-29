package solver;

public class Row {
    double result;
    int length;
    double[] line;
    // Construct every row for further operation
    public Row(double[] line){
        this.length= line.length;
        this.result= line[length- 1];
        this.line= line;
    }

    public int leadingNum(){
        for(int i= 0; i< length; i++){
            if(line[i]!= 0) return i;
        }
        return -1;
    }

    public double addRow(Row aRow, int idx){
        double coefficient= line[idx]/aRow.line[idx];
        for(int i= idx; i< line.length; i++){
            line[i]-= aRow.line[i]* coefficient;
        }
        return coefficient;
    }

    public double deductRow(Row aRow, int idx){
        double coefficient= line[idx]/aRow.line[idx];
        for(int i= idx; i< line.length; i++){
            line[i]-= aRow.line[i]* coefficient;
        }
        return coefficient;
    }

    public double multiRow(int idx){
        double coefficient= 1/line[idx];
        if (coefficient== 1) return coefficient;
        for(int i= idx; i< line.length; i++){
            line[i]= line[i]* coefficient;
        }
        return coefficient;
    }

}