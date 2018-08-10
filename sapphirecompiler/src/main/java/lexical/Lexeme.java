package lexical;

import CustomExceptions.InvalidCharException;
import CustomExceptions.InvalidFloatFormatException;
import CustomExceptions.InvalidLexemeException;
import CustomExceptions.InvalidStringException;

class Lexeme {

    private String value;

    Lexeme(String value) {
        this.value = value;
    }

    TokenCategory getCategory(String currentLine, int begin) throws Exception {

        if (isUnaryNegative(currentLine, begin))
            return TokenCategory.OP_UNNEG;
        else if (Table.map.containsKey(value))
            return Table.map.get(value);
        else if (isValidChar(value))
            return TokenCategory.CONST_CHAR;
        else if (isValidStr(value))
            return TokenCategory.CONST_STR;
        else if (isValidInterger(value))
            return TokenCategory.CONST_INT;
        else if (isValidFloat(value))
            return TokenCategory.CONST_FLOAT;
        else if (isValidID(value))
            return TokenCategory.ID;

        throw new InvalidLexemeException("O lexema não pode ser reconhecido!");

    }

    private boolean isValidChar(String value) throws InvalidCharException {

        if (value.matches("'(.?)'"))
            return true;
        else if (value.startsWith("'"))
            throw new InvalidCharException("Char inválido");

        return false;
    }

    private boolean isValidStr(String value) throws InvalidStringException {

        if (value.startsWith("\"") && value.endsWith("\""))
            return true;
        else if (value.startsWith("\""))
            throw new InvalidStringException("Fecha áspas não encontrado!");

        return false;

    }

    private boolean isValidInterger(String value) {

        return value.matches("(\\d)+");

    }

    private boolean isValidFloat(String value) throws InvalidFloatFormatException {

        if (value.matches("(\\d)+\\.(\\d)+"))
            return true;
        else if (value.matches("(\\d)+\\."))
            throw new InvalidFloatFormatException("Float em formato inválido.");

        return false;

    }

    private boolean isValidID(String value) {

        if (value.matches("[_a-zA-Z][_a-zA-Z0-9]*")) {
            if (value.length() < 31)
                return true;
            else
                System.out.println("Erro: diminui o nome dessa desgraça aí mlk!");

        }

        return false;
    }

    private boolean isUnaryNegative(String currentLine, int begin) {

        if (value.equals("-")){

            if (begin > 0){
                int previous = begin-1;
                while (currentLine.charAt(previous) == ' ' && previous > 0)
                    previous--;

                return Character.toString(currentLine.charAt(previous)).matches("[^a-zA-Z0-9|)]");
            }
        }
        return false;
    }

}
