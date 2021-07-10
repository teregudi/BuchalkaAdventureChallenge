import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer."));
        locations.put(1, new Location(1, "You are standing at the end of a road."));
        locations.put(2, new Location(2, "You are at the top of a hill."));
        locations.put(3, new Location(3, "You are inside a building."));
        locations.put(4, new Location(4, "You are in a valley beside a stream."));
        locations.put(5, new Location(5, "You are in the forest."));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        Map<String, String> directions = new HashMap<>();
        directions.put("NORTH", "N");
        directions.put("WEST", "W");
        directions.put("SOUTH", "S");
        directions.put("EAST", "E");
        directions.put("QUIT", "Q");

        int loc = 1;
        while (true){
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0) break;

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String[] input = scanner.nextLine().toUpperCase().split(" ");
            String whereTo = null;
            for (String s : input){
                if (directions.containsValue(s)) {
                    whereTo = s;
                    break;
                }
                else if (directions.containsKey(s)) {
                    whereTo = directions.get(s);
                    break;
                }
            }

            if (exits.containsKey(whereTo)){
                loc = exits.get(whereTo);
            } else {
                System.out.println("You cannot go in that direction.");
            }
        }
    }
}
