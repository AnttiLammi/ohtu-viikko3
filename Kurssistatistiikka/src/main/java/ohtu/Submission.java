package ohtu;


public class Submission {
    private String course;
    private int week;
    private int hours;
    private Integer[] exercises;

    public Submission(String course, int week, int hours, Integer[] exercises) {
        this.course = course;
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
    
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String part1 = course + ", viikko "+week + " tehtyjä tehtäviä yhteensä " + exercises.length + ", aikaa kului " + hours + " tuntia. Tehdyt tehtävät: ";
        String part2 = "";
        for (int i=0; i<exercises.length-2; i++){
            part2 = part2 + exercises[i].toString() + ", ";
        }
        part2 = part2 + this.exercises[this.exercises.length-1];
        return part1 + part2;
    }
}