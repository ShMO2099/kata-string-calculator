import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.katas.StringCalculator;

public class StringCalculatorShould {

    @ParameterizedTest
    @CsvSource({
        "'',0",
        "4,4",
        "'4,\n',4",
        "'4,5',9",
        "'4,5,1000',1009",
        "'4,5,1001',9",
        "'4,5\n6',15",
        "'//;\n4;5\n6',15",
        "'//|\n4|5\n6',15",
        "'//[*]\n4*5\n6',15",
        "'//[****][****]\n4****5****2\n6',17"
    })
    public void return_sum_when_input_is_arrayOfNumbers(String input,String expectedSum) throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(Integer.valueOf(expectedSum), stringCalculator.add(input));
    }

    @Test
    public void return_0_when_input_is_negative() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = assertThrows(Exception.class, () -> stringCalculator.add("1,-2,-3"));
        assertEquals("Negatives not allowed: -2 -3", exception.getMessage());
    }
}
