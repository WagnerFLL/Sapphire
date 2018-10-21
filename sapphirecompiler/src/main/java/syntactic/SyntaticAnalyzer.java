package syntactic;

import Grammar.Grammar;
import Grammar.Production;
import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SyntaticAnalyzer {

    private LexicalAnalyzer lexical;
    private Stack<Node> stack;
    private String[][] actionTable;
    private List<Production> productions;
    private Map<String, Integer> map;
    private int[][] trasitions;

    public SyntaticAnalyzer(String path) {
        this.lexical = new LexicalAnalyzer(path);
        this.stack = new Stack<>();
        this.actionTable = SLRTableAction.action;
        this.productions = Grammar.productions;
        this.map = SLRTableTransition.map;
        this.trasitions = SLRTableTransition.transitions;
    }

//    public static String padRight(TokenCategory s) {
//        return String.format("%1$-" + 10 + "s", s);
//    }

//    public void run() {
//
//        NumberFormat formatter = new DecimalFormat("0000");
//
//
//        try {
//            this.lexical.openFile();
//            while (lexical.hasMoreTokens()){
//                Token token = lexical.nextToken();
//                TokenCategory ctg = token.getCategory();
//                System.out.println(ctg);
////                System.out.println("        [" + formatter.format(token.getLine()) + ", " +
////                                    formatter.format(token.getColumn()) + "]" +
////                                    " (" + formatter.format(ctg.getCategoryValue()) +
////                                    ", " + padRight(ctg) + ") " +
////                                    "{" + token.getLexeme() + "}");
//
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }

    public void run() {

        try {

            lexical.openFile();
            stack.push(new Node(0));

            Token currentTK;
            Node headStack;
            String action;
            Scanner ik = new Scanner(System.in);
            if (this.lexical.hasMoreTokens())
                currentTK = lexical.nextToken();
            else return;

            while (!stack.empty()) {
                ik.nextLine();

                System.out.println("CurrentTK = " + currentTK.getCategory());
                System.out.println("Stack = " + stack.peek().state);
                headStack = stack.peek();

                action = actionTable[headStack.state][currentTK.getCategory().getCategoryValue() - 1];
                System.out.println("Action = " + action);
                System.out.println("Token value/column: " + (currentTK.getCategory().getCategoryValue() - 1));
                if (action.startsWith("s")) { // EMPILHA
                    System.out.println("Empilha");
                    int state = Integer.valueOf(action.replace("s", ""));
                    stack.push(new Node(state, currentTK));
                    System.out.println("Stack " + stack.peek().state);
                    if (lexical.hasMoreTokens())
                        currentTK = lexical.nextToken();
                    else {
                        currentTK = new Token(TokenCategory.EOF);
                    }
                } else if (action.startsWith("r")) { // REDUÇÃO
                    System.out.println("Reduz");
                    int codeProd = Integer.valueOf(action.replace("r", ""));
                    Production production = productions.get(codeProd);

                    int nRemoves = production.len;
                    System.out.println("Removes: " + nRemoves);
                    for (int i = 0; i < nRemoves; i++) stack.pop();

                    codeProd = map.get(production.head);
                    int newState = trasitions[stack.peek().state][codeProd];
                    stack.push(new Node(newState, production.head));

                } else if (action.equals("acc")) {
//                    @// TODO: 19/10/2018 TIRAR ESSA PORRA!
                    System.out.println("Passou porra!");
                    break;
                } else {
                    System.out.println("Erro.");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
