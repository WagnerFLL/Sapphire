import syntactic.SyntaticAnalyzer;

public class Main {

    public static void main(String args[]){
        SyntaticAnalyzer syntatic = new SyntaticAnalyzer("C:\\Users\\Wagner\\Documents\\GitHub\\Sapphire\\sapphirecompiler\\files\\"+ args[0] +".sapp");
        syntatic.run();
    }

}
