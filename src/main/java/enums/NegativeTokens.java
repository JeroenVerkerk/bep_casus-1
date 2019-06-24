package enums;

public enum NegativeTokens {
    WhiteSpace(' '),
    ExclamationMark('!'),
    DoubleQuote('"'),
    NumberSign('#'),
    Dollar('$'),
    Percent('%'),
    Ampersand('&'),
    DoubleBackslash('\\'),
    LeftParenthesis('('),
    RightParenthesis(')');


    public final char value;

    NegativeTokens(char negativeTokens) {
        this.value = negativeTokens;
    }
}

