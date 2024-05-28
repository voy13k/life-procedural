package voy13k.life;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static voy13k.life.TestUtils.fromMatrix;

class LifeTickTests {

    private static String[][] one() {
        return new String[][]{{"""
                [X  ]
                [   ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [ X ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [   ]
                [  X]
                """, """
                [   ]
                [   ]
                [   ]
                """},};
    }

    private static String[][] two() {
        return new String[][]{{"""
                [X  ]
                [ X ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [ X ]
                [ X ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [  X]
                [ X ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [ XX]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [ X ]
                [  X]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [ X ]
                [ X ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [ X ]
                [X  ]
                """, """
                [   ]
                [   ]
                [   ]
                """}, {"""
                [   ]
                [XX ]
                [   ]
                """, """
                [   ]
                [   ]
                [   ]
                """},};
    }

    private static String[][] three() {
        return new String[][]{{"""
                [X   ]
                [ XX ]
                [    ]
                """, """
                [ X  ]
                [ X  ]
                [    ]
                """}, {"""
                [ X  ]
                [ XX ]
                [    ]
                """, """
                [ XX ]
                [ XX ]
                [    ]
                """}, {"""
                [  X ]
                [ XX ]
                [    ]
                """, """
                [ XX ]
                [ XX ]
                [    ]
                """}, {"""
                [   X]
                [ XX ]
                [    ]
                """, """
                [  X ]
                [  X ]
                [    ]
                """}, {"""
                [    ]
                [ XXX]
                [    ]
                """, """
                [  X ]
                [  X ]
                [  X ]
                """}, {"""
                [    ]
                [ XX ]
                [   X]
                """, """
                [    ]
                [  X ]
                [  X ]
                """}, {"""
                [    ]
                [ XX ]
                [  X ]
                """, """
                [    ]
                [ XX ]
                [ XX ]
                """}, {"""
                [    ]
                [ XX ]
                [ X  ]
                """, """
                [    ]
                [ XX ]
                [ XX ]
                """}, {"""
                [    ]
                [ XX ]
                [X   ]
                """, """
                [    ]
                [ X  ]
                [ X  ]
                """}, {"""
                [    ]
                [XXX ]
                [    ]
                """, """
                [ X  ]
                [ X  ]
                [ X  ]
                """}, {"""
                [XX  ]
                [    ]
                [  X ]
                """, """
                [    ]
                [ X  ]
                [    ]
                """}, {"""
                [XX  ]
                [    ]
                [ X  ]
                """, """
                [    ]
                [XX  ]
                [    ]
                """},};
    }

    @Test
    void tick_blankToBlank() {
        assertThat(Life.tick(Set.of()), hasSize(0));
    }

    @ParameterizedTest
    @MethodSource({
            "one",
            "two",
            "three",
    })
    void tick_neighbours(String matrixBefore, String expectedMatrixAfter) {
        var beforeCells = fromMatrix(matrixBefore);
        var expectedCells = fromMatrix(expectedMatrixAfter);
        assertThat(Life.tick(beforeCells), containsInAnyOrder(expectedCells.toArray()));
    }

}
