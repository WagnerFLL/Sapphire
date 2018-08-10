import syntactic.SyntaticAnalyzer;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo fonte: ");
        String fileName = scan.nextLine();
        SyntaticAnalyzer syntatic = new SyntaticAnalyzer("files/"+ fileName +".sapp");
        syntatic.run();
    }

}
