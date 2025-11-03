public class GameStats {
    private int missCount;
    private int hitCount;
    private int totalMissCount;
    private int totalHitCount;
    private int strikeCount;

    public GameStats(){
        reset();
    }

    public void registerHit(){
        totalHitCount++;
        hitCount++;
        missCount = 0;
    }

    public void registerMiss(){
        missCount++;
        totalMissCount++;

    }
    public void registerStrike(){
        strikeCount++;
    }

    public int getMissCount(){
        return  missCount;}
    public int getStrikeCount(){
        return strikeCount;
    }
    public int getTotalMissCount(){
        return totalMissCount;
    }
    public int getTotalHitCount(){
        return totalHitCount;
    }
    public void resetMissCounterOnly(){
        missCount = 0;
    }

    public void reset(){
        missCount = 0;
        hitCount = 0;
        strikeCount = 0;
        totalMissCount = 0;
        totalHitCount = 0;
    }

}
