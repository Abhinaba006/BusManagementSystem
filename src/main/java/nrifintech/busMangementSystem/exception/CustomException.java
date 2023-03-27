package nrifintech.busMangementSystem.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{
	String customMessage;

	public CustomException(String customMessage) {
		super(String.format("%s",customMessage));
		this.customMessage = customMessage;
	}
}
