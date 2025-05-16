class Field {

    private final int xCoordinate;
    private final int yCoordinate;
    private final boolean isDark;
    private boolean isVisited;

    Field(int xCoordinate, int yCoordinate, boolean isDark, boolean isVisited) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isDark = isDark;
        this.isVisited = isVisited;
    }

    int getXCoordinate() {
        return xCoordinate;
    }

    int getYCoordinate() {
        return yCoordinate;
    }

    boolean isDark() {
        return isDark;
    }

    boolean isVisited() {
        return isVisited;
    }

    void setVisited(boolean visited) {
        isVisited = visited;
    }

}
