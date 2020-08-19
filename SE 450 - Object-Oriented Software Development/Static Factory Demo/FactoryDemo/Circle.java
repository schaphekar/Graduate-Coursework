package FactoryDemo;

class Circle extends Shape {
	public Circle(int numSides) {
		super(numSides);
	}

	public String getString(){
		return "Circle";	
	}
}
