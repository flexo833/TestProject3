import java.util.Random;

public class Products {
    public  static String name;
    public  static String nums;
    public  static String price;

    public Products() {
        getRandomProdName();
        getRandomNums();
        getRandomPrice();
    }

        public void getRandomProdName () {
            Random rd = new Random();
            String[] prodNames = new String[]{"Thing", "Boots", "SunGlasses", "IPhone", "PowerBank", "Car"};
            String randomName = prodNames[rd.nextInt(prodNames.length)];
            this.name = randomName;
        }

        public void getRandomNums () {
            int nums = (int) (Math.random() * 101);
            String randomNums = String.valueOf(nums);
            this.nums = randomNums;
        }
        public void getRandomPrice () {
            double price = (double) 0.1 * (Math.random() * 101);
            String randomPrice = String.valueOf(price);
            this.price = randomPrice;
        }

    }

