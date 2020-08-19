package fileSystem;

public class FileSystemItemFactory {
	public static IFileSystemItem createFileSystem() {
		FileSystemItemBuilder builder = new FileSystemItemBuilder();
		builder.addFolder("Root", null);
		builder.addFile(23, "File3", "Root");
		builder.addFolder("Folder1", "Root");
		builder.addFile(65, "File2", "Folder1");
		builder.addFolder("Folder2", "Folder1");
		builder.addFile(83, "File1", "Folder2");
		
		return builder.toFileSystem();
		
		/*Folder root = new Folder("Root");
		File file3 = new File(23, "File3");
		Folder folder1 = new Folder("Folder1");
		File file2 = new File(65, "File2");
		Folder folder2 = new Folder("Folder2");
		File file1 = new File(83, "File1");
		
		root.addChild(file3);
		root.addChild(folder1);
		folder1.addChild(file2);
		folder1.addChild(folder2);
		folder2.addChild(file1);*/
	}
}
