package lexical;

public class Token {

    private String lexeme;
    private TokenCategory category;
    private int line;
    private int column;

    public Token(int currentColumn, int currentLine) {
        this.line = currentLine;
        this.column = currentColumn;
    }


    public TokenCategory getCategory() {
        return category;
    }

    public void setCategory(TokenCategory category) {
        this.category = category;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}
