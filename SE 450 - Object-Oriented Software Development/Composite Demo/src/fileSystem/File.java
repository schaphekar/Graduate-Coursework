package fileSystem;

class File implements IFileSystemItem {
	private int size;
	private String name;
	
	public File(int size, String name) {
		this.size = size;
		this.name = name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getName() {
		return name;
	}
}
