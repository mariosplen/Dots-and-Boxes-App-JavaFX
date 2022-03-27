package com.github.mariosplen.dotsandboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record Box(Dot bottomLeft, Dot topLeft,
                  Dot topRight, Dot bottomRight) {

    public boolean canBeCreatedUsing(List<List<Dot>> lines) {
        List<List<Dot>> possibleBoxLines = lines();
        possibleBoxLines.addAll(reverseLines());

        return numberOfBoxLinesIn(lines, possibleBoxLines) == 4;
    }

    public List<List<Dot>> lines() {
        return new ArrayList<>() {
            {
                add(Arrays.asList(bottomLeft, topLeft));
                add(Arrays.asList(topLeft, topRight));
                add(Arrays.asList(topRight, bottomRight));
                add(Arrays.asList(bottomRight, bottomLeft));
            }
        };
    }

    private List<List<Dot>> reverseLines() {
        return new ArrayList<>() {
            {
                add(Arrays.asList(topLeft, bottomLeft));
                add(Arrays.asList(topRight, topLeft));
                add(Arrays.asList(bottomRight, topRight));
                add(Arrays.asList(bottomLeft, bottomRight));

            }
        };
    }

    private long numberOfBoxLinesIn(List<List<Dot>> lines, List<List<Dot>> boxLines) {
        return boxLines.stream()
                .filter(lines::contains)
                .count();
    }

    public boolean isWithin(Dot lowerBound, Dot upperBound) {
        return Stream.of(bottomLeft, topLeft, topRight, bottomRight)
                .allMatch(dot -> dot.isWithin(lowerBound, upperBound));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box box = (Box) o;

        return bottomLeft.equals(box.bottomLeft)
                && bottomRight.equals(box.bottomRight)
                && topLeft.equals(box.topLeft)
                && topRight.equals(box.topRight);
    }

    @Override
    public int hashCode() {
        int result = bottomLeft.hashCode();
        result = 31 * result + topLeft.hashCode();
        result = 31 * result + topRight.hashCode();
        result = 31 * result + bottomRight.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Box{" +
                "bottomLeft=" + bottomLeft +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomRight=" + bottomRight +
                '}';
    }
}
