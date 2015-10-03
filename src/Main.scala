import java.util.Arrays

import Dijkstra.Edge

object Main {
  
  def checkTreap() {
    System.out.println("checkTreap:")
    val t0 = Treap.create(0)
    val t2 = Treap.create(2)
    val t4 = Treap.create(4)
    val res = Treap.merge(Treap.merge(t0, t2), t4)
    val tmp = res.insert(6).insert(1).insert(3)
    println(tmp)
  }

  def checkDijkstra() {
    System.out.println("checkDijkstra:")
    val graph = Array(
      Array(Edge(1, 10), Edge(2, 1)),
      Array(Edge(3, 2)),
      Array(Edge(3, 10)),
      Array(Edge(4, 10))
    )
    val result = Dijkstra.process(0, 4, graph)
    println(result.get.toSeq)
  }

  def main(args: Array[String]) {
    checkDijkstra()
    checkTreap()
    System.out.println("DONE")
  }
}