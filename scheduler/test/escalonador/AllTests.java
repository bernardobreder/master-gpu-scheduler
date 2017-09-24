package escalonador;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import escalonador.algorithm.knapsack.IODynamicKnapsackTest;
import escalonador.algorithm.knapsack.KnapsackResourceTest;
import escalonador.algorithm.knapsack.MemoryDynamicKnapsackTest;
import escalonador.algorithm.scheduler.OrderSchedulerTest;

@RunWith(Suite.class)
@SuiteClasses({ KnapsackResourceTest.class, MemoryDynamicKnapsackTest.class, IODynamicKnapsackTest.class, OrderSchedulerTest.class })
public class AllTests {

}