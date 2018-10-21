package syntactic;

import lexical.Token;

class Node {

    public int state;
    public String nonTerminal;
    public Token token;

    Node(int state) {
        this.state = state;
    }

    Node(int state, Token token) {
        this.state = state;
        this.token = token;
    }

    Node(int state, String nonTerminal) {
        this.state = state;
        this.nonTerminal = nonTerminal;
    }

}
