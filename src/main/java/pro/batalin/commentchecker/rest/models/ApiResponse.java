package pro.batalin.commentchecker.rest.models;

import org.springframework.http.HttpStatus;

/**
 * @author Kirill Batalin (kir55rus)
 */
public class ApiResponse {
    private Object data;
    private String error;

    public ApiResponse() {
    }

    public static ApiResponse withError(String error) {
        ApiResponse response = new ApiResponse();
        response.setError(error);
        return response;
    }

    public static ApiResponse withData(Object data) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        return response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
