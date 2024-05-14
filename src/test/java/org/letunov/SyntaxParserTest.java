package org.letunov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.letunov.exception.SyntaxParsingException;
import org.letunov.lexer.Lexer;
import org.letunov.lexer.Word;
import org.letunov.parser.SyntaxParserImpl;
import org.letunov.parser.SyntaxParser;

import static org.junit.jupiter.api.Assertions.*;

public class SyntaxParserTest {
    private Lexer lexer;
    @BeforeEach
    public void setupLexer() {
        lexer = new Lexer();
        lexer.reserve(Word.BEGIN);
        lexer.reserve(Word.END);
        lexer.reserve(Word.FIRST);
        lexer.reserve(Word.SECOND);
        lexer.reserve(Word.ABS);
        lexer.reserve(Word.SIN);
        lexer.reserve(Word.COS);
    }

    @Test
    public void throwedMessageTest_1() {
        String text = "Begin";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось First или Second, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_2() {
        String text = "Begin Third";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось First или Second, но найден Third", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_3() {
        String text = "Begin First";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_4() {
        String text = "Begin First ,";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найдено ,", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_5() {
        String text = "Begin First 23,";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_6() {
        String text = "Begin First 23, 32";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_7() {
        String text = "Begin First 23, 32 , ,";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найдено ,", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_8() {
        String text = "Begin Second";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_9() {
        String text = "Begin Second gh";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_10() {
        String text = "Begin Second gh df ds";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число или ;, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_11() {
        String text = "Begin Second gh df ds ;";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_12() {
        String text = "Begin Second gh df ds First";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_13() {
        String text = "Begin Second gh df ds First 23, 21";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_14() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf, fds";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найдено ,", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_15() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_16() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd da";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найдено da", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_17() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось :, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_18() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35 dsa";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось :, но найдено dsa", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_19() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: ";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_20() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: 23";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найдено целое число", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_21() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось =, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_22() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa ds";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось =, но найдено ds", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_23() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa =";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось выражение, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_24() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = 32";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось End, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_25() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = 32 23";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось ключевое слово End, но найдено целое число", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_26() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = 32 +";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидался операнд, но найден конец", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_27() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = 32 + * \\";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Неожиданный токен: *", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_28() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = cos End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось (, но найдено End", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_29() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = cos(ads +) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Неожиданный токен: )", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_30() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = cos(ads)^ End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Неожиданный токен: End", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_31() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = cos(ads)^23 ad End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось ключевое слово End, но найдено ad", syntaxParsingException.getMessage());
    }

    @Test
    public void throwedMessageTest_32() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = abs + cos(ads)^23 + (23 + 53 / asd) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось (, но найдено +", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingTest() {
        String text = "Begin First 23 asd123; hj 56 : rty = 4 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingSeveralNumberAtLinkSuccessTest() {
        String text = "Begin First 23, 134, 89, 0 asd123;d 56 : rty = 2 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingSeveralNumberAtLinkFailedTest() {
        String text = "Begin First 23, 134, 89, asd123; 56 : rty = End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось целое число, но найдено asd123", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingSeveralVariableAtLinkSuccessTest() {
        String text = "Begin Second a1 a2 a3 a4 asd123;hj 56 : rty = 4 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingSeveralVariableAtLinkFailedTest() {
        String text = "Begin Second a1 ; 56 : rty = End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найдено ;", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingSeveralVariableAtLastSuccessTest() {
        String text = "Begin Second a1 a2 a3 a4 asd123;fg23;df32 56 : rty = 4 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingSeveralVariableAtLastFailedTest() {
        String text = "Begin Second a1 a2 a3 a4 asd123; 56 : rty = End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалась переменная, но найдено целое число", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingExpressions_1() {
        String text = "Begin Second wf23 gh4 32 : g = 1 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_2() {
        String text = "Begin Second wf23 gh4 32 : g = 1 + 2 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_3() {
        String text = "Begin Second wf23 gh4 32 : g = (1 + 2) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_4() {
        String text = "Begin Second wf23 gh4 32 : g = 1 + End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Неожиданный токен: End", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingExpressions_5() {
        String text = "Begin Second wf23 gh4 32 : g = 1 + 2 + 3 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_6() {
        String text = "Begin Second wf23 gh4 32 : g = - 1 + 2 + 3 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_7() {
        String text = "Begin Second wf23 gh4 32 : g = - (1 + 2 + 3) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_8() {
        String text = "Begin Second wf23 gh4 32 : g = 1 + 2 * 3 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_9() {
        String text = "Begin Second wf23 gh4 32 : g = 1 * 2 * 3 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_10() {
        String text = "Begin Second wf23 gh4 32 : g = (1 + 2) * 3 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_11() {
        String text = "Begin Second wf23 gh4 32 : g = 1 + (2 * 3) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_12() {
        String text = "Begin Second wf23 gh4 32 : g = (1 + 2) + (3 + 4) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_13() {
        String text = "Begin Second wf23 gh4 32 : g = (1 + 2) * (3 + 4) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_14() {
        String text = "Begin Second wf23 gh4 32 : g = 1 / 2 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_15() {
        String text = "Begin Second wf23 gh4 32 : g = 3 +  6 ^ 2 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_16() {
        String text = "Begin Second wf23 gh4 32 : g = (3 +  6) ^ 2 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_17() {
        String text = "Begin Second wf23 gh4 32 : g = cos(5) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_18() {
        String text = "Begin Second wf23 gh4 32 : g = cos(5) sin(6) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось ключевое слово End, но найдено sin", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingExpressions_19() {
        String text = "Begin Second wf23 gh4 32 : g = cos 5 End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Ожидалось (, но найдено целое число", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingExpressions_20() {
        String text = "Begin Second wf23 gh4 32 : g = cos( 5 + 6 * 2 ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_21() {
        String text = "Begin Second wf23 gh4 32 : g = cos( (5 + 6) * 2 ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_22() {
        String text = "Begin Second wf23 gh4 32 : g = abs( cos( (5 + 6) * 2 ) ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_23() {
        String text = "Begin Second wf23 gh4 32 : g = abs( cos( (5 + 6) * 2 ) + 10 ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }
    @Test
    public void parsingExpressions_24() {
        String text = "Begin Second wf23 gh4 32 : g = abs( cos( (5 + 6) * 2 ) + + ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        SyntaxParsingException syntaxParsingException =
                assertThrows(SyntaxParsingException.class, () -> syntaxParser.parse(text));
        assertEquals("Неожиданный токен: +", syntaxParsingException.getMessage());
    }

    @Test
    public void parsingExpressions_25() {
        String text = "Begin Second wf23 gh4 32 : g = 26 * 4 / abs( cos( (5 + 6) * 2 ) + 10 ) End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }

    @Test
    public void parsingExpressions_26() {
        String text = "Begin Second gh df ds First 23, 21 Second dsaf ankl ; sdf ; fdd 35: dsa = 32 + ad End";
        SyntaxParser syntaxParser = new SyntaxParserImpl(lexer);
        assertDoesNotThrow(() -> syntaxParser.parse(text));
    }
}
