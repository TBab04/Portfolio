/* 
* @author (Tyler Babcock) 
* <p> (SurviveTheApocalypse) 
* <p> (HW5) 
* <p> (Takes a weighted graph and uses Dijkstra's Algorithm to find the shorted path from Chicago to each city) 
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DSAndAlgos.Graph;

public class SurviveTheApocalypse
{
	public static void main(String[] args)
	{
		Graph<String> cities = new Graph<>();

		BuildGraph(cities);

		System.out.println(cities.toString());

		double[][] adjMatrix = new double[cities.size()][cities.size()];
		for (int i = 0; i < cities.size(); i++)
		{
			for (int j = 0; j < cities.size(); j++)
			{
				double weight = cities.getAdjacencyMatrixValue(i, j);
				adjMatrix[i][j] = weight == Double.MAX_VALUE ? Double.POSITIVE_INFINITY : weight;
			}
		}

		int source = 0;
		double[] dist = dijkstra(adjMatrix, source);
		int[] prev = new int[cities.size()];
		Arrays.fill(prev, -1);
		for (int i = 0; i < cities.size(); i++)
		{
			if (i != source)
			{
				for (int j = 0; j < cities.size(); j++)
				{
					double weight = cities.getAdjacencyMatrixValue(i, j);
					if (weight != Double.MAX_VALUE && dist[j] + weight == dist[i])
					{
						prev[i] = j;
						break;
					}
				}
			}
		}

		printShortestPaths(dist, prev, source, cities);
	}

	private static double[] dijkstra(double[][] graph, int source) {
	    int n = graph.length;
	    double[] dist = new double[n];
	    int[] prev = new int[n];
	    boolean[] known = new boolean[n];
	    Arrays.fill(dist, Double.MAX_VALUE);
	    Arrays.fill(prev, -1);
	    Arrays.fill(known, false);
	    dist[source] = 0;
	    for (int i = 0; i < n - 1; i++) {
	        int minIdx = -1;
	        double minDist = Double.MAX_VALUE;
	        for (int j = 0; j < n; j++)
	            if (!known[j] && dist[j] < minDist) {
	                minIdx = j;
	                minDist = dist[j];
	            }
	        if (minIdx == -1) break;
	        known[minIdx] = true;
	        for (int k = 0; k < n; k++)
	            if (graph[minIdx][k] > 0 && !known[k]) {
	                double alt = dist[minIdx] + graph[minIdx][k];
	                if (alt < dist[k]) { dist[k] = alt; prev[k] = minIdx; }
	            }
	    }
	    return dist;
	}


	private static void printShortestPaths(double[] dist, int[] prev, int source, Graph<String> cities) {
	    System.out.println("Shortest Paths\n--------------");
	    for (int i = 0; i < dist.length; i++) {
	        if (i != source) {
	            List<String> path = new ArrayList<>();
	            int j = i;
	            while (j != -1) { path.add(0, cities.getVertexByIndex(j)); j = prev[j]; }
	            System.out.println("From Chicago to " + cities.getVertexByIndex(i) + ": " + String.join(" -> ", path));
	        }
	    }
	}


	private static void BuildGraph(Graph<String> cities)
	{
		cities.addVertex("Chicago");
		cities.addVertex("Aurora");
		cities.addVertex("Rockford");
		cities.addVertex("Joliet");
		cities.addVertex("Naperville");
		cities.addVertex("Springfield");
		cities.addVertex("Peoria");
		cities.addVertex("Elgin");
		cities.addVertex("Waukegan");
		cities.addVertex("Cicero");
		cities.addVertex("Champaign");
		cities.addVertex("Bloomington");
		cities.addVertex("Decatur");

		// Commented out edges indicate that the androids have a significant presence
		// in a territory along that route that makes using it impossible.

		cities.addEdge("Chicago", "Aurora", 36.4);
		// cities.addEdge( "Chicago", "Rockford", 88.7 ) ;
		cities.addEdge("Chicago", "Joliet", 44.9);
		// cities.addEdge( "Chicago", "Naperville", 35.3 ) ;
		// cities.addEdge( "Chicago", "Springfield", 201.7 ) ;
		// cities.addEdge( "Chicago", "Peoria", 165.7 ) ;
		cities.addEdge("Chicago", "Elgin", 40.4);
		// cities.addEdge( "Chicago", "Waukegan", 41.2 ) ;
		cities.addEdge("Chicago", "Cicero", 9.1);
		// cities.addEdge( "Chicago", "Champaign", 136.4 ) ;
		// cities.addEdge( "Chicago", "Bloomington", 137.6 ) ;
		// cities.addEdge( "Chicago", "Decatur", 180.2 ) ;

		cities.addEdge("Aurora", "Rockford", 72.4);
		// cities.addEdge( "Aurora", "Joliet", 22.6 ) ;
		cities.addEdge("Aurora", "Naperville", 10.3);
		// cities.addEdge( "Aurora", "Springfield", 180.0 ) ;
		cities.addEdge("Aurora", "Peoria", 120.0);
		// cities.addEdge( "Aurora", "Elgin", 21.4 ) ;
		cities.addEdge("Aurora", "Waukegan", 66.4);
		// cities.addEdge( "Aurora", "Cicero", 36.3 ) ;
		cities.addEdge("Aurora", "Champaign", 160.4);
		// cities.addEdge( "Aurora", "Bloomington", 115.9 ) ;
		cities.addEdge("Aurora", "Decatur", 161.1);

		// cities.addEdge( "Rockford", "Joliet", 110.3 ) ;
		cities.addEdge("Rockford", "Naperville", 90.8);
		// cities.addEdge( "Rockford", "Springfield", 199.2 ) ;
		cities.addEdge("Rockford", "Peoria", 136.1);
		// cities.addEdge( "Rockford", "Elgin", 52.2 ) ;
		cities.addEdge("Rockford", "Waukegan", 130.9);
		// cities.addEdge( "Rockford", "Cicero", 89.0 ) ;
		cities.addEdge("Rockford", "Champaign", 184.8);
		// cities.addEdge( "Rockford", "Bloomington", 135.1 ) ;
		cities.addEdge("Rockford", "Decatur", 180.3);

		// cities.addEdge( "Joliet", "Naperville", 19.0 ) ;
		cities.addEdge("Joliet", "Springfield", 165.5);
		// cities.addEdge( "Joliet", "Peoria", 129.6 ) ;
		cities.addEdge("Joliet", "Elgin", 57.4);
		// cities.addEdge( "Joliet", "Waukegan", 69.8 ) ;
		cities.addEdge("Joliet", "Cicero", 32.9);
		// cities.addEdge( "Joliet", "Champaign", 115.7 ) ;
		cities.addEdge("Joliet", "Bloomington", 101.4);
		// cities.addEdge( "Joliet", "Decatur", 146.6 ) ;

		cities.addEdge("Naperville", "Springfield", 179.3);
		// cities.addEdge( "Naperville", "Peoria", 143.4 ) ;
		cities.addEdge("Naperville", "Elgin", 25.7);
		// cities.addEdge( "Naperville", "Waukegan", 58.4 ) ;
		cities.addEdge("Naperville", "Cicero", 29.0);
		// cities.addEdge( "Naperville", "Champaign", 145.5 ) ;
		cities.addEdge("Naperville", "Bloomington", 115.2);
		// cities.addEdge( "Naperville", "Decatur", 160.4 ) ;

		cities.addEdge("Springfield", "Peoria", 74.3);
		// cities.addEdge( "Springfield", "Elgin", 213.2 ) ;
		cities.addEdge("Springfield", "Waukegan", 232.7);
		// cities.addEdge( "Springfield", "Cicero", 195.8 ) ;
		cities.addEdge("Springfield", "Champaign", 86.6);
		// cities.addEdge( "Springfield", "Bloomington", 67.9 ) ;
		cities.addEdge("Springfield", "Decatur", 40.6);

		// cities.addEdge( "Peoria", "Elgin", 152.1 ) ;
		cities.addEdge("Peoria", "Waukegan", 197.0);
		// cities.addEdge( "Peoria", "Cicero", 160.1 ) ;
		cities.addEdge("Peoria", "Champaign", 89.4);
		// cities.addEdge( "Peoria", "Bloomington", 38.2 ) ;
		cities.addEdge("Peoria", "Decatur", 84.9);

		cities.addEdge("Elgin", "Waukegan", 56.7);
		// cities.addEdge( "Elgin", "Cicero", 35.9 ) ;
		cities.addEdge("Elgin", "Champaign", 165.8);
		// cities.addEdge( "Elgin", "Bloomington", 148.1 ) ;
		cities.addEdge("Elgin", "Decatur", 193.3);

		// cities.addEdge( "Waukegan", "Cicero", 50.7 ) ;
		cities.addEdge("Waukegan", "Champaign", 180.5);
		// cities.addEdge( "Waukegan", "Bloomington", 167.9 ) ;
		cities.addEdge("Waukegan", "Decatur", 213.1);

		// cities.addEdge( "Cicero", "Champaign", 140.8 ) ;
		cities.addEdge("Cicero", "Bloomington", 130.9);
		// cities.addEdge( "Cicero", "Decatur", 176.1 ) ;

		cities.addEdge("Champaign", "Bloomington", 50.7);
		// cities.addEdge( "Champaign", "Decatur", 49.1 ) ;

		cities.addEdge("Bloomington", "Decatur", 46.3);
	}
}
