package main;

/* ATTRIBUTE DESCRIPTION
 * -int indexSource
 *      index of source vertex
 * -int indexDestination
 *      index of destination vertex
 * -int weight
 *      weight of the edge
 *
 * METHOD DESCRIPTION
 * --public Edge(int indexSource, int indexDestination, int weight)
 *      constructor: initialize indexSource, indexDestination, weight
 */

public class Edge
{
    private int indexSource;
    private int indexDestination;
    private int weight;

    public Edge(int indexSource, int indexDestination, int weight)
    {
        this.indexSource = indexSource;
        this.indexDestination = indexDestination;
        this.weight = weight;
    }

    public int getIndexSource()
    {
        return indexSource;
    }

    public int getIndexDestination()
    {
        return indexDestination;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setIndexSource(int indexSource)
    {
        this.indexSource = indexSource;
    }

    public void setIndexDestination(int indexDestination)
    {
        this.indexDestination = indexDestination;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }
}
