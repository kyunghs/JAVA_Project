public class Stopwatch {
    private long startTime;
    private boolean running;

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            running = false;
        }
    }

    public void reset() {
        startTime = 0;
        running = false;
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return 0;
        }
    }

    public boolean isRunning() {
        return running;
    }
}