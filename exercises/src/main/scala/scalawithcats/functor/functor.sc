import scala.language.higherKinds
import cats.Functor
import cats.instances.list._ // for functor


val list1 = List(1,2,3)

val list2 = Functor[List].map(list1)(_ * 2)



import cats.instances.option._ // for functor

val option1 = Option(123)

val option2 = Functor[Option].map(option1)(_.toString)





val func = (x: Int) => x + 1

val liftedFunc = Functor[Option].lift(func)

liftedFunc(Option(4))


// Functions are Functors too

import cats.instances.function._ // Functor for function
import cats.syntax.functor._ // for map

val func1 = (a: Int) => a + 1
val func2 = (a: Int) => a * 2
val func3 = (a: Int) => a + "!"
// this is in the example but won't compile
//val func4 = func1.map(func2)




def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
  start.map(n => n * 2 + 10)


doMath(Option(20))


doMath(List(11, 3, 5))
