package project;

public class Answer { // 객관식 선지 세트에 사용되는 클래스
	private int number; // 선지 번호
    private String text; // 선지 내용
    
	public Answer(int number, String text) { // 객관식
		super();
		this.number = number;
		this.text = text;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
    
    

    
    
}
