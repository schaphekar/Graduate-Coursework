class Counter {
    private var n = 0
    def increment () = { n = n + 1 }
    def decrement () = { n = n - 1 }
    def get () : Int = n
  }

  // Define a class myCounter to reference
  class myCounter extends Counter {
  private var num = 0
  private var sum = 0

  // Override definitions
  override def increment () = { num = num + 1; sum +=1}
  override def decrement () = { num = num + 1; sum +=1}
  
  def getSum () : Int = sum

}
  // EXERCISE 1: complete the following function.
  // The observeCounter function has one parameter f: a function that accepts (a reference to) a Counter instance but returns nothing.
  // The observeCounter function should call f with (a reference to) an object (of a class extending Counter).
  // Your class that extends Counter must keep track of the total number of times that increment/decrement have been called.
  // I.e., if the increment method is called 3 times on an instance, and the decrement method is called 2 times on the same instance, then it should store 5  (somewhere other than the existing field n).
  // observeCounter should call f, and then return the total number of times that increment/decrement were called on the instance by f.
  
  def observeCounter (f : Counter => Unit) : Int = {
    val a = new myCounter
    f(a)
    a.getSum
  }

  // EXERCISE 2: complete the following function.
  // It is the same as observeCounter except that f has a parameter of type List[Counter] not Counter.
  // f will insist that the List[Counter] has length 3.
  // You must return a List[Int] not an Int.
  // The first element of the result List[Int] must correspond to the number of times that increment/decrement 
  // were called on the first element of type List[Counter], similarly for the second and third elements.

  def observeCounterList (f : List[Counter] => Unit) : List[Int] = {

    // Use val to define each new myCounter
    val a = new myCounter
    val b = new myCounter
    val c = new myCounter

    // Recursive call to f
    f(List(a,b,c))

    // Return the List of type Int
    List(a.getSum, b.getSum, c.getSum)
  }

  // EXERCISE 3: complete the following function.
  // It is the same as observeCounterList except that f has a parameter of type Array[Counter] not List[Counter].
  // f will insist that the Array[Counter] has length 3.
  // You must return a Array[Int] not a List[Int].
  // The first element of the result Array[Int] must correspond to the number of times that increment/decrement 
  // were called on the first element of type Array[Counter], similarly for the second and third elements.

  def observeCounterArray (f : Array[Counter] => Unit) : Array[Int] = {
    
    // Use val to define each new myCounter
    val a = new myCounter
    val b = new myCounter
    val c = new myCounter

    // Same as observeCounterList, except with Array
    f(Array(a,b,c))

    // Return the array of type int
    Array(a.getSum, b.getSum, c.getSum)
  }
}

