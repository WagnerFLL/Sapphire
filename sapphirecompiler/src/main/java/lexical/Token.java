package lexical;

public class Token {

    private String lexeme;
    private TokenCategory category;
    private int line;
    private int column;

    Token(int currentColumn, int currentLine) {
        this.line = currentLine;
        this.column = currentColumn;
    }

    public Token(TokenCategory tokenCategory) {
        this.category = tokenCategory;
    }

    public TokenCategory getCategory() {
        return category;
    }

    void setCategory(TokenCategory category) {
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

    void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}
