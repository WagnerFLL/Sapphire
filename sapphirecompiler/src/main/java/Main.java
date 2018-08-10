import syntactic.SyntaticAnalyzer;

public class Main {

    public static void main(String args[]){
        SyntaticAnalyzer syntatic = new SyntaticAnalyzer("files/"+ args[0] +".sapp");
        syntatic.run();
    }

}
