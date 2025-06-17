public class Client {
    private static Runnable getRunnable() {

        return new Runnable() {
            @Override
            public void run() {

            }
        }
    }
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(Client.getRunnable);
            thread.start();
        }catch(
    }
}
