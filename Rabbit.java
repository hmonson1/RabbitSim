import java.util.ArrayList;
import java.util.Random;
public class Rabbit {
    private int gender;
    private boolean pregnant;
    private int gestPeriodLength = 0;
    private int gestPeriodDay = 0;
    private int daysSinceBirth = 0;
    private int age;
    private Random rand = new Random();
    public ArrayList<Rabbit> rabbits = new ArrayList<>();
    private int i;
    private int litterNumber;

    Rabbit(int gender, boolean pregnant, int age) {
        this.gender = gender;
        this.pregnant = pregnant;
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setPregnancy(boolean pregnant) {
        this.pregnant = pregnant;
    }
    public boolean checkPregnancy() {
        return pregnant;
    }

    public void startPregnancy() {
        // gestPeriodLength = (rand.nextInt(28) + 4);
        gestPeriodLength = (int)Math.floor(Math.random() * (32 - 28 + 1) + 28);
        gestPeriodDay = 0;
    }

    public ArrayList<Rabbit> getRabbits() {
        return rabbits;
    }

    public void setRabbits(ArrayList<Rabbit> rabbits) {
        this.rabbits = rabbits;
    }

    public void addRabbit(Rabbit r) {
        this.rabbits.add(r);
    }

    public void incrementGestPeriodDay() {
        this.gestPeriodDay += 1;
    }

    public int getGestPeriodDay() {
        return this.gestPeriodDay;
    }

    public int getGestPeriodLength() {
        return this.gestPeriodLength;
    }

    public void setGestPeriodLength(int gestPeriodLength) {
        this.gestPeriodLength = gestPeriodLength;
    }

    public void setGestPeriodDay(int gestPeriodDay) {
        this.gestPeriodDay = gestPeriodDay;
    }

    public void setDaysSinceBirth(int days) {
        this.daysSinceBirth = days;
    }
    public int getDaysSinceBirth() {
        return daysSinceBirth;
    }
}
