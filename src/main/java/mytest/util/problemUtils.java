import java.util.*;
import java.util.stream.Collectors;

public class problemUtils {

    boolean almostIncreasingSequence(int[] sequence) {
        int j = 0;
        for(int i=0;i<sequence.length-1;i++){
            if(sequence[i]>sequence[i+1])
                j++;
        }
        Set<Integer> set = Arrays.stream(sequence).boxed().collect(Collectors.toSet());
        return (j > 1) ||
                (sequence.length - set.size() > 1) ||
                (sequence.length - set.size() == 1  && j > 0) ? false : true;
    }

    int matrixElementsSum(int[][] matrix) {
        int sum = 0;
        int minus = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                sum += matrix[i][j];
                if(matrix[i][j] == 0){
                    int k = i+1;
                    while(k < matrix.length && matrix[k][j] != 0){
                        minus += matrix[k][j];
                        k++;
                    }
                }
            }
        }
        return sum - minus;
    }

    String[] allLongestStrings(String[] inputArray) {
        int size = 1;
        List<String> retorno = new ArrayList<>();

        for(int i=0;i < inputArray.length;i++){
            int this_size = inputArray[i].length();
            if (this_size > size){
                size = this_size;
                retorno = new ArrayList<>();
            }
            if(this_size == size) {
                retorno.add(inputArray[i]);
            }
        }
        String[] itemsArray = new String[retorno.size()];
        return retorno.toArray(itemsArray);
    }

    int commonCharacterCount(String s1, String s2) {
        List<Character> c1 = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> c2 = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Integer> e = new ArrayList<>();
        int count = 0;
        for(char a : c1){
            for(int i = 0; i < c2.size();i++){
                if(a == c2.get(i)){
                    count++;
                    e.add(i);
                    break;
                }
            }
            for(int j : e){
                c2.remove(j);
            }
            e = new ArrayList<>();
        }
        return count;
    }

    boolean isLucky(int n) {
        String sNum = String.valueOf(n);
        List<Character> num = sNum.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        int s1 =0;
        int s2 =0;
        for(int i=0; i < num.size();i++){
            if(i < num.size()/2)
                s1 += (int) num.get(i);
            else
                s2 += (int) num.get(i);
        }

        return s1 == s2 ? true : false;
    }

    int[] sortByHeight(int[] a) {
        List<Integer> tree = new ArrayList<>();
        List<Integer> row = Arrays.stream(a).boxed().collect(Collectors.toList());
        row.sort(Comparator.naturalOrder());
        List<Integer> retorno = new ArrayList<>();

        for(int i=0;i < a.length;i++){
            if(a[i] == -1){
                tree.add(i);
            }
        }
        int t=0;
        int k = tree.size();
        for(int j=0;j < row.size();j++){
            if(!tree.isEmpty() && t < tree.size() && tree.get(t) == j){
                retorno.add(-1);
                t++;
                k--;
            }else{
                retorno.add(row.get( j + k));
            }
        }

        Integer[] itemsArray = new Integer[retorno.size()];
        return Arrays.stream(retorno.toArray(itemsArray)).mapToInt(Integer::intValue).toArray();
    }

    static String reverseInParentheses(String inputString) {
        List<Character> list = inputString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        while(list.contains('(') || list.contains(')')){
            StringBuilder word = new StringBuilder("");
            String reverseWord;
            for(char c : list){
                if(c == '('){
                    word = new StringBuilder("");
                    word.append(c);
                }
                if(c == ')'){
                    word.append(c);
                    reverseWord = new StringBuilder(word).reverse().toString();
                    reverseWord = reverseWord.replaceAll("\\(", "");
                    reverseWord = reverseWord.replaceAll("\\)", "");
                    System.out.println(reverseWord);
                    list = inputString.replaceAll(word.toString(), reverseWord).chars().mapToObj(d -> (char) d).collect(Collectors.toList());
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : list) {
            sb.append(ch);
        }
        return sb.toString();
    }

    static boolean arrayShift(int[] elements) {
        List<Integer> identity = Arrays.stream(elements).boxed().collect(Collectors.toList());
        List<Integer> list = Arrays.stream(elements).boxed().collect(Collectors.toList());
        identity.sort(Comparator.naturalOrder());

        boolean result = false;

        for(int i=0;i<list.size();i++){
            List<Integer> shift = new ArrayList<>();
            for(int j=0;j < list.size();j++){
                if(j == 0)
                    shift.add(list.get(list.size() - 1));
                else
                    shift.add(list.get(j-1));
            }
            int t = 0;
            for(int k=0; k < identity.size(); k++){
                if(identity.get(k) == shift.get(k))
                    t++;
            }
            if(t == identity.size()){
                result = true;
            }

            list = new ArrayList<Integer>(shift);
        }
        return result;
    }
}