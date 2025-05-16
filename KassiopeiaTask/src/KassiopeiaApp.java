import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class KassiopeiaApp {

    private static Turtle turtle;
    private static boolean solutionFound = false;
    private static final ArrayList<String> solution = new ArrayList<>();

    // Direction arrays for movement: up, right, down, left
    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Board board = initGame();
        System.out.println("Amount of fields: " + board.getAmountOfFields());
        System.out.println("Amount of dark fields: " + board.getAmountOfDarkFields());
        System.out.println("Amount of white fields: " + board.getAmountOfWhiteField());
        System.out.println("Kassiopeia at: " + turtle.getXCoordinate() + "," + turtle.getYCoordinate());

        int whiteFields = board.getAmountOfWhiteField();
        
        if (turtle == null) {
            System.out.println("No starting position found!");
            return;
        }
        
        Field[][] fields = board.getFields();
        fields[turtle.getYCoordinate()][turtle.getXCoordinate()].setVisited(true);
        moveTurtle(turtle.getYCoordinate(), turtle.getXCoordinate(), 1, fields, whiteFields);
        
        if (!solutionFound) {
            System.out.println("No solution found!");
        }
    }

    private static Board initGame() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("data/kassiopeiaBoard1.txt"));
        lines = lines.stream()
                .map(line -> line.replace(",", ""))
                .toList();

        int height = lines.size();
        int width = lines.getFirst().length();
        Field[][] fields = new Field[height][width];
        
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                
                Field newField;
                switch (c) {
                    case 'x' -> newField = new Field(x, y, true, false);
                    case '0' -> newField = new Field(x, y, false, false);
                    case '1' -> {
                        newField = new Field(x, y, false, false);
                        turtle = new Turtle(x, y);
                    }
                    default -> {
                        System.out.println("Invalid character at x:" + x + " y:" + y + " c:" + c);
                        newField = new Field(x, y, true, false); // Treat unknown as obstacle
                    }
                }
                fields[y][x] = newField;
            }
        }
        return new Board(fields);
    }

    private static void moveTurtle(int y, int x, int amountVisited, Field[][] fields, int totalWhiteFields) {
        if (amountVisited == totalWhiteFields) {
            System.out.println("Is possible with: " + solution);
            solutionFound = true;
            return;
        }

        int height = fields.length;
        int width = fields[0].length;
        
        // Try all four directions using direction arrays
        for (int dir = 0; dir < 4; dir++) {
            int newY = y + DY[dir];
            int newX = x + DX[dir];
            
            // Check boundaries and if the field is valid to move to
            if (newY >= 0 && newY < height && newX >= 0 && newX < width && 
                !fields[newY][newX].isDark() && !fields[newY][newX].isVisited()) {
                
                // Mark as visited, move, then backtrack
                fields[newY][newX].setVisited(true);
                solution.add(decideDirection(dir));
                moveTurtle(newY, newX, amountVisited + 1, fields, totalWhiteFields);
                fields[newY][newX].setVisited(false);
                solution.removeLast();
            }
        }
    }

    private static String decideDirection(int dirNumber) {
        String dir = "";
        switch (dirNumber) {
            case 0 -> dir = "N";
            case 1 -> dir = "E";
            case 2 -> dir = "S";
            case 3 -> dir = "W";
        }
        return dir;
    }
}
