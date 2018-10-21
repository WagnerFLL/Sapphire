package lexical;

public enum TokenCategory {

    FUNC(1),
    MAIN(2),
    BEGIN_PARAM(3),
    END_PARAM(4),
    BEGIN_SCP(5),
    END_SCP(6),
    ID(7),
    SEPARATOR(8),
    OP_ATRIB(9),
    BEGIN_ARR(10),
    END_ARR(11),
    INT(12),
    FLOAT(13),
    BOOL(14),
    CHAR(15),
    STR(16),
    VOID(17),
    OP_NEG(18),
    OP_UNNEG(19),
    OP_ADD(20),
    OP_MULTI(21),
    OP_EXP(22),
    OP_RELAT(23),
    OP_REL_EQ(24),
    OP_CONCAT(25),
    OP_AND(26),
    OP_OR(27),
    CONST_BOOL(28),
    CONST_STR(29),
    CONST_CHAR(30),
    CONST_INT(31),
    CONST_FLT(32),
    INS_INPUT(33),
    INS_SHOW(34),
    INS_ELSE(35),
    INS_IF(36),
    INS_ELIF(37),
    INS_WHILE(38),
    INS_FOR(39),
    INS_RETURN(40),
    EOF(41),
    COMMENT(42);

    private int value;

    TokenCategory(int value) {
        this.value = value;
    }

    public int getCategoryValue() {
        return value;
    }

}
