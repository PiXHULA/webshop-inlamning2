package Inlämningsuppgift2.Model.models;

public class showRating {
    String shoe;
    String averageScore;
    String averageScoreText;

    public showRating(String shoe, String averageScore, String averageScoreText) {
        this.shoe = shoe;
        this.averageScore = averageScore;
        this.averageScoreText = averageScoreText;
    }

    public String getShoe() {
        return shoe;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public String getAverageScoreText() {
        return averageScoreText;
    }

    @Override
    public String toString() {
        return "showRating{" +
                "shoe=" + shoe +
                ", averageScore=" + averageScore +
                ", averageScoreText='" + averageScoreText + '\'' +
                '}';
    }
}
