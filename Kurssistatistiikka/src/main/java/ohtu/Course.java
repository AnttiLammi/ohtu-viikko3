package ohtu;


public class Course {
    public String name;
    public Integer week;
    public Integer[] exercises;
    public String fullName;
    public String term;
    public Integer year;
    public Boolean miniproject;

    private String id;
    private String url;
    private Boolean enabled;
    
    public Course(String id, String name, String url, Integer week, Boolean enabled, String term, Integer year, String fullName, Boolean miniproject, Integer[] exercises) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.week = week;
        this.enabled = enabled;
        this.term = term;
        this.year = year;
        this.fullName = fullName;
        this.miniproject = miniproject;
        this.exercises = exercises;
    
    }

    public int totalExercises(){
        Integer total = 0;
        for (int i=0; i<this.exercises.length; i++){
            total+=this.exercises[i];
        }
        return total;
    }
}