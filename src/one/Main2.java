import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {
    class Input{
        String N;
        String L;
    }
        public List<Long> parseInput(String input){
                var array = input.split(" ");
            return Stream.of(array).map(str->str.trim()).map(Long::valueOf).collect(Collectors.toList());
        }

    public static void main(String[] args) {
        var main = new Main2();
        Scanner in = new Scanner(System.in);
        List<String> allInput= new ArrayList<>();
        List<Long> longs = Collections.emptyList();
        var strings = new ArrayList<String>();
        while(in.hasNext()) {
            strings.add(in.nextLine());
            if(strings.size()==2){
                break;
            }
        }
        longs= main.parseInput(strings.get(1));
        longs.sort(Long::compareTo);
        long difSum=0;
        for (int i = 0; i < longs.size(); i+=2) {
            difSum+=Math.abs(longs.get(i)-longs.get(i+1));
        }
        System.out.println(difSum);
    }
}



