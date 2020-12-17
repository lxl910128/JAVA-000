package io.kimmking.rpcfx.error;


/**
 * 业务异常
 */
public class RpcfxException extends RuntimeException {
    
    
    private String message;
    
    private Exception exception;
    
    
    public RpcfxException(String message) {
        this.message = message;
    }
    
    public RpcfxException(String message, Exception e) {
        this.message = message;
        this.exception = e;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Exception getException() {
        return exception;
    }
    
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
