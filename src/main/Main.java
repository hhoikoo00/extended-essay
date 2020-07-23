package main;

/* METHOD DESCRIPTION
 * -public static main(String[] args): void
 *      main method
 */

import java.io.*;
import java.nio.file.*;

public class Main
{
    private static final int NUM_GRAPH = 5;
    private static final int NUM_ATTEMPT = 10;
    private static int[] listVertexNumber = { 50, 100, 150, 200, 210, 220, 230, 240, 250};

    public static void main(String[] args) throws IOException
    {
        int numberOfVertex;

        for (int i: listVertexNumber) {
            numberOfVertex = i;
            Graph g = new Graph(numberOfVertex);
            GraphSearch gs = new GraphSearch();

            /* file output setup
             */
            Path path = Paths.get(System.getProperty("user.home")
                    + "/Downloads/output/" + Integer.toString(numberOfVertex) );

            File fileList = new File(path + "/vertex_list.txt");
            File fileTime = new File(path + "/timing.txt");
            PrintStream psList = null;
            PrintStream psTime = null;

            try {
                fileList.delete(); fileTime.delete(); // delete files
                Files.createDirectories(path); // initialize files
                fileList.createNewFile(); fileTime.createNewFile();

                psList = new PrintStream(
                        new FileOutputStream(fileList.getAbsolutePath(), true) );
                psTime = new PrintStream(
                        new FileOutputStream(fileTime.getAbsolutePath(), true) );
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            /* process
             */
            for (int j=0; j<NUM_GRAPH; j++) {
                g.generateGraph(numberOfVertex);

                System.setOut(psList);
                System.out.printf("Graph %d\n", j);
                g.printAdjacencyList();
                System.setOut(psTime);
                System.out.printf("Graph %d\n", j);

                /* Bellman-Ford
                 */
                System.out.printf("Bellman-Ford\n");
                long bfStartTime = 0;
                long bfEndTime = 0;
                for (int k=0; k<NUM_ATTEMPT; k++) {
                    bfStartTime = System.nanoTime();
                    gs.bellmanFord(g);
                    bfEndTime = System.nanoTime();

                    System.out.printf("%d\n", (bfEndTime-bfStartTime) );
                }

                /* Dijkstra's
                 */
                System.out.printf("Dijkstra's\n");
                long dStartTime = 0;
                long dEndTime = 0;
                for (int k=0; k<NUM_ATTEMPT; k++) {
                    dStartTime = System.nanoTime();
                    gs.dijkstras(g);
                    dEndTime = System.nanoTime();

                    System.out.printf("%d\n", (dEndTime-dStartTime) );
                }

                /* output shortest path
                 */
                System.setOut(psList);
                g.printShortestPath();
                System.out.println();
            }

            psList.close();
            psTime.close();
        }
    }
}
