import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main3 {
    public static void main(String[] args) {
        var main = new Main3();
        Scanner in = new Scanner(System.in);
        var strings = new ArrayList<String>();
        var outputs = new ArrayList<String>();
        while (in.hasNext()) {
            var s = in.nextLine();
            strings.add(s);
            if (s.contains("</root>")) {
                break;
            }
        }
        int rootId = 0;
        for (int i = 0; i < strings.size(); i++) {
        }
        List<Integer> parentId = new ArrayList<>();
        parentId.add(0);
        for (int i = rootId + 1; i < strings.size(); i++) {
            if (strings.get(i).contains("<") && !strings.get(i).contains("<root>")) {
                //System.out.println(strings.get(i)+"     "+main.percentMatching(strings.get(i), "id="));
                var req = strings.get(i);
                if(req.matches(" *</.*")){
                    if(parentId.size()>0){
                        parentId.remove(parentId.size()-1);
                    }
                }
                var startId = req.indexOf("id");
                if (startId == -1) {
                    continue;//нет id
                }
                req=req.substring(startId+2);
                if (req.charAt(0) == '=') {
                    req = req.substring(1);
                }
                if (req.charAt(0) == '\"') {
                    req = req.substring(1);
                }
                var split = req.split("[^0-9]+");
                //int indexOfIdEnd=main.findMin(req.indexOf("\""),req.indexOf(" "),req.indexOf("val"),req.indexOf("vlue"), req.indexOf("alue"), req.indexOf(">"));
                int indexOfIdEnd=split.length>0?Integer.parseInt(split[0]):-1;

                if(indexOfIdEnd==-1){
                    continue;
                }
                int id=indexOfIdEnd;//req.substring(0,indexOfIdEnd);
                //req=req.substring(indexOfIdEnd);
                String value=null;
                int indexOfValueStart=main.findMaxIndex(req,"ue=\"","value=\"","value =\"","value =","alue=","valu","vlue","alue");
                if(indexOfValueStart!=-1){
                    req=req.substring(indexOfValueStart);

                    int indexOfValueStop=main.findMin(req.indexOf("\""),req.indexOf("/"),req.indexOf(">"),req.indexOf("\""));
                    if(indexOfValueStop!=-1){
                        value=req.substring(0,indexOfValueStop);
                    }
                }
                //System.out.println("parent="+parentId.get(parentId.size()-1)+ " id="+id+ " value="+ value);
                System.out.println(parentId.get(parentId.size()-1)+ " "+id+(value==null?"":" "+value));

                if(!req.contains("/>")&&!req.contains("</")){//есть вложенность
                    parentId.add(Integer.valueOf(id));
                }
            }
        }

        //System.out.println(strings);
    }

    public Integer findMin(int ... longs){
        Stream.Builder<Integer> builder = Stream.<Integer>builder();
        for (int s : longs) {
            if(s!=-1){
            builder.add(s);}
        }
        Stream<Integer> allParams = builder.build();

        return allParams
                .reduce((a,b) -> a < b ? a : b)
                .orElse(-1);

    }
    public Integer findMaxIndex(String s1,String ... strs){
        Stream.Builder<Integer> builder = Stream.<Integer>builder();
        for (String s : strs) {
            if(s!=null&& s1.contains(s)){
                builder.add(s1.indexOf(s)+s.length());}
        }
        Stream<Integer> allParams = builder.build();

        return allParams
                .reduce((a,b) -> a > b ? a : b)
                .orElse(-1);

    }

    public List<Long> parseInput(String input) {
        var array = input.split(" ");
        return Stream.of(array).map(str -> str.trim()).map(Long::valueOf).collect(Collectors.toList());
    }

    /*алгоритм нечеткого поиска (алгоритм Левенштейна) для подсчета процента сопоставления двух строк*/
    public int percentMatching(CharSequence left, CharSequence right) {
        int len0 = left.length() + 1;
        int len1 = right.length() + 1;
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        for (int i = 0; i < len0; i++) {
            cost[i] = i;
        }
        for (int j = 1; j < len1; j++) {
            newcost[0] = j;

            for (int i = 1; i < len0; i++) {
                int match = (left.charAt(i - 1) == right.charAt(j - 1)) ? 0 : 1;

                int costReplace = cost[i - 1] + match;
                int costInsert = cost[i] + 1;
                int costDelete = newcost[i - 1] + 1;

                newcost[i] = Math.min(Math.min(costInsert, costDelete), costReplace);
            }

            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }
        return 100 - cost[len0 - 1] * 100 / Math.max(left.length(), right.length());
    }

    class Input {
        String N;
        String L;
    }
}



