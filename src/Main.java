import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("b_read_on.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output2.txt")));
        String line1 = in.readLine();
        String[] data = line1.split(" ");
        int B = Integer.parseInt(data[0]);
        int L = Integer.parseInt(data[1]);
        int D = Integer.parseInt(data[2]);
        String line2 = in.readLine();
        String[] bookStrArr = line2.split(" ");

        /*
        list of books
         */
        int[] books = new int[bookStrArr.length];


        for (int i = 0; i < bookStrArr.length; i++) {
            books[i] = Integer.parseInt(bookStrArr[i]);
        }

        int l = 0;
        List<Library> libraries = new ArrayList<Library>();
        while (l < L) {
            String first = in.readLine();
            String[] firstData = first.split(" ");
            int numB = Integer.parseInt(firstData[0]);
            int signD = Integer.parseInt(firstData[1]);
            int shipLimit = Integer.parseInt(firstData[2]);

            String second = in.readLine();
            String[] sndArr = second.split(" ");
            List<Integer> bookList = new ArrayList<Integer>();
            for (int i = 0; i < sndArr.length; i++) {
                bookList.add(i, Integer.parseInt(sndArr[i]));
            }
            Library lib = new Library(l, signD, shipLimit, numB, bookList, books);
            libraries.add(lib);
            l++;
        }

        in.close();


        Map<Integer, List<Integer>> answer = OrderLibrary.orderLibrary(libraries, books, D);

        System.out.println(answer == null);

        int numLibraries = answer.size();
        bw.write(numLibraries + "\n");
        for(Map.Entry<Integer, List<Integer>> e : answer.entrySet()){
            int libId = e.getKey();
            List<Integer> libBooks = e.getValue();
            int lenBooks = libBooks.size();
            bw.write(libId+" "+lenBooks + "\n");
            StringBuilder sb = new StringBuilder();
            for (int i:libBooks) {
                sb.append(i + " ");
            }
            bw.write(sb.toString());
            bw.write("\n");


        }
        bw.flush();
        bw.close();
    }


}

