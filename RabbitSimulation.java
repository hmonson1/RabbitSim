import java.util.ArrayList;
import java.util.Random;

public class RabbitSimulation {
    private ArrayList<Rabbit> rabbitList = new ArrayList<>();
    void generate(int bucks, int does) {
        // Spawns n number of male rabbits based on input
        for (int i = 0; i < bucks; i++) {
            Rabbit r = new Rabbit(1, false, 0);
            rabbitList.add(r);
        }

        // Spawns n number of female rabbits based on input
        for (int i = 0; i < does; i++) {
            Rabbit r = new Rabbit(0, false, 0);
            rabbitList.add(r);
        }
    }

    public int getBucks() {
        int bucksCount = 0;
        for (int i = 0; i < rabbitList.size(); i++) {
            if (rabbitList.get(i).getGender() == 1) {
                bucksCount++;
            }
        }
        return bucksCount;
    }

    public int getDoes() {
        int doesCount = 0;
        for (int i = 0; i < rabbitList.size(); i++) {
            if (rabbitList.get(i).getGender() == 0) {
                doesCount++;
            }
        }
        return doesCount;
    }



    public void haveLitter() {
        Random rand = new Random();

        int litterNumber = (int)Math.floor(Math.random() * (8 - 3 + 1) + 3);
        for (int i = 0; i < litterNumber; i++) {
            int gender = rand.nextInt() % 2;
            rabbitList.add(new Rabbit(gender, false, 0));
            /* if (gender == 0) {
                System.out.println("boy");
            }
            if (gender == 1) {
                System.out.println("girl");
            }*/
        }
    }






    void oneDay() {
        for (int i = 0; i < rabbitList.size(); i++) {
            int litterCount = 0;
            // Gets the age of the current rabbit and adds a day to it
            int currentAge = rabbitList.get(i).getAge();
            rabbitList.get(i).setAge(currentAge + 1);


            // Checks if rabbit is over 100 days old and handles pregnancy
            if ((rabbitList.get(i).getAge() >= 100) && (rabbitList.get(i).getGender() == 0)) {
                // If the rabbit hasn't given birth in 7 days, start pregnancy. Otherwise, add another day
                if (rabbitList.get(i).getDaysSinceBirth() >= 7) {
                    // Checks if rabbit is already pregnant. If not, impregnates the rabbit
                    if (!rabbitList.get(i).checkPregnancy()) {
                        rabbitList.get(i).setPregnancy(true);
                        rabbitList.get(i).startPregnancy();
                    } else {
                        rabbitList.get(i).incrementGestPeriodDay();
                        if (rabbitList.get(i).getGestPeriodDay() >= rabbitList.get(i).getGestPeriodLength()) {
                            litterCount++;
                            // System.out.println("Rabbits born! It took " + rabbitList.get(i).getGestPeriodLength());
                            rabbitList.get(i).setGestPeriodDay(0);
                            rabbitList.get(i).setGestPeriodLength(0);
                            rabbitList.get(i).setPregnancy(false);
                            rabbitList.get(i).setDaysSinceBirth(0);
                        }
                    }
                } else {
                    int daysBirth = rabbitList.get(i).getDaysSinceBirth();
                    rabbitList.get(i).setDaysSinceBirth(daysBirth + 1);
                }
            }

            for (int j = 0; j < litterCount; j++) {
                haveLitter();
            }
        }
    }


}
