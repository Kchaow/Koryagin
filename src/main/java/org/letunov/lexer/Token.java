package org.letunov.lexer;

public class Token {
    protected final LexemeType lexemeType;
    public Token(LexemeType lexemeType) {
        this.lexemeType = lexemeType;
    }
    public Token(int tag) {
        this.lexemeType = new LexemeType(tag);
    }
    public Token(char tag) {
        this.lexemeType = new LexemeType(tag);
    }
    public LexemeType getLexemeType() {
        return lexemeType;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (object instanceof Token otherToken) {
            return lexemeType.equals(otherToken.lexemeType);
        }
        else
            return false;
    }
    @Override
    public int hashCode() {
        return lexemeType.hashCode();
    }
    @Override
    public String toString() {
        if (lexemeType.equals(LexemeType.NUM)) {
            return "целое число";
        }
        else if (lexemeType.equals(LexemeType.WORD)) {
            Word word = (Word) this;
            return word.getLexeme();
        }
        else {
            return String.valueOf((char) lexemeType.getTag());
        }
    }
}
