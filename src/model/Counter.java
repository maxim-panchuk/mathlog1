package model;

import lexem.Lexeme;
import lexem.LexemeBuffer;
import lexem.LexemeType;

public class Counter {

    public String expr (LexemeBuffer lexemeBuffer) {
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.EOF) {
            return "";
        } else {
            lexemeBuffer.back();
            return impl(lexemeBuffer);
        }
    }

    public String impl (LexemeBuffer lexemeBuffer) {
        String operand = dis(lexemeBuffer);
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.OP_IMPL) {
            return "(->," + operand + "," + impl(lexemeBuffer) + ")";
        } else {
            lexemeBuffer.back();
            return operand;
        }
    }

    public String dis (LexemeBuffer lexemeBuffer) {
        String operand = con(lexemeBuffer);
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.OP_DIS) {
            return "(|," + operand + "," + dis(lexemeBuffer) + ")";
        } else {
            lexemeBuffer.back();
            return operand;
        }
    }

    public String con (LexemeBuffer lexemeBuffer) {
        String operand = neg(lexemeBuffer);
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.OP_CON) {
            return "(&," + operand + "," + con(lexemeBuffer) + ")";
        } else {
            lexemeBuffer.back();
            return operand;
        }
    }

    public String neg (LexemeBuffer lexemeBuffer) {
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.OP_NEG) {
            return "(!" + neg(lexemeBuffer) + ")";
        } else {
            lexemeBuffer.back();
            return var(lexemeBuffer);
        }
    }

    public String var (LexemeBuffer lexemeBuffer) {
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.lexemeType == LexemeType.VAR) {
            return lexeme.value;
        } else if (lexeme.lexemeType == LexemeType.LEFT_BRACKET) {
            String operand = expr(lexemeBuffer);
            lexeme = lexemeBuffer.next();
            if (lexeme.lexemeType != LexemeType.RIGHT_BRACKET) {
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemeBuffer.getPos());
            }
            return operand;
        } else {
            throw new RuntimeException("Unexpected token: " + lexeme.value
                    + " at position: " + lexemeBuffer.getPos());
        }
    }
}
