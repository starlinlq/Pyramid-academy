package engine;

public class Response {
    private boolean success;
    private String feedback;

    public Response(){};
    public Response(boolean success, String feedback){
        this.success = success;
        this.feedback = feedback;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public void setFeedback(String feedback){
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback(){
        return this.feedback;
    }
}
