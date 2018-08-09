package lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    private String path;
    private List<String> linesList = new ArrayList<>();
    private String line;
    private int currentColumn, currentLine, sizeCurrentLine;

    public LexicalAnalyzer(String path) {
        this.path = path;
        this.currentColumn = 0;
        this.currentLine = 0;
    }

    public void readFile() {

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(this.path));

            String brLine = br.readLine();

            while (brLine != null) {
                linesList.add(brLine);
                brLine = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isCharToken(String value) {

        if (value.matches("'(.?)'")) return true;

        else if (value.startsWith("'")) System.out.println("Erro: char loucão");

        return false;
    }

    private boolean isStr(String value) {

        if (value.startsWith("\"") && value.endsWith("\""))
            return true;
        else if (value.startsWith("\""))
            System.out.println("Erro: str loucão");

        return false;

    }

    private boolean isInterger(String value) {

        if (value.matches("(\\d)+")) return true;

        return false;

    }

    private boolean isFloat(String value) {

        if (value.matches("(\\d)+\\.(\\d)+")) return true;

        return false;

    }

    private boolean isID(String value) {

        if (value.matches("[_a-zA-Z][_a-zA-Z0-9]*")) {
            if (value.length() < 31)
                return true;
            else
                System.out.println("Erro: diminui o nome dessa desgraça aí mlk!");

        }

        return false;
    }

    private boolean isUnaryNegative(String value, int begin) {

        if (value.equals("-")){

            if (begin > 0){
                int previous = begin-1;
                while (line.charAt(previous) == ' ' && previous > 0)previous--;
                if (Character.toString(line.charAt(previous)).matches("[^a-zA-Z0-9|)]"))
                    return true;
            }
        }
        return false;
    }

    private TokenCategory discoveryCategory(String value, int begin) {

        if (isUnaryNegative(value, begin))
            return TokenCategory.OP_UNNEG;
        else if (Table.map.containsKey(value))
            return Table.map.get(value);
        else if (isCharToken(value))
            return TokenCategory.CONST_CHAR;
        else if (isStr(value))
            return TokenCategory.CONST_STR;
        else if (isInterger(value))
            return TokenCategory.CONST_INT;
        else if (isFloat(value))
            return TokenCategory.CONST_FLOAT;
        else if (isID(value))
            return TokenCategory.ID;
        return null;

    }

    private char nextCharacter() {

        if (this.currentColumn >= line.length()) {
            this.currentColumn = 0;
            return '\n';
        }

        return line.charAt( this.currentColumn++ );

    }

    public boolean hasMoreTokens() {

            // ver se chegou ao fim do arquivo
            if (currentLine < linesList.size()) {

                line = linesList.get(currentLine);

                // verifica se o resto da linha é somente espaço em branco
                // se for vai para a próxima

                while (line.substring(currentColumn).matches("\\s*")){
                    currentLine++;
                    currentColumn = 0;
                    // se houver uma próxima linha
                    if (linesList.size() > currentLine)
                        line = linesList.get(currentLine);
                    else
                        return false;
                }
                // se há mais caracteres na linha
                if (currentColumn < line.length()) {
                    return true;
                }
            }


        return false;

    }

    private char ignoreBlankSpace() {
        char c = line.charAt(currentColumn);
        while (c == ' ') c = nextChar();
        return c;
    }

    private boolean isNumber(char c){
        if (Character.toString(c).matches("\\d"))
            return true;
        return false;
    }

    private String getNumber(char c) {
        String value = String.valueOf(c);
        c = nextChar();
        while (isNumber(c)) {
            value += c;
            c = nextChar();
        }

        if (c == '.') {
            value += c;
            c = nextChar();
            while (isNumber(c)) {
                value += c;
                c = nextChar();
            }
        }
        if (c == '\n'){
            currentLine++;
            currentColumn = 0;
        }
        return value;
    }

    private String getName(char c) {

        String value = "";

        while (!Table.special.contains(c)) {
            value += c;
            c = nextChar();

            if (c == '\n') break;
        }

        return value;
    }

    public Token nextToken() {

        String lexeme = "";

        // Ignora sequ?ncia de espa?os vazios
        char character = ignoreBlankSpace();
        Token token = new Token(currentColumn,currentLine);

        if (isNumber(character))
            lexeme = getNumber(character);

        else if (!Table.special.contains(character))
            lexeme += getName(character);

        else if (lexeme == ""){

            if (character == '@'){
                currentLine++;
                currentColumn = 0;
                if (hasMoreTokens())
                    return nextToken();

            } else if (character == '\''){
                lexeme += character;
                character = nextChar();
                lexeme += character;
                character = nextChar();

                if (character == '\'') {
                    lexeme += character;
                    currentColumn++;
                }
                else
                    System.out.println("Erro");

            } else if (character == '"'){
                lexeme += character;
                character = nextChar();

                while (character != '"'){
                    lexeme += character;
                    character = nextChar();
                }
                lexeme += character;
                currentColumn++;

            } else if (character == '<' || character == '>' || character == '=' || character == '!'){
                lexeme += character;
                character = nextChar();
                if (character == '=') {
                    lexeme += character;
                    currentColumn++;
                }
            } else {
                lexeme += character;
                currentColumn++;
            }
        }

        token.setLexeme(lexeme);
        token.setCategory(discoveryCategory(lexeme,token.getColumn()));

        return token;

    }


    private Character nextChar() {

        if (++currentColumn < line.length()) {
            return line.charAt(currentColumn);
        } else {
            return '\n';
        }

    }

}
