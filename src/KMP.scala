
object KMP {

  private def p(s: String): Array[Int] = {
    val len: Int = s.length
    val p: Array[Int] = Array(len)
    for (i <- Range(1, len)) {
      var j: Int = p(i - 1)
      while (j > 0 && s.charAt(i) != s.charAt(j)) {
        j = p(j - 1)
      }
      if (s.charAt(i) == s.charAt(j)) {
        j += 1
      }
      p(i) = j
    }
    p
  }

  private def z(s: String): Array[Int] = {
    val len: Int = s.length
    val z: Array[Int] = Array(len)
    var l: Int = 0
    var r: Int = 0
    for (i <- Range(1, len)) {
      if (i < r) {
        z(i) = Math.min(z(i - l), r - i)
      }
      while (i + z(i) < len && s.charAt(z(i)) == s.charAt(i + z(i))) {
        z(i) += 1
      }
      if (i + z(i) > r) {
        l = i
        r = i + z(i)
      }
    }
    z
  }

}
