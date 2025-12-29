import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import kiosk.Application;

public class ApplicationTest {

    @Test
    void 범위밖입력_예외발생() {

        // given
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // 출력 캡쳐 (선택)
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }
}