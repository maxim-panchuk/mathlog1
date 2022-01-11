import lexem.Lexeme;
import lexem.LexemeAnalyzer;
import lexem.LexemeBuffer;
import model.Counter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String expression1 = "C&D|E|F&G";
        LexemeAnalyzer lexemeAnalyzer = new LexemeAnalyzer();
        List<Lexeme> lexemeList = lexemeAnalyzer.lexAnalyze(expression1);
        System.out.println(lexemeList.toString());
        //Separator separator = new Separator();
        Counter counter = new Counter();
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemeList);
        System.out.println(counter.expr(lexemeBuffer));

    }

}
