package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ai.AITest;
import tests.ai.SensorsTest;
import tests.world.BotTests;
import tests.world.CellTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({AITest.class, SensorsTest.class, CellTests.class, BotTests.class})
public class AllTests {
}
