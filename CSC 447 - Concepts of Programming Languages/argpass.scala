object argpass {

  class RefInt (initial : Int) {
    private var n : Int = initial 
    def get () : Int = n 
    def set (m : Int) : Unit = { n = m }
 }

  // EXERCISE 1: complete the following higher-order function.
  // It has one parameter f: a function that takes an instance of RefInt (see above for the definition of the class RefInt) and returns Unit (i.e., nothing interesting).
  // Your code must create ONLY ONE instance of RefInt, then call f three times.
  // f will update the integer stored in the instances of RefInt it is given.
  // Your code must return a tuple of the three integers provided by f in the order that they came back from calls, i.e., the integer from the first call to f is the first integer in the returned tuple.
  def refint1 (f : RefInt => Unit) : (Int, Int, Int) = {
    var x = new RefInt(2)
    val a = f(x)
    val a1 = x.get
    val b = f(x)
    val b1 = x.get
    val c = f(x)
    val c1 = x.get
  (a1,b1,c1)
  }

  // EXERCISE 2: complete the following higher-order function.
  // It has one parameter f: a function that takes an instance of RefInt (see above for the definition of the class RefInt) and returns Unit (i.e., nothing interesting).
  // Your code must create EXACTLY THREE instances of RefInt, then call f three times.
  // f will update the integer stored in the instances of RefInt it is given.
  // However, f will not do this update immediately.  
  // It will only do it after the third call to f: at that point it updates all three instances of RefInt that it has received as arguments so far.
  // Your code must return a tuple of the three integers provided by f in the order that they came back from calls, i.e., the integer from the first call to f is the first integer in the returned tuple.
  def refint2 (f : RefInt => Unit) : (Int, Int, Int) = {
    val a = new RefInt(0)
    val b = new RefInt(0)
    val c = new RefInt(0)
    f(a)
    f(b)
    f(c)
    val x = a.get
    val y = b.get
    val z = c.get
    (x,y,z)
  }

  // EXERCISE 3: complete the following function.
  // It has one parameter r: (a reference to) an instance of RefInt (see above for the definition of the class RefInt) and returns (a reference to) an instance of RefInt.
  // Your code must increment (add 1 to) the RefInt it receives and return double the original value (stored in a separate RefInt instance) as the result.
  def refint3 (r : RefInt) : RefInt = {
    val x = new RefInt(r.get*2)
    r.set(r.get+1)
    x
  }

  // EXERCISE 4: complete the following function.
  // It has two parameters
  // - r: (a reference to) an instance of RefInt (see above for the definition of the class RefInt); AND
  // - f: a function that accepts (a reference to) an instance of RefInt.
  // Your code must call f with a *copy* of the RefInt r, i.e., it must make a new instance of RefInt with the same Int.
  // Your code should return true if f has NOT changed the Int stored in the copy of r.  Otherwise it should return false.
  def refint4 (r : RefInt, f : RefInt => Unit) : Boolean = {
    
    val x = r.get()
    val x1 = new RefInt(x)
    f(x1)
    
    val y = x1.get()

    if(x != y) 
    	return false
    else 
    	return true

  }

  // EXERCISE 5: complete the following function.
  // It has one parameter: a list of (references to) RefInt instances.
  // Your code must CHANGE the argument it receives in a way that is visible to the caller.
  // Note that this is the Scala List type, which is immutable!
  // You can assume that the list you receive is not empty.
  def refint5 (xs : List[RefInt]) : Unit = {
    xs match{
    case Nil => Nil
    case x::xs => { 
      x.set(x.get*2) 
      refint5(xs) }
  }
  }
}

