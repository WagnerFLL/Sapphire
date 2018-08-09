import syntactic.SyntaticAnalyzer;

public class Main {

    public static void main(String args[]){
        SyntaticAnalyzer syntatic = new SyntaticAnalyzer("files/bruteste.sapp");
        syntatic.run();
        System.out.println("Fim!");
    }

}
