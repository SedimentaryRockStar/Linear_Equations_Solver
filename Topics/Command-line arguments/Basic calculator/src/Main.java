class Problem {
    public static void main(String[] args) {
        char operator= args[0].charAt(0);
        int operand1= Integer.parseInt(args[1]);
        int operand2= Integer.parseInt(args[2]);
        if(operator== '+'){
            System.out.println(operand1+ operand2);
        }else if(operator== '-'){
            System.out.println(operand1- operand2);
        }else if(operator== '*'){
            System.out.println(operand1* operand2);
        }else{
            System.out.println("Unknown operator");
        }
        
    }
}
