
import java.util.*;

public class OrderLibrary {
	
	public static long maxScore = 0;
	public static Map<Integer, Library> Alllibraries = new HashMap<Integer, Library>();
	//public static List<Integer> AllibraryIDs;
	
	public static Map<Integer, List<Integer>> bestSolution = new HashMap<Integer, List<Integer>>();
	public static int[] AllBookScores;
	
	
	
	public static void orderHelper(HashSet<Integer> chosenBooks, int currentTime, HashSet<Integer> chosenLibraries,
								   long totalScore, int totalDays, Map<Integer, List<Integer>> ans) {
		
		//might need to change the condition to stop
		if (currentTime >= totalDays || chosenLibraries.size() == Alllibraries.size()) {
			if (totalScore > maxScore) {
				maxScore = totalScore;
				bestSolution.clear();
				bestSolution.putAll(ans);


				System.out.println(bestSolution);
				System.out.println(maxScore);
				return;
			}
		}
		
		for (int id = 0; id < Alllibraries.size(); id++) {
			
			if (!(chosenLibraries.contains(id))) {
				Library currentLibrary = Alllibraries.get(id);

				if (currentTime+currentLibrary.signD > totalDays) {
					continue;
				}
				int newTime = currentTime + currentLibrary.signD;
				int daysLeft = totalDays - newTime;
				System.out.println("newtiem: " + newTime + " library id : " + id + " daysLeft: " + daysLeft);
				List<Integer> booksToScan = new ArrayList<Integer>();
				List<Integer> booksInCurrentLibrary = currentLibrary.bookList;
				
				long newTotalScore = totalScore;
				
				for (int i = 0; i < daysLeft * currentLibrary.shipLimit; i++) {
					if (i >= booksInCurrentLibrary.size()) {
						break;
					} else {
						int currentBookId = booksInCurrentLibrary.get(i);
						if (!chosenBooks.contains(currentBookId)) {
							booksToScan.add(currentBookId);
							chosenBooks.add(currentBookId);
							newTotalScore += AllBookScores[currentBookId];
						}

						
					}
					
				}
				
				ans.put(id, booksToScan);
				chosenLibraries.add(id);
				orderHelper(chosenBooks,newTime,chosenLibraries, newTotalScore, totalDays,ans);
				
				
				ans.remove(id);
				chosenLibraries.remove(id);
				chosenBooks.removeAll(booksToScan);

			}
			
		}
		
		
	}
	
	public static Map<Integer, List<Integer>> orderLibrary(List<Library> libraryList, int[] allBookScores, int totalDays) {
		AllBookScores = allBookScores;
		
		for (Library l : libraryList) {
			Alllibraries.put(l.id, l);
		}

		HashSet<Integer> chosenBooks = new HashSet<Integer>();
		HashSet<Integer> chosenLibraries = new HashSet<Integer>();
		HashMap<Integer, List<Integer>> ans = new HashMap<Integer, List<Integer>>();
		orderHelper(chosenBooks,0, chosenLibraries, 0, totalDays, ans);

		System.out.println("best"+bestSolution);
		return bestSolution;
		
		
	}

}
