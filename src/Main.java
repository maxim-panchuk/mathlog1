import lexem.Lexeme;
import lexem.LexemeAnalyzer;
import lexem.LexemeBuffer;
import model.Counter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String expression1 = "P1'->!QQ->!R10&S|!T&U&V";
        LexemeAnalyzer lexemeAnalyzer = new LexemeAnalyzer();
        List<Lexeme> lexemeList1 = lexemeAnalyzer.lexAnalyze(expression1);
        Counter counter = new Counter();
        LexemeBuffer lexemeBuffer1 = new LexemeBuffer(lexemeList1);
        System.out.println(counter.expr(lexemeBuffer1));
    }
}
