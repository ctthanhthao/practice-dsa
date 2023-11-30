package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrintNameAndEmail {
    public static int bitwiseAnd(int N, int K) {
        // Build list from 1 to N
        List<Integer> arrIntegers = new ArrayList<>();
        for (int i=1; i <= N; i++) {
            System.out.println("i = " + i);
            arrIntegers.add(i);
        }
        int max = 0;
        int result;
        for (int i = 0; i< arrIntegers.size() -1; i++) {
            for (int j = i+1; j<arrIntegers.size();j++) {
                result = arrIntegers.get(i) & arrIntegers.get(j);
                System.out.println("Result = " + result);
                if (result < K) {
                    max = Math.max(result, max);
                    System.out.println("Max is " + max);
                }
            }
        }
        return max;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int count = Integer.parseInt(firstMultipleInput[0]);

                int lim = Integer.parseInt(firstMultipleInput[1]);

                int res = bitwiseAnd(count, lim);

                System.out.println(String.valueOf(res));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
