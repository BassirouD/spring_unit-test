package diallo.org.sprint_unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SprintUnitTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void assertThanOnePlusOneIsTwo() {
        int somme = 1 + 1;
        assertThat(somme).isNotNull();
        assertThat(somme).isEqualTo(2);
    }

    @Test
    void assertThanOnePlusTwoIsThree() {
        int somme = 1 + 2;
        assertThat(somme).isEqualTo(2);
    }

}
