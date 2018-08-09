package lexical;

public enum TokenCategory {

    MAIN(1),
    ID(2),
    INT(3),
    FLOAT(4),
    BOOL(5),
    CHAR(6),
    STR(7),
    BEGIN_SCP(8),
    END_SCP(9),
    BEGIN_PARM(10),
    END_PARM(11),
    BEGIN_ARR(12),
    END_ARR(13),
    COMMENT(14),
    FUNC(15),
    SEPARATOR(16),
    CONST_INT(17),
    CONST_FLOAT(18),
    CONST_BOOL(19),
    CONST_STR(20),
    CONST_CHAR(21),
    INS_INPUT(22),
    INS_SHOW(23),
    INS_IF(24),
    INS_ELIF(25),
    INS_ELSE(26),
    INS_FOR(27),
    INS_WHILE(28),
    INS_RETURN(29),
    OP_ATRIB(30),
    OP_AND(31),
    OP_OR(32),
    OP_NEG(33),
    OP_ADD(34),
    OP_MULTI(35),
    OP_EXP(36),
    OP_RELAT(37),
    OP_REL_EQ(38),
    OP_CONCAT(39),
    OP_UNNEG(40),
    VOID(41);

    private int value;

    TokenCategory(int value) {
        this.value = value;
    }

    public int getCategoryValue() {
        return value;
    }

}
