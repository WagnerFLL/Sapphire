package lexical;

public enum TokenCategory {

    FUNC(0),
    ID(1),
    BEGIN_PARAM(2),
    END_PARAM(3),
    BEGIN_SCP(4),
    END_SCP(5),
    MAIN(6),
    SEPARATOR(7),
    OP_ATRIB(8),
    BEGIN_ARR(9),
    END_ARR(10),
    INT(11),
    STR(12),
    CHAR(13),
    FLOAT(14),
    BOOL(15),
    VOID(16),
    OP_CONCAT(17),
    OP_OR(18),
    OP_AND(19),
    OP_REL_EQ(20),
    OP_RELAT(21),
    OP_ADD(22),
    OP_MULTI(23),
    OP_EXP(24),
    OP_UNNEG(25),
    OP_NEG(26),
    CONST_STR(27),
    CONST_BOOL(28),
    CONST_CHAR(29),
    CONST_INT(30),
    CONST_FLT(31),
    INS_INPUT(32),
    INS_SHOW(33),
    INS_IF(34),
    INS_ELIF(35),
    INS_ELSE(36),
    INS_WHILE(37),
    INS_FOR(38),
    INS_RETURN(39),
    EOF(40),
    COMMENT(41);

    private int value;

    TokenCategory(int value) {
        this.value = value;
    }

    public int getCategoryValue() {
        return value;
    }

}
