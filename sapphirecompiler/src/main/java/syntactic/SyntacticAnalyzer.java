package syntactic;

import Grammar.Grammar;
import Grammar.Production;
import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SyntacticAnalyzer {

    private LexicalAnalyzer lexical;
    private final ThreadLocal<Stack<Node>> stack = new ThreadLocal<>();
    private String[][] actionTable;
    private List<Production> productions;
    private Map<String, Integer> map;
    private int[][] transitions;

    public SyntacticAnalyzer(String path) {
        this.lexical = new LexicalAnalyzer(path);
        this.stack.set(new Stack<>());
        this.actionTable = SLRTableAction.action;
        this.productions = Grammar.productions;
        this.map = SLRTableTransition.map;
        this.transitions = SLRTableTransition.transitions;
    }

    public static String padRight(TokenCategory s) {
        return String.format("%1$-" + 10 + "s", s);
    }
    public void run() {

        try {

            lexical.openFile();
            stack.get().push(new Node(0));

            Token currentTK;
            Node headStack;
            String action;
            Scanner ik = new Scanner(System.in);

            if (this.lexical.hasMoreTokens())
                currentTK = lexical.nextToken();
            else return;

            while (!stack.get().empty()) {
                ik.nextLine();

                headStack = stack.get().peek();

                action = actionTable[headStack.state][currentTK.getCategory().getCategoryValue() - 1];

                if (action.startsWith("s")) {
                    System.out.format("Empilha              [%04d, %04d] (%04d, %10s) {%s}\n",currentTK.getLine(), currentTK.getColumn(), currentTK.getCategory().getCategoryValue(), currentTK.getCategory(), currentTK.getLexeme());
                    int state = Integer.valueOf(action.replace("s", ""));
                    stack.get().push(new Node(state, currentTK));

                    if (lexical.hasMoreTokens()) currentTK = lexical.nextToken();
                    else currentTK = new Token(TokenCategory.EOF);

                } else if (action.startsWith("r")) {
                    int codeProd = Integer.valueOf(action.replace("r", ""));
                    Production production = productions.get(codeProd);
                    System.out.println("Reduz          "+production.text);
                    int nRemoves = production.len;
                    for (int i = 0; i < nRemoves; i++) stack.get().pop();

                    codeProd = map.get(production.head);
                    int newState = transitions[stack.get().peek().state][codeProd];
                    stack.get().push(new Node(newState, production.head));

                } else if (action.equals("acc")) {
                    System.out.println("\n -------- ACC ---------");
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
