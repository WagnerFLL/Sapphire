package syntactic;

import CustomExceptions.SyntacticError;
import Grammar.Grammar;
import Grammar.Production;
import lexical.LexicalAnalyzer;
import lexical.Token;
import lexical.TokenCategory;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SyntacticAnalyzer {

    private final ThreadLocal<Stack<Node>> stack = new ThreadLocal<>();
    private LexicalAnalyzer lexical;
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

    public void run() {

        try {

            lexical.openFile();
            stack.get().push(new Node(0));

            Token currentTK;
            Node headStack;
            String action;

            if (this.lexical.hasMoreTokens())
                currentTK = lexical.nextToken();
            else return;

            while (!stack.get().empty()) {
                headStack = stack.get().peek();
                action = actionTable[headStack.state][currentTK.getCategory().getCategoryValue()];

                if (action.startsWith("s")) {
                    System.out.format("              [%04d, %04d] (%04d,%11s) {%s}\n", currentTK.getLine() + 1, currentTK.getColumn(), currentTK.getCategory().getCategoryValue(), currentTK.getCategory(), currentTK.getLexeme());

                    int state = Integer.valueOf(action.replace("s", ""));
                    stack.get().push(new Node(state, currentTK));

                    if (lexical.hasMoreTokens()) currentTK = lexical.nextToken();
                    else currentTK = new Token(TokenCategory.EOF);

                } else if (action.startsWith("r")) {

                    int codeProd = Integer.valueOf(action.replace("r", ""));
                    Production production = productions.get(codeProd);
                    System.out.println("          " + production.text);

                    int nRemoves = production.len;
                    for (int i = 0; i < nRemoves; i++) stack.get().pop();

                    codeProd = map.get(production.head);
                    int newState = transitions[stack.get().peek().state][codeProd];
                    stack.get().push(new Node(newState, production.head));

                } else if (action.equals("acc")) {
                    System.out.println("\n -------- ACC ---------");
                    break;

                } else {
                    throw new SyntacticError("Ação inválida.");
                }
            }


        } catch (Exception e) {
            e.getMessage();
        }
    }

}
