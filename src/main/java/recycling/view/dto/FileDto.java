package recycling.view.dto;

// DB구조에 맞는 DTO
public class FileDto {
	
	private int fileno;
	private String title;
	private String originName;
	private String storedName;
	
	public FileDto() {}

	public FileDto(int fileno, String title, String originName, String storedName) {
		this.fileno = fileno;
		this.title = title;
		this.originName = originName;
		this.storedName = storedName;
	}

	@Override
	public String toString() {
		return "Filetest [fileno=" + fileno + ", title=" + title + ", originName=" + originName + ", storedName="
				+ storedName + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int titleno) {
		this.fileno = titleno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}
}
