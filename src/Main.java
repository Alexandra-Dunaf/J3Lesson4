public class Main {
    static final int num = 5;
    static Object mon = new Object();
    private static char currentChar = 'A';

    static class CharThread extends Thread {
        private Character currentCharacter, nextCharacter;

        public CharThread(Character currentCharacter, Character nextCharacter) {
            this.currentCharacter = currentCharacter;
            this.nextCharacter = nextCharacter;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                synchronized (mon) {
                    while (currentChar != this.currentCharacter) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(this.currentCharacter);
                    currentChar = nextCharacter;
                    mon.notifyAll();
                }
            }
        }
    }
    public static void main(String[] args) {

        new CharThread('A', 'B').start();
        new CharThread('B', 'C').start();
        new CharThread('C', 'A').start();

    }
}
