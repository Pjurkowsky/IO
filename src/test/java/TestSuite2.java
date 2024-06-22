
import org.junit.platform.suite.api.*;

@Suite
@SelectClasses({TestSuite.class})
@IncludeTags("Entity")
@ExcludeTags("Omin")
public class TestSuite2 {
}
