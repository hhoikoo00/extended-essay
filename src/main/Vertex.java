package main;

/* ATTRIBUTE DESCRIPTION
 * -int index
 *      index value of the vertex
 * -int pi
 *      previous vertex for regression in minimum path
 * -int d
 *      minimum distance from source
 *
 * METHOD DESCRIPTION
 * --public Vertex(int index)
 *      constructor: initialize index
 * -public compareTo(Vertex otherVertex): int
 *      overridden method for Comparable interface
 */

public class Vertex implements Comparable<Vertex>
{
    private int index;
    private int pi; // default: -1
    private int d; // default: -1

    public Vertex(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    public int getPi()
    {
        return pi;
    }

    public int getD()
    {
        return d;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public void setPi(int pi)
    {
        this.pi = pi;
    }

    public void setD(int d)
    {
        this.d = d;
    }

    @Override
    public int compareTo(Vertex otherVertex)
    {
        return -1 * Integer.compare(otherVertex.d, this.d);
    }
}
