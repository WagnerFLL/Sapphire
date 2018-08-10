package lexical;

import CustomExceptions.InvalidCharException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    private String path;
    private List<String> linesList = new ArrayList<>();
    private String currentLine;
    private int columnIndex, lineIndex;

    public LexicalAnalyzer(String path) {
        this.path = path;
        this.columnIndex = 0;
        this.lineIndex = 0;
    }

    public void readFile() throws IOException {

        BufferedReader br;

        br = new BufferedReader(new FileReader(this.path));

        String brLine = br.readLine();

        while (brLine != null) {
            linesList.add(brLine);
            brLine = br.readLine();
        }
        br.close();

    }

    public boolean hasMoreTokens() {

        // ver se chegou ao fim do arquivo
        if (lineIndex < linesList.size()) {

            currentLine = linesList.get(lineIndex);

            // verifica se o resto da linha é somente espaço em branco
            // se for vai para a próxima
            while (currentLine.substring(columnIndex).matches("\\s*")){
                lineIndex++;
                columnIndex = 0;
                // se houver uma próxima linha
                if (linesList.size() > lineIndex)
                    currentLine = linesList.get(lineIndex);
                else
                    return false;
            }
            // se há mais caracteres na linha
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

    private String getNumber(char c) {

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
            lineIndex++;
            columnIndex = 0;
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

        // Ignora sequ?ncia de espa?os vazios
        char character = ignoreBlankSpace();
        Token token = new Token(columnIndex+1, lineIndex+1);

        if (isNumber(character))
            lexeme = new StringBuilder(getNumber(character));

        else if (!Table.special.contains(character))
            lexeme.append(getName(character));

        else {

            if (character == '@'){
                lineIndex++;
                columnIndex = 0;
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
