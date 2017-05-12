package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason="Banco de dados não conectado")
public class ResourceBadGatewayException extends RuntimeException {
	
	
		private static final long serialVersionUID = 1L;
		public ResourceBadGatewayException(){
			super();
		}
		public ResourceBadGatewayException(String message, Throwable cause){
			super(message, cause);
		}
		public ResourceBadGatewayException(String message){
			super(message);
		}
		public ResourceBadGatewayException(Throwable cause){
			super(cause);
		}
}
