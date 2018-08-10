package syntactic;

import com.sun.javafx.binding.StringFormatter;
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

    public static String padRight(TokenCategory s) {
        return String.format("%1$-" + 10 + "s", s);
    }

    public void run() {

        NumberFormat formatter = new DecimalFormat("0000");


        try {
            this.lexical.openFile();
            while (lexical.hasMoreTokens()){
                Token token = lexical.nextToken();
                TokenCategory ctg = token.getCategory();
                System.out.println("        [" + formatter.format(token.getLine()) + ", " +
                                    formatter.format(token.getColumn()) + "]" +
                                    " (" + formatter.format(ctg.getCategoryValue()) +
                                    ", " + padRight(ctg) + ") " +
                                    token.getLexeme());

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

}
