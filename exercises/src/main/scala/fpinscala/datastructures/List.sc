import fpinscala.datastructures.List._
import fpinscala.datastructures.{List => FPList}

val l1 = FPList(1, 2, 3, 4, 5)

dropWhile[Int](l1, x => x % 2 == 0)


init(l1) == FPList(1, 2, 3, 4)


