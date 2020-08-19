public class Main {

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
        for(int i = 1; i <= 10; i++) {
            linkedList.add(i);
        }

        ArrayList arrayList = new ArrayList();
        for(int i = 1; i <= 10; i++) {
        	arrayList.add(i);
        }

        find5(linkedList);
        find5(arrayList);
	}

	private static void find5(IIteratorFactory collection) {
	    IIterator iterator = collection.getIterator();
        while(iterator.hasNext()){
            if(iterator.getNext() == 5)
                System.out.println("Found!");
        }
    }
}
