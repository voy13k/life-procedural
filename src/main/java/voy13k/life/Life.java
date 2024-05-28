package voy13k.life;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Life {

    public static Collection<Position> tick(Collection<Position> oldGeneration) {
        return oldGeneration.stream()
                .flatMap(Life::neigbourhood)
                .filter(potential -> switch (neighbourCountOf(potential, oldGeneration)) {
                    case (2) -> oldGeneration.contains(potential);
                    case (3) -> true;
                    default -> false;
                })
                .collect(Collectors.toSet());
    }

    private static int neighbourCountOf(Position cell, Collection<Position> generation) {
        return (int) neigbourhood(cell)
                .filter(c -> !c.equals(cell))
                .filter(generation::contains)
                .count();
    }

    private static Stream<? extends Position> neigbourhood(Position cell) {
        return Stream.of(
                new Position(cell.row() - 1, cell.col() - 1),
                new Position(cell.row() - 1, cell.col()),
                new Position(cell.row() - 1, cell.col() + 1),
                new Position(cell.row(), cell.col() - 1),
                cell,
                new Position(cell.row(), cell.col() + 1),
                new Position(cell.row() + 1, cell.col() - 1),
                new Position(cell.row() + 1, cell.col()),
                new Position(cell.row() + 1, cell.col() + 1)
        );
    }
}
