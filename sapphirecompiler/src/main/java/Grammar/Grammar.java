package Grammar;

import java.util.ArrayList;
import java.util.List;

public class Grammar {

    public static List<Production> productions = new ArrayList<Production>();

    static {
        productions.add(new Production("A = Sinicial"));
        productions.add(new Production("Sinicial = List_Func Decl_Main "));
        productions.add(new Production("Sinicial = Decl_Main"));
        productions.add(new Production("Decl_Main = FUNC Var_type MAIN BEGIN_PARAM END_PARAM BEGIN_SCP Cmds END_SCP "));
        productions.add(new Production("List_Func = List_Func Decl_Func "));
        productions.add(new Production("List_Func = Decl_Func "));
        productions.add(new Production("Decl_Func = FUNC Var_type ID BEGIN_PARAM Parameters END_PARAM BEGIN_SCP Cmds END_SCP "));
        productions.add(new Production("Decl_Func = FUNC Var_type ID BEGIN_PARAM END_PARAM BEGIN_SCP Cmds END_SCP "));
        productions.add(new Production("Parameters = Parameters SEPARATOR Decl_Var "));
        productions.add(new Production("Parameters = Decl_Var"));
        productions.add(new Production("Decl_Var_r = Decl_Var_List "));
        productions.add(new Production("Decl_Var_r = Decl_Var"));
        productions.add(new Production("Decl_Var_r = Decl_Var_List OP_ATRIB E"));
        productions.add(new Production("Decl_Var_r = Decl_Var OP_ATRIB E "));
        productions.add(new Production("Decl_Var_List = Decl_Var_r SEPARATOR Decl_Var"));
        productions.add(new Production("Decl_Var = Var_type_r "));
        productions.add(new Production("Var_type_r = Var_type"));
        productions.add(new Production("Var_type_r = Var_arr_type"));
        productions.add(new Production("Var_arr_type = Var_type BEGIN_ARR E END_ARR"));
        productions.add(new Production("Var_type = INT"));
        productions.add(new Production("Var_type = FLOAT"));
        productions.add(new Production("Var_type = BOOL"));
        productions.add(new Production("Var_type = CHAR"));
        productions.add(new Production("Var_type = STR"));
        productions.add(new Production("Var_type = VOID"));
        productions.add(new Production("E = OP_NEG Er"));
        productions.add(new Production("E = OP_UNNEG Er"));
        productions.add(new Production("E = E Opr Er"));
        productions.add(new Production("Er = BEGIN_PARAM E END_PARAM"));
        productions.add(new Production("Er = Operand"));
        productions.add(new Production("Operand = Const "));
        productions.add(new Production("Operand = ID "));
        productions.add(new Production("Operand = ID BEGIN_ARR E END_ARR"));
        productions.add(new Production("Operand = Call_Func"));
        productions.add(new Production("Opr = OP_ADD"));
        productions.add(new Production("Opr = OP_MULTI"));
        productions.add(new Production("Opr = OP_EXP"));
        productions.add(new Production("Opr = OP_RELAT"));
        productions.add(new Production("Opr = OP_REL_EQ"));
        productions.add(new Production("Opr = OP_CONCAT"));
        productions.add(new Production("Opr = OP_AND"));
        productions.add(new Production("Opr = OP_OR"));
        productions.add(new Production("Const = Numeric_Const "));
        productions.add(new Production("Const = CONST_BOOL"));
        productions.add(new Production("Const = CONST_STR"));
        productions.add(new Production("Const = CONST_CHAR"));
        productions.add(new Production("Numeric_Const = CONST_INT "));
        productions.add(new Production("Numeric_Const = CONST_FLT "));
        productions.add(new Production("Attrib = ID OP_ATRIB E"));
        productions.add(new Production("Attrib = Id_Arr OP_ATRIB E"));
        productions.add(new Production("Id_Arr = ID BEGIN_ARR E END_ARR"));
        productions.add(new Production("Call_Func = ID BEGIN_PARAM Parameters END_PARAM"));
        productions.add(new Production("Call_Func = ID BEGIN_PARAM END_PARAM"));
        productions.add(new Production("Input = INS_INPUT BEGIN_PARAM Input_Param END_PARAM"));
        productions.add(new Production("Input_Param = Input_Param SEPARATOR Param_r"));
        productions.add(new Production("Input_Param = Param_r"));
        productions.add(new Production("Show = INS_SHOW BEGIN_PARAM Show_Param END_PARAM"));
        productions.add(new Production("Show_Param = Show_Param OP_CONCAT Param_r"));
        productions.add(new Production("Show_Param = Param_r"));
        productions.add(new Production("Param_r = CONST_STR"));
        productions.add(new Production("Param_r = ID"));
        productions.add(new Production("Cond = If_r E BEGIN_SCP Cmds END_SCP"));
        productions.add(new Production("Cond = INS_ELSE BEGIN_SCP Cmds END_SCP"));
        productions.add(new Production("If_r = INS_IF"));
        productions.add(new Production("If_r = INS_ELIF"));
        productions.add(new Production("Loop = INS_WHILE E BEGIN_SCP Cmds END_SCP"));
        productions.add(new Production("Loop = For Cmds END_SCP"));
        productions.add(new Production("For = INS_FOR Attrib SEPARATOR E SEPARATOR E BEGIN_SCP "));
        productions.add(new Production("For = INS_FOR SEPARATOR E SEPARATOR E BEGIN_SCP "));
        productions.add(new Production("For = INS_FOR SEPARATOR SEPARATOR E BEGIN_SCP "));
        productions.add(new Production("For = INS_FOR SEPARATOR SEPARATOR SEPARATOR BEGIN_SCP  "));
        productions.add(new Production("For = INS_FOR Attrib SEPARATOR SEPARATOR E BEGIN_SCP "));
        productions.add(new Production("For = INS_FOR Attrib SEPARATOR E SEPARATOR BEGIN_SCP "));
        productions.add(new Production("For = INS_FOR Attrib SEPARATOR SEPARATOR BEGIN_SCP "));
        productions.add(new Production("Cmds = Cmd Cmds "));
        productions.add(new Production("Cmds = Cmd"));
        productions.add(new Production("Cmd = Decl_Var_r "));
        productions.add(new Production("Cmd = Call_Func "));
        productions.add(new Production("Cmd = Input "));
        productions.add(new Production("Cmd = Show "));
        productions.add(new Production("Cmd = Cond "));
        productions.add(new Production("Cmd = Loop"));
        productions.add(new Production("Cmd = Rtrn"));
        productions.add(new Production("Rtrn = INS_RETURN E"));
    }

}
