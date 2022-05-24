package stackoverflowstats.api.model;

public class Stats {
    public final String status;
    private final String message;
    private final int userId;
    private final String username;
    private final int reputation;
    private final int reached;
    private final int answers;
    private final int questions;
    private final String percentile;
    private final int badges;

    public Stats(String status, String message, int userId, String username, int reputation, int reached, int answers, int questions, String percentile, int badges){
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.reputation = reputation;
        this.reached = reached;
        this.answers = answers;
        this.questions = questions;
        this.percentile = percentile;
        this.badges = badges;
    }

    public static Stats error(String status, String message){
        return new Stats(status, message, 0, "", 0, 0, 0, 0, "", 0);
    }

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public int getUserId(){
        return userId;
    }

    public String getUsername(){
        return username;
    }

    public int getReputation(){
        return reputation;
    }

    public int getReached(){
        return reached;
    }

    public int getAnswers(){
        return answers;
    }

    public int getQuestions(){
        return questions;
    }

    public String getPercentile(){
        return percentile;
    }

    public int getBadges(){
        return badges;
    }
}
