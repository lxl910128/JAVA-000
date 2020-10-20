package club.gaiaproject.geekbang;

class HelloWord {
    private String p1;
    public static final int p2 = 2;
    public long p3 = 3L;

    public long add(int arg) {
        int add = arg + p2;
        long mul = add * p3;
        return mul;
    }

    public static void main(String[] args) {
        HelloWord test = new HelloWord();
        System.out.println(test.add(4));
    }
}