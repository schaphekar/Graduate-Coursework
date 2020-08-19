import fileSystem.FileSystemItemFactory;
import fileSystem.IFileSystemItem;

public class Main {

	public static void main(String[] args) {
		IFileSystemItem fileSystem = FileSystemItemFactory.createFileSystem();
		
		int size = fileSystem.getSize();
		System.out.println(size);	
	}

}
