import java.util.*;

public class Library {
    public int id;
    public int signD;
    public int shipLimit;
    public int numBooks;
    public List<Integer> bookList;


    public Library(int id, int time, int limit, int numBooks, List<Integer> bkList, int[] book) {
        this.id = id;
        this.signD = time;
        this.shipLimit = limit;
        this.numBooks = numBooks;


        //sort the list of books
        Map<Integer, Integer> bookIdScoreMap = new HashMap<Integer, Integer>();
        for (Integer bookId : bkList) {
            bookIdScoreMap.put(bookId, book[bookId]);
        }

        List<Map.Entry<Integer, Integer>> resultList = new ArrayList<>(bookIdScoreMap.entrySet());

        resultList.sort(Map.Entry.comparingByValue());
        Collections.reverse(resultList);
        this.bookList = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> e : resultList) {
            bookList.add(e.getKey());
        }

    }

    public void print() {
        System.out.println("Library: " + id + " " + signD + " " + shipLimit + " " + numBooks + " ");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.print(bookList.get(i) + " ");
        }
        System.out.println();
    }

    public void sortList(int[] book) {
        Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();


        List<Map.Entry<Integer, Integer>> resultList = new ArrayList<>(resultMap.entrySet());

        resultList.sort(Map.Entry.comparingByValue());
        Collections.reverse(resultList);
    }


}
