import scala.collection.mutable

object Dijkstra {

  case class Edge (to: Int, weight: Int)

  case class VertexPQ(weight: Int, path: Set[Int], last: Int) extends Ordered[VertexPQ] {

    def compare(that: VertexPQ): Int = this.weight compare that.weight

  }

  def process(start: Int, finish: Int, graph: Array[Array[Edge]]): Option[Array[Int]] = {
    var pq = mutable.PriorityQueue[VertexPQ]()
    pq += VertexPQ(0, Set(start), start)
    while (pq.nonEmpty) {
      val curr: VertexPQ = pq.dequeue()
      val last: Int = curr.last
      val path: Set[Int] = curr.path
      if (finish == last) {
         return Some(path.toArray)
      }
      graph(last)
        .filter(edge => !path.contains(edge.to))
        .map(edge =>
            VertexPQ(curr.weight + edge.weight, curr.path + edge.to, edge.to))
        .foreach(pq += _)
    }
    None
  }

}
