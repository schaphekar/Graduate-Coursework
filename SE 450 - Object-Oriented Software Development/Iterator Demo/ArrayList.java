public class ArrayList implements ICollection {
	int[] arr = new int[5];
    int numberOfElements = 0;
    public void add(int i) {
        if(numberOfElements >= arr.length) {
            growArray();
        }
        arr[numberOfElements++] = i;
    }

    private void growArray() {
        int[] tempArray = new int[numberOfElements * 2];
        System.arraycopy(arr, 0, tempArray, 0, arr.length);
        arr = tempArray;
    }

    public int get(int index) {
        if (index > numberOfElements)
            return 0;
        return arr[index];
    }

    @Override
    public IIterator getIterator() {
        return new ArrayListIterator(this);
    }

    class ArrayListIterator implements IIterator {
        private final ArrayList list;
        private int currentIndex = 0;

        public ArrayListIterator(ArrayList list){
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.numberOfElements;
        }

        @Override
        public int getNext() {
            int val = list.get(currentIndex);
            currentIndex++;
            return val;
        }
    }

}
