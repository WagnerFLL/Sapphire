package syntactic;

import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SyntaticAnalyzer {

    private LexicalAnalyzer lexical;

    public SyntaticAnalyzer(String path) {
        this.lexical = new LexicalAnalyzer(path);
    }

    public void run() {

        NumberFormat formatter = new DecimalFormat("0000");

        try {
            this.lexical.readFile();
            while (lexical.hasMoreTokens()){
                Token token = lexical.nextToken();
                TokenCategory ctg = token.getCategory();
                System.out.println("[" + formatter.format(token.getLine()) + ", " +
                                    formatter.format(token.getColumn()) + "]" +
                                    " (" + formatter.format(ctg.getCategoryValue()) +
                                    ", " + ctg + ") " +
                                    token.getLexeme());

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
