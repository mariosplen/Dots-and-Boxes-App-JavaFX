package com.github.mariosplen.dotsandboxes.logic;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final int rows;
    private final int cols;


    private final Dot gridLowerBound;
    private final Dot gridUpperBound;
    private final List<List<Dot>> markedLines = new ArrayList<>();
    private final Map<String, Set<Box>> scores;
    private final List<String> players;
    private final long possibleBoxesCount;
    private int nextPlayer;

    public Game(int rows, int cols, List<String> players) {

        if (rows < 1)
            throw new IllegalArgumentException("Require at least 1 row");

        if (cols < 1)
            throw new IllegalArgumentException("Require at least 1 column");

        if (null == players || players.isEmpty() || players.size() < 2)
            throw new IllegalArgumentException("Please specify players");

        this.rows = rows;
        this.cols = cols;
        this.players = players;
        this.gridLowerBound = new Dot(0, 0);
        this.gridUpperBound = new Dot(rows, cols);
        this.nextPlayer = 0;
        this.possibleBoxesCount = possibleBoxes(rows, cols).size();
        this.scores = initializeScoresToZero(players);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private Map<String, Set<Box>> initializeScoresToZero(List<String> players) {
        return players.stream()
                .collect(Collectors.toMap(Function.identity(), name -> new HashSet<>()));
    }

    private Set<Box> possibleBoxes(int rows, int cols) {
        return Stream.iterate(0, i -> i + 1)
                .limit(rows + 1)
                .flatMap(x ->
                        Stream.iterate(0, j -> j + 1)
                                .limit(cols + 1)
                                .map(y -> boxesFor(new Dot(x, y))))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public void join(Dot d1, Dot d2, String playerName) {
        final String nextPlayerName = nextPlayerName();

        if (!nextPlayerName.equals(playerName))
            throw new IllegalArgumentException(String.format("It's %s's turn!", nextPlayerName));

        if (notWithinGrid(d1, d2))
            return;

        final List<Dot> line = Arrays.asList(d1, d2);
        final List<Dot> reverseLine = Arrays.asList(d2, d1);

        if (markedLines.contains(line) || markedLines.contains(reverseLine))
            return;

        if (!d1.canBeJoinedHorizontallyOrVerticallyWith(d2))
            return;

        int currentPlayer = nextPlayer;
        markedLines.add(line);
        nextPlayer = getNextPlayer(currentPlayer);

        makeBoxesFor(d1, d2)
                .forEach(completedBox -> {
                    add(completedBox, playerName);
                    currentPlayerPlaysNext(currentPlayer);
                });
    }

    private Set<Box> completedBoxes() {
        return scores.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private void currentPlayerPlaysNext(int player) {
        nextPlayer = player;
    }

    private int getNextPlayer(int idx) {
        final int nextIdx = idx + 1;
        return nextIdx < players.size() ? nextIdx : 0;
    }

    public String nextPlayerName() {
        return players.get(nextPlayer);
    }

    private boolean notWithinGrid(Dot d1, Dot d2) {
        return !d1.isWithin(gridLowerBound, gridUpperBound)
                || !d2.isWithin(gridLowerBound, gridUpperBound);
    }

    private Stream<Box> makeBoxesFor(Dot d1, Dot d2) {
        final Set<Box> boxes = new HashSet<>();
        boxes.addAll(boxesFor(d1));
        boxes.addAll(boxesFor(d2));
        return boxes.stream()
                .filter(box -> box.canBeCreatedUsing(markedLines))
                .filter(box -> !completedBoxes().contains(box));
    }


    private Set<Box> boxesFor(Dot center) {
//        {x-1,y-1},{x-1,y},{x-1,y+1}
//         LeftTop,   Top,   RightTop
//          {x,y-1}, {x,y} ,{x,y+1}
//           Left,  Center,  Right
//        {x+1,y-1},{x+1,y},{x+1,y+1}
//        LeftBottom, Bottom, RightBottom

        final Dot right = center.translate(0, 1);
        final Dot left = center.translate(0, -1);
        final Dot top = center.translate(-1, 0);
        final Dot bottom = center.translate(1, 0);
        final Dot leftTop = center.translate(-1, -1);
        final Dot leftBottom = center.translate(1, -1);
        final Dot rightTop = center.translate(-1, 1);
        final Dot rightBottom = center.translate(1, 1);

        return Stream.of(new Box(left, leftTop, top, center), new Box(center, top, rightTop, right),
                        new Box(leftBottom, left, center, bottom), new Box(bottom, center, right, rightBottom))
                .filter(box -> box.isWithin(gridLowerBound, gridUpperBound))
                .collect(Collectors.toSet());
    }

    private void add(Box box, String player) {
        if (scores.containsKey(player))
            scores.get(player).add(box);
    }

    public Map<String, Integer> scores() {
        return scores.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), player -> scores.get(player).size()));
    }

    public boolean isOver() {
        return possibleBoxesCount == completedBoxes().size();
    }

    public String winner() {
        if (!isOver())
            return "Game is not complete yet!";

        if (hasCompletedInADraw())
            return "Game has Drawn!";

        return nextPlayerName();
    }

    private boolean hasCompletedInADraw() {
        return new HashSet<>(scores().values()).size() == 1;
    }


//    public Set<int[]> getBoxes(){
//        Box[] boxes = (Box[]) completedBoxes().toArray();
//        Dot[] boxesCords = new Dot[0];
//        int boxCounter = 0;
//        for (int i =0; i<boxes.length;i++){
//            boxesCords[i]=boxes[i].getTopLeft();
//
//        }
//
//
//    }


}
