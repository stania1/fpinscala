import cats.kernel.Monoid
import cats.kernel.Semigroup
import cats.kernel.instances.string._

Monoid[String].combine("hello", "world")

// Monoid extends Semigroup, so this should return the same thing
Semigroup[String].combine("hello", "world")


import cats.kernel.instances.int._

// but won't multiplication be a valid operation too? yet the implementation only covers addition
Monoid[Int].combine(3, 7)


import cats.kernel.instances.option._

Monoid[Option[Int]].combine(Some(3), Some(4))

Monoid[Option[String]].combine(Some("hello"), Some("world"))

Monoid[Option[String]].combine(Some("hello"), None)

Monoid[Option[String]].combineAll(List(Some("hello"), Some("world!!"), None))


import cats.syntax.semigroup._

val stringResult = "Hi " |+| "there" |+| Monoid[String].empty

val stringResult1 = "Hi " |+| "there"

// practice using it with foldLeft
List("Hi ", "there ", "FP student!").foldLeft(Monoid[String].empty)(Monoid[String].combine)



// section 2.5.4

/*
The cutting edge SuperAdder v3.5a-32 is the world’s first choice for adding
together numbers. The main func􀦞on in the program has signature def
add(items: List[Int]): Int. In a tragic accident this code is deleted!
Rewrite the method and save the day!
 */

def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(Monoid[Int].combine)

add(List(1, 6, 7))

/*
Well done! SuperAdder’s market share continues to grow, and now
there is demand for additional functionality. People now want to add
List[Option[Int]]. Change add so this is possible. The SuperAdder code
base is of the highest quality, so make sure there is no code duplication!
 */
def add2[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(Monoid[A].combine)


add2(List(1, 6, 7))

add2(List(Some(4), Some(5), None))

/*
SuperAdder is entering the POS (point-of-sale, not the other POS) market.
Now we want to add up Orders:
case class Order(totalCost: Double, quantity: Double)
We need to release this code really soon so we can’t make any modifications
to add. Make it so!
 */

case class Order(totalCost: Double, quantity: Double)

class OrderMonoid extends Monoid[Order] {
  def empty: Order = Order(0.0, 1)

  override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
}

implicit val orderMonoid = new OrderMonoid

val order1 = Order(10.0, 2)
val order2 = Order(15.0, 3)

add2(List(order1, order2))

/*
class StringMonoid extends Monoid[String] {
  def empty: String = ""
  def combine(x: String, y: String): String = x + y

  override def combineAll(xs: TraversableOnce[String]): String = {
    val sb = new StringBuilder
    xs.foreach(sb.append)
    sb.toString
  }
}
 */