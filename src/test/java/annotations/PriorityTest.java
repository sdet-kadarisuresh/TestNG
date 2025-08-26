package annotations;

import org.testng.annotations.Test;

public class PriorityTest {

    @Test(priority = -90)
    public void B() {
        System.out.println("B Login Test");
    }

    @Test(priority = -2)
    public void A() {
        System.out.println("A Search Test");
    }

    @Test(priority = -9)
    public void C() {
        System.out.println("C Checkout Test");
    }
}
