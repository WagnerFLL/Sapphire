package lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Table {

    static Map<String, TokenCategory> map = new HashMap<>();
    static List<Character> special = new ArrayList<>();


    static {
        // Operadores
        map.put("+", TokenCategory.OP_ADD);
        map.put("-", TokenCategory.OP_ADD);
        map.put("*", TokenCategory.OP_MULTI);
        map.put("/", TokenCategory.OP_MULTI);
        map.put("^", TokenCategory.OP_EXP);

        map.put("<", TokenCategory.OP_RELAT);
        map.put(">", TokenCategory.OP_RELAT);
        map.put("<=", TokenCategory.OP_RELAT);
        map.put(">=", TokenCategory.OP_RELAT);
        map.put("==", TokenCategory.OP_REL_EQ);
        map.put("!=", TokenCategory.OP_RELAT);

        map.put("and",TokenCategory.OP_AND);
        map.put("or",TokenCategory.OP_OR);
        map.put("not",TokenCategory.OP_NEG);

        map.put("=", TokenCategory.OP_ATRIB);
        map.put("&", TokenCategory.OP_CONCAT);

        // Delimitadores
        map.put("(", TokenCategory.BEGIN_PARM);
        map.put(")", TokenCategory.END_PARM);
        map.put("[", TokenCategory.BEGIN_ARR);
        map.put("]", TokenCategory.END_ARR);
        map.put("@", TokenCategory.COMMENT);
        map.put(",",TokenCategory.SEPARATOR);
        map.put(":",TokenCategory.BEGIN_SCP);
        map.put("end",TokenCategory.END_SCP);

        // Palavras reservadas
        map.put("func",TokenCategory.FUNC);
        map.put("void",TokenCategory.VOID);
        map.put("main",TokenCategory.MAIN);
        map.put("int",TokenCategory.INT);
        map.put("float",TokenCategory.FLOAT);
        map.put("bool",TokenCategory.BOOL);
        map.put("char",TokenCategory.CHAR);
        map.put("str",TokenCategory.STR);
        map.put("true",TokenCategory.CONST_BOOL);
        map.put("flase",TokenCategory.CONST_BOOL);
        map.put("input",TokenCategory.INS_INPUT);
        map.put("show",TokenCategory.INS_SHOW);
        map.put("if",TokenCategory.INS_IF);
        map.put("elif",TokenCategory.INS_ELIF);
        map.put("else",TokenCategory.INS_ELSE);
        map.put("for",TokenCategory.INS_FOR);
        map.put("while",TokenCategory.INS_WHILE);
        map.put("return",TokenCategory.INS_RETURN);

        // Caractres especiais
        special.add('/');
        special.add('>');
        special.add('<');
        special.add('=');
        special.add(':');
        special.add('-');
        special.add('+');
        special.add(',');
        special.add(';');
        special.add('.');
        special.add('\'');
        special.add('"');
        special.add('\\');
        special.add(' ');
        special.add('{');
        special.add('}');
        special.add('[');
        special.add(']');
        special.add(')');
        special.add('(');
        special.add('*');
        special.add('~');
        special.add('^');
        special.add('´');
        special.add('`');
        special.add('%');
        special.add('#');
        special.add('@');
        special.add('¨');
        special.add('&');
        special.add('*');
        special.add('!');






    }



}
