class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;
        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];

        // pacific
        bfs(pacific, heights, 0,0);
        
        // atlantic
        bfs(atlantic, heights, r-1, c-1);

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j < c ; j++) {
                if(pacific[i][j] && atlantic[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(boolean[][] ocean, int[][] heights, int x , int y ){
        int r = heights.length;
        int c = heights[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0 ; i < r ; i++) {
            ocean[i][y] = true;
            queue.offer(new int[]{i, y});
        }
        for(int i = 0 ; i < c ; i++){
            ocean[x][i] = true;
            queue.offer(new int[]{x,i});
        }
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            OfferDirectionPossible(i+1,j, heights, heights[i][j], queue, ocean );
            OfferDirectionPossible(i-1,j, heights, heights[i][j], queue, ocean );
            OfferDirectionPossible(i,j+1, heights, heights[i][j], queue, ocean );
            OfferDirectionPossible(i,j-1, heights, heights[i][j], queue, ocean );
        }
    }

    private void OfferDirectionPossible(int i , int j , int[][] heights, int currValue, Queue<int[]> queue, boolean[][] ocean ){
        int r = heights.length;
        int c = heights[0].length;
        if(i<0 || j < 0 || i > r-1 || j > c-1) return;
        if( heights[i][j] >= currValue && !ocean[i][j] ){
            queue.add(new int[]{i,j});
            ocean[i][j] = true;
        }
            
    }


}
