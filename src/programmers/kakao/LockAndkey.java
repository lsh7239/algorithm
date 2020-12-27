package programmers.kakao;

public class LockAndkey {

    public static void main(String[] args) {

        LockAndkey lockAndkey = new LockAndkey();
        System.out.println(lockAndkey.solution(new int[][]{{1,0,0,0}, {0,1,1,0}, {0,0,0,0},{0,0,1,1}},
                new int[][]{{1,1,1,1,1}, {1,0,0,1,1}, {1,1,1,0,1},{1,1,1,1,1},{1,1,1,1,1}}));
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        int[][] newLock = new int[lock.length + 2*(key.length-1)][lock.length + 2*(key.length-1)];
        for(int i = 0 ; i < newLock.length ; i++){
            for(int j = 0 ; j<newLock.length ; j++){
                newLock[i][j] = -1;
                if(i >= key.length-1 && j >= key.length -1 && i-( key.length-1) < lock.length && j - (key.length-1) < lock.length){
                    newLock[i][j] = lock[i-( key.length-1)][j - (key.length-1)];
                }
            }
        }

        answer = checkKey(key, newLock, 0);
        return answer;
    }

    public boolean checkKey(int[][] key, int[][] lock, int rotateCnt){
        if (rotateCnt == 4){
            return false;
        }
        boolean answer = compareValue(key,lock);//compare(key, lock);
        if(answer == true){
            return true;
        }
        if(answer == false){
            answer = checkKey(rotate(key),lock, rotateCnt+1);    //compare(lock[i][j], rotate(key)[i][j]);
        }
        return answer;
    }

    // 90도 회전 (반시계)
    public int[][] rotate(int[][] key){
        int[][] newKey = new int[key.length][key.length];
        for(int i = 0 ; i<key.length ; i++){
            int idx = 0;
            for(int j = (key.length)-1;j>=0 ; j--){
                newKey[j][i] = key[i][idx];
                idx ++;
            }
        }
        return newKey;
    }

    public boolean compareValue(int[][] key, int[][] lock){

        boolean result = true;
        boolean[][] visit = new boolean[lock.length][lock.length];
        for(int idx = 0 ; idx < lock.length; idx++ ){

            for(int idy = 0 ; idy< lock.length; idy ++){
                visit = new boolean[lock.length][lock.length];
                result = true;
                for(int i = 0 ; i<key.length ; i++){
                    for(int j = 0; j < key.length;j++){
                        if(idx+i < lock.length && idy+j < lock.length){
                            if(lock[idx+i][idy+j] != -1 && (key[i][j]^lock[idx+i][idy+j])!=1){
                                result = false;
                            }
                            visit[idx+i][idy+j] = true;
                        }
                    }
                }
                result = result ? checkLock(visit, lock): false;
                if(result){
                    return true;
                }

            }
        }

        return result;
    }

    public boolean checkLock(boolean[][] visit, int[][] lock){
        boolean result = true;
        for(int i = 0 ; i<lock.length ; i++){
            for (int j = 0 ; j<lock.length ; j++){
                if(!visit[i][j] && lock[i][j] == 0){
                    return false;
                }
            }
        }
        return result;
    }

}
