package enums;

public enum NegativeTokens {
    WHITESPACE(' '),
    EXCLAMATIONMARK('!'),
    DOUBLEQUOTE('"'),
    NUMBERSIGN('#'),
    DOLLAR('$'),
    PERCENT('%'),
    AMPERSAND('&'),
    DOUBLEBACKSLASH('\\'),
    LEFTPARENTHESIS('('),
    RIGHTPARENTHESIS(')');


    public final char value;

    NegativeTokens(char negativeTokens) {
        this.value = negativeTokens;
    }
}

