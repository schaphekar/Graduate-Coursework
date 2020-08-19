package fileSystem;
import java.util.ArrayList;
import java.util.List;

class Folder implements IFileSystemItem {
	private String name;
	private List<IFileSystemItem> children = new ArrayList<IFileSystemItem>();
	
	public Folder(String name) {
		this.name = name;
	}
	
	public void addChild(IFileSystemItem fileSystemItem) {
		children.add(fileSystemItem);
	}

	@Override
	public int getSize() {
		int size = 0;
		for(IFileSystemItem child : children) {
			size += child.getSize();
		}
		
		return size;
	}

	@Override
	public String getName() {
		return name;
	}
}
