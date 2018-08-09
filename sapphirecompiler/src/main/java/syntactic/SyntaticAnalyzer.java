package syntactic;

import lexical.LexicalAnalyzer;
import lexical.Token;

import java.util.Scanner;

public class SyntaticAnalyzer {

    private LexicalAnalyzer lexical;

    public SyntaticAnalyzer(String path) {
        this.lexical = new LexicalAnalyzer(path);
    }

    public void run() {
        this.lexical.readFile();
        while (lexical.hasMoreTokens()){
            Scanner a = new Scanner(System.in);
            a.nextLine();
            Token token = lexical.nextToken();
            System.out.println(token.getCategory() + "  e lexema: |" + token.getLexeme() + "|");
        }
    }

}
