package lexical;

import CustomExceptions.InvalidCharException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer {

    private String path;
    private String currentLine;
    private int columnIndex, lineIndex;
    private BufferedReader br;

    public LexicalAnalyzer(String path) {
        this.path = path;
        this.columnIndex = 0;
        this.lineIndex = 0;
    }

    public void openFile() throws IOException {

        br = new BufferedReader(new FileReader(this.path));

        currentLine = br.readLine();
        System.out.println(currentLine);
    }

    private void nextLine() throws IOException {
        lineIndex++;
        currentLine = br.readLine();
        columnIndex = 0;
        System.out.println("\n"+currentLine);
    }

    public boolean hasMoreTokens() throws IOException {

        if (currentLine != null) {

            while (currentLine.substring(columnIndex).matches("\\s*")){

                nextLine();
                if (currentLine == null)
                    return false;
            }


            return columnIndex < currentLine.length();
        }

        return false;
    }

    private char ignoreBlankSpace() {
        char c = currentLine.charAt(columnIndex);
        while (c == ' ') c = getCharacter();
        return c;
    }

    private boolean isNumber(char c){

        return Character.toString(c).matches("\\d");

    }

    private String getNumber(char c) throws IOException {

        StringBuilder value = new StringBuilder(String.valueOf(c));
        c = getCharacter();

        while (isNumber(c)) {
            value.append(c);
            c = getCharacter();
        }

        if (c == '.') {
            value.append(c);
            c = getCharacter();
            while (isNumber(c)) {
                value.append(c);
                c = getCharacter();
            }
        }

        if (c == '\n'){
            nextLine();
        }
        return value.toString();
    }

    private String getName(char c) {

        StringBuilder value = new StringBuilder();

        while (!Table.special.contains(c)) {
            value.append(c);
            c = getCharacter();

            if (c == '\n') break;
        }

        return value.toString();
    }

    public Token nextToken()  throws Exception {

        StringBuilder lexeme = new StringBuilder();

        char character = ignoreBlankSpace();
        Token token = new Token(columnIndex+1, lineIndex+1);

        if (isNumber(character))
            lexeme = new StringBuilder(getNumber(character));

        else if (!Table.special.contains(character))
            lexeme.append(getName(character));

        else {

            if (character == '@'){
                nextLine();
                if (hasMoreTokens())
                    return nextToken();

            } else if (character == '\''){
                lexeme.append(character);
                character = getCharacter();
                lexeme.append(character);
                character = getCharacter();

                if (character == '\'') {
                    lexeme.append(character);
                    columnIndex++;
                }
                else
                    throw new InvalidCharException("Char inválido. " +
                            "Apóstrofo esperado, mas não encontrado!");

            } else if (character == '"'){
                lexeme.append(character);
                character = getCharacter();

                while (character != '"'){
                    lexeme.append(character);
                    character = getCharacter();
                }
                lexeme.append(character);
                columnIndex++;

            } else if (character == '<' || character == '>' || character == '=' || character == '!'){
                lexeme.append(character);
                character = getCharacter();
                if (character == '=') {
                    lexeme.append(character);
                    columnIndex++;
                }
            } else {
                lexeme.append(character);
                columnIndex++;
            }
        }

        token.setLexeme(lexeme.toString());
        token.setCategory(new Lexeme(lexeme.toString())
                .getCategory(currentLine,token.getColumn()));

        return token;

    }

    private Character getCharacter() {
        if (++columnIndex < currentLine.length())
            return currentLine.charAt(columnIndex);
        else
            return '\n';

    }

}
