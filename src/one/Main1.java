
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main1 {
    class Input{
        String N;
        String L;
    }
        public List<Input> parseInput(List<String> input){
            return input.stream().map(str -> {
                var array = str.split(" ");
                var person = new Input();
                person.N=array[0];
                person.L=array[1];
                return person;
            }).collect(Collectors.toList());
        }

    public static void main(String[] args) {
        var main = new Main1();
        Scanner in = new Scanner(System.in);
        List<String> allInput= new ArrayList<>();

        while(in.hasNext()) {
            allInput.add(in.nextLine()); // read int from 'STDIN'
            break;
        }
        var inputs = main.parseInput(allInput).get(0);
        Long L=Long.valueOf(inputs.L);
        Long N=Long.valueOf(inputs.N);
        System.out.println((L/N)+((L%N)>0?1:0));
    }
}



