import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LifeTests {

    static String matrixToArgs(String matrix) {
        var lines = matrix.lines()
                .map(l -> l.replaceAll("[\\[\\]]", ""))
                .toList();
        var cells = new ArrayList<String>();
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) != ' ') {
                    cells.add(String.format("[%d, %d]", row, col));
                }
            }
        }
        return cells.toString();
    }

    static String argsToMatrix(String args, int rowCount, int colCount) {
        String intList = args.replaceAll("[\\[\\] ]", "");
        String[] coords = intList.split(",");
        var positions = new HashSet<Life.Position>(coords.length / 2);
        for (int i = 0; i < coords.length - 1; i++, i++) {
            positions.add(new Life.Position(Integer.parseInt(coords[i]), Integer.parseInt(coords[i + 1])));
        }
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < rowCount; row++) {
            builder.append('[');
            for (int col = 0; col < colCount; col++) {
                Life.Position position = new Life.Position(row, col);
                builder.append(positions.contains(position) ? "X" : " ");
            }
            builder.append("]\n");
        }
        return builder.toString();
    }

    @Test
    void argsToMatrix_EmptyList() {
        assertThat(argsToMatrix("[]", 3, 3), is("""
                [   ]
                [   ]
                [   ]
                """));
    }

    @Test
    void argsToMatrix_many() {
        assertThat(argsToMatrix("[[1,0], [0,2], [1,4]]", 2, 5), is("""
                [  X  ]
                [X   X]
                """));
    }

    @Test
    void matrixToArgs_BlankToEmptyList() {
        assertThat(matrixToArgs(""), is("[]"));
    }

    @Test
    void matrixToArgs_SingleToSingle() {
        assertThat(matrixToArgs("""
                [X]
                """), is("[[0, 0]]"));
    }

    @Test
    void matrixToArgs_secondRow() {
        assertThat(matrixToArgs("""
                [   ]
                [X  ]
                """), is("[[1, 0]]"));
    }

    @Test
    void matrixToArgs_secondCol() {
        assertThat(matrixToArgs("""
                [ X]
                """), is("[[0, 1]]"));
    }

    @Test
    void matrixToArgs_manyItems() {
        assertThat(matrixToArgs("""
                [  X ]
                [ X X]
                [X   ]
                """), is("[[0, 2], [1, 1], [1, 3], [2, 0]]"));
    }

    @Test
    void matrixToArgs_anyChar() {
        assertThat(matrixToArgs("""
                [  A ]
                [ B C]
                """), is("[[0, 2], [1, 1], [1, 3]]"));
    }

}
