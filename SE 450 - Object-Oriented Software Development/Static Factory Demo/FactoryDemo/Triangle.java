package FactoryDemo;

class Triangle extends Shape {

    private boolean isEquilateral;
    Triangle(int numSides, boolean isEquilateral){
        super(numSides);
        this.isEquilateral = isEquilateral;
    }

    @Override
    public String getString() {
        return "Triangle";
    }
}
