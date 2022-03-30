package com.github.mariosplen.dotsandboxes.logic;

public record Dot(double x, double y) {

    private boolean formsHorizontalLine(Dot other) {
        return this.y == other.y;
    }

    private boolean formsVerticalLine(Dot other) {
        return this.x == other.x;
    }

    public boolean canBeJoinedHorizontallyOrVerticallyWith(Dot other) {
        if (other == this)
            return false;

        return formsVerticalLine(other) || formsHorizontalLine(other);
    }

    public Dot translate(double dx, double dy) {
        return new Dot(x + dx, y + dy);
    }

    public boolean isWithin(Dot lowerBound, Dot upperBound) {
        final boolean includeBounds = true;
        return isWithin(lowerBound, upperBound, includeBounds);
    }


    public boolean isWithin(Dot lowerBound, Dot upperBound, boolean includeBounds) {
        if (includeBounds)
            return x >= lowerBound.x && x <= upperBound.x
                    && y >= lowerBound.y && y <= upperBound.y;

        return x > lowerBound.x && x < upperBound.x
                && y > lowerBound.y && y < upperBound.y;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Dot dot = (Dot) o;
//
//        if (x != dot.x) return false;
//        return y == dot.y;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        temp = Double.doubleToLongBits(x);
//        result = (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(y);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("{%.0f, %.0f}", x, y);
//    }


}
