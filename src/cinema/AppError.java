package cinema;

public class AppError {
    private String error;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AppError() {
    }

    public AppError( String error) {
        this.error = error;
    }
}
