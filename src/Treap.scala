import java.util.Random

object Treap {
  private val GENERATOR: Random = new Random

  def create(key: Int): Treap = {
    val prior: Int = GENERATOR.nextInt
    new Treap(key, prior, null, null)
  }

  def merge(l: Treap, r: Treap): Treap = {
    if (l == null) {
      return r
    }
    if (r == null) {
      return l
    }
    if (l.priority > r.priority) {
      l.cloneNode(l.left, merge(l.right, r))
    }
    else {
      r.cloneNode(merge(l, r.left), r.right)
    }
  }
}

case class Treap(key: Int, priority: Int, left: Treap = null, right: Treap = null) {

  private def cloneNode(left: Treap, right: Treap): Treap = {
    new Treap(this.key, this.priority, left, right)
  }

  def split(pivot: Int): (Treap, Treap) = {
    if (this.key < pivot) {
      val tuple = if (this.right == null) {
        (null, null)
      } else {
        this.right.split(pivot)
      }
      val l1: Treap = this.cloneNode(this.left, tuple._1)
      (l1, tuple._2)
    }
    else if (this.key > pivot) {
      val tuple = if (this.left == null) {
        (null, null)
      } else {
        this.left.split(pivot)
      }
      val r1: Treap = this.cloneNode(tuple._2, this.right)
      (tuple._1, r1)
    }
    else {
      (this.left, this.right)
    }
  }

  def insert(key: Int): Treap = {
    val tuple: (Treap, Treap) = this.split(key)
    Treap.merge(tuple._1, Treap.merge(Treap.create(key), tuple._2))
  }

  override def toString: String = {
    val ll: String = if (this.left == null) "" else this.left.toString
    val rr: String = if (this.right == null) "" else right.toString
    "(" + ll + " " + this.key + "/" + this.priority + " " + rr + ")"
  }

}
