import syntactic.SyntacticAnalyzer;

public class Main {

    public static void main(String args[]){
        SyntacticAnalyzer syntatic = new SyntacticAnalyzer("C:\\Users\\Wagner\\Documents\\GitHub\\Sapphire\\sapphirecompiler\\files\\"+ args[0] +".sapp");
        syntatic.run();
    }

}
