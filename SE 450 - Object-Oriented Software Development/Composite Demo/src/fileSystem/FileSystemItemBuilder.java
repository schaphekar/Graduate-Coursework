package fileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemItemBuilder {
	List<IFileSystemItem> fileSystemItems = new ArrayList<>();
	Map<String,String> parentMappings = new HashMap<>();
	
	public void addFolder(String name, String parent) {
		fileSystemItems.add(new Folder(name));
		parentMappings.put(name, parent);
	}
	
	public void addFile(int size, String name, String parent) {
		fileSystemItems.add(new File(size, name));
		parentMappings.put(name, parent);
	}
	
	public IFileSystemItem toFileSystem() {
		IFileSystemItem root = null;
		
		for(IFileSystemItem fileSystemItem: fileSystemItems) {
			String parent = parentMappings.get(fileSystemItem.getName());
			
			if(parent == null)
				root = fileSystemItem;
			else { 
				Folder parentFolder = getFolder(parent);
				parentFolder.addChild(fileSystemItem);
			}
		}
		
		return root;
	}

	private Folder getFolder(String parent) {
		for(IFileSystemItem fileSystemItem: fileSystemItems) {
			if(fileSystemItem.getName().equals(parent));
				return (Folder)fileSystemItem; 
		}
		
		throw new Error("No parent found!");
	}
}
